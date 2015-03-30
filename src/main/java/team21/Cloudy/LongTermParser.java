package team21.Cloudy;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class obtains the JSONObjects from the website and store it into LongTermData
 *
 * @author: Gurpreet
 */
public class LongTermParser extends Parser
{
    /**** Fields ****/
    private String locationName; 	//Stores the name of the location
    private LongTermData[] data; 	//Stores an array of forecasts (each element represent the forecast for that hour)
    private Configuration config; 	//Stores the user's preference

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public LongTermParser(String locationName)
    {
        this.locationName = locationName.replace(" ", "%20");

        //Create a configuration object
        config = new Configuration();

        //Load the configuration stored in file 
        config.load();

        //Initialize the LongTermdata array
        data = new LongTermData[5];
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return a LongTerm object
     */
    @Override
    protected TermObject parse() throws IOException
    {
        //A string that will contain the generated URL
        String url = "";

        //A switch statement that specifies the url based on the degrees units set in the config.
        switch (config.getDegrees())
        {
            case IMPERIAL:
            {
            	//Generate and store the url based off constants
                url = baseURL + longModifier + locationName + imperialModifier + APIKEY;
                break;
            }
            case METRIC:
            {
            	//Generate and store the url based off constants
                url = baseURL + longModifier + locationName + metricModifier + APIKEY;
            }
        }

        //Create a request and pass in the URL to the OKHttp library.
        Request request = new Request.Builder().url(url).build();

        //Make a new call with the OkHTTPClient and pass in the request
        Call call = client.newCall(request);

        //Use OkHttp's ability to ask for new data and store it when it is returned
        Response response = call.execute();

        //Take the raw data
        String JSONData = response.body().string();

        //Populate the data array from the JSONArray
        getArray(JSONData);

		//Create a new longTerm object using the data array and return it
        return new LongTerm(data);
    }//End of parse method

    /**
     * Extracting groups of data from the JSONObject. (there are 5 sets of data -- forecasts for the next 5 days)
     *
     * @param jsonData the json that the array will be extracted from
     */
    private void getArray(String jsonData) throws JSONException
    {
    	//Creates a new JSONObject of the whole data
        JSONObject forecast = new JSONObject(jsonData);

        //Extracts the array of forecast data from the json list stored in the forecast object
        JSONArray arr = forecast.getJSONArray("list");

        //Use each element of the forecast array and get it's details, then store it in a new array.
        for (int i = 0; i < 5; i++)
        {
            data[i] = getDetails(arr.getJSONObject(i).toString());
        }
    }

    /**
     * * Extract Specific data from each group and storing to LongTermData
     
     * @param rawJSONData the jsondata that was extracted
     * @return LongTermData Object
     */
    @Override
    protected LongTermData getDetails(String rawJSONData)
    {
    	//Takes the string and converts it into a JSONObject
        JSONObject forecast = new JSONObject(rawJSONData);

        //Takes the forecast JSONObject and extracts the first element of the json array
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        //Extract the temp JSONObject from forecast
        JSONObject temp = forecast.getJSONObject("temp");

        //Obtain required data from all extracted objects and return them in the form of a LongTermObject
        return new LongTermData(
                temp.getDouble("night"),
                temp.getDouble("eve"),
                temp.getDouble("morn"),
                temp.getDouble("day"),
                temp.getDouble("min"),
                temp.getDouble("max"),
                forecast.getDouble("speed"),
                forecast.getInt("deg"),
                forecast.getDouble("pressure"),
                weather.getString("icon"),
                weather.getString("description"),
                forecast.getInt("dt"),
                forecast.getDouble("humidity")
        );
    }//End of getDetails method
}//End of LongTermParser class
