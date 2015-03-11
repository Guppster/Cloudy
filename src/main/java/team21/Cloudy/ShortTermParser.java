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
    /**** Fields ****/
    private String locationName;        //Stores the name of the location
    private ShortTermData[] data;       //Stores an array of forecasts (each element represent the forecast for that hour)
    private Configuration config;       //Stores the user's preference

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public ShortTermParser(String locationName)
    {
        this.locationName = locationName;
        config = new Configuration();
        config.load();
        data = new ShortTermData[7];
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return a ShortTerm object
     */
    @Override
    protected TermObject parse()
    {
    	//A string that will contain the generated URL
        String url = "";

        //A switch statement that specifies the url based on the degrees units set in the config.
        switch (config.getDegrees())
        {
            case IMPERIAL:
            {
            	//Generate and store the url based off constants
                url = baseURL + shortModifier + locationName + imperialModifier;
                break;
            }
            case METRIC:
            {
            	//Generate and store the url based off constants
                url = baseURL + shortModifier + locationName + metricModifier;
            }
        }

       	//Create a request and pass in the URL to the OKHttp library. 
        Request request = new Request.Builder().url(url).build();

        //Make a new call with the OkHTTPClient and pass in the request
        Call call = client.newCall(request);

        //Use the OkHttp's callback ability to ask for new data and store it when it is returned 
        final String finalUrl = url;
        call.enqueue(new Callback()
        {
        	//The following method specifies what happens when the request fails.
            @Override
            public void onFailure(Request request, IOException e)
            {

            }

            //The following method specifies what happens when the request is successfull. 
            @Override
            public void onResponse(Response response) throws IOException
            {
                try
                {
                	//Take the raw data 
                    String JSONData = response.body().string();

                    //Debug println
                    System.out.println(finalUrl + " " + locationName + " data recieved!");

                    //If the response is not successful state that there was an error
                    if (!response.isSuccessful())
                    {
                        System.out.println("ERROR: REQUEST UNSUCCESSFUL!");
                    }
                    else
                    {
                    	//If it is successfull obtain the array of data
                        getArray(JSONData);
                    }
                } catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

		//Create a new shortTerm object using the data array and return it
        return new ShortTerm(data);
    }//End of parse method

    /**
     * Extracting groups of data from the JSONObject. (there are 8 sets of data -- 3 hour forecasts for 24 hours)
     *
     * @param jsonData
     */
    private void getArray(String jsonData) throws JSONException
    {
    	//Creates a new JSONObject of the whole data
        JSONObject forecast = new JSONObject(jsonData);

        //Extracts the array of forecast data from the json list stored in the forecast object
        JSONArray arr = forecast.getJSONArray("list");

        //Use each element of the forecast array and get it's details, then store it in a new array.
        for (int i = 0; i < 7; i++)
        {
            data[i] = getDetails(arr.getJSONObject(i).toString());
        }
    }

    /**
     * Extract Specific data from each group and storing to ShortTermData
     *
     * @param rawJSONData
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
                weather.getString("description")
        );
    }//End of getDetails method

}
