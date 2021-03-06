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
 * Class obtains the JSONObjects from the website and store it into ShortTermData
 *
 * @author: Gurpreet
 */
public class ShortTermParser extends Parser
{
    /**
     * Name of the location
     */
    private String locationName;

    /**
     * Stores an array of forecasts (each element represent the forecast for that hour)
     */
    private ShortTermData[] data;

    /**
     * Stores the user's preference
     */
    private Configuration config;

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public ShortTermParser(String locationName)
    {
        this.locationName = locationName.replace(" ", "%20");
        config = new Configuration();
        config.load();
        data = new ShortTermData[8];
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return a ShortTerm object
     * @throws IOException When the parse is not successful this exception is thrown
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
                url = baseURL + shortModifier + locationName + imperialModifier + APIKEY;
                break;
            }
            case METRIC:
            {
            	//Generate and store the url based off constants
                url = baseURL + shortModifier + locationName + metricModifier + APIKEY;
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

        //Populate the data array from JSONArray
        getArray(JSONData);

        //Create a new ShortTerm object using the data array and return it
        return new ShortTerm(data);
    }//End of parse method

    /**
     * Extracting groups of data from the JSONObject. (there are 8 sets of data -- 3 hour forecasts for 24 hours)
     *
     * @param jsonData the raw data retrieved from the API call
     * @throws JSONException When the data cannot be put into a JSON object, this exception is thrown
     */
    private void getArray(String jsonData) throws JSONException
    {
    	//Creates a new JSONObject of the whole data
        JSONObject forecast = new JSONObject(jsonData);

        //Extracts the array of forecast data from the json list stored in the forecast object
        JSONArray arr = forecast.getJSONArray("list");

        //Use each element of the forecast array and get it's details, then store it in a new array.
        for (int i = 0; i < 8; i++)
        {
            data[i] = getDetails(arr.getJSONObject(i).toString());
        }
    }

    /**
     * Extract Specific data from each group and storing to ShortTermData
     *
     * @param rawJSONData The data retrieved from the API call
     * @return ShortTermData object
     */
    @Override
    protected ShortTermData getDetails(String rawJSONData)
    {
    	//Takes the string and converts it into a JSONObject
        JSONObject forecast = new JSONObject(rawJSONData);

        //Takes the forecast JSONObject and extracts the first element of the json array
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        //Extract the main JSONObject from forecast
        JSONObject main = forecast.getJSONObject("main");

        //Extract the clouds JSONObject from forecast
        JSONObject clouds = forecast.getJSONObject("clouds");

        //Extract the wind JSONObject from forecast
        JSONObject wind = forecast.getJSONObject("wind");
        //JSONObject snow = forecast.getJSONObject("snow");


        //Obtain required data from all extracted objects and return them in the form of a ShortTermObject
        return new ShortTermData(
                forecast.getString("dt_txt"),
                main.getDouble("temp"),
                main.getDouble("temp_min"),
                main.getDouble("temp_max"),
                wind.getDouble("speed"),
                wind.getInt("deg"),
                main.getDouble("pressure"),
                weather.getString("icon"),
                weather.getString("description"),
                forecast.getInt("dt"),
                main.getDouble("humidity")
        );
    }//End of getDetails method

}
