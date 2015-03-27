package team21.Cloudy;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class obtains the JSONObjects from the website and store it into CurrentTermData
 *
 * @author: Gurpreet
 */
public class CurrentTermParser extends Parser
{
    /**** Fields ****/
    private String locationName;        //Stores the name of the location
    private CurrentTermData[] data;     //Stores an array containing just one element with the current weather
    private Configuration config;       //Stores the user's saved preferences

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public CurrentTermParser(String locationName)
    {
        this.locationName = locationName.replace(" ", "%20");

        //Create a configuration object
        config = new Configuration();

        //Load the configuration stored in file
        config.load();

        //Initialize the CurrentTermdata array
        data = new CurrentTermData[1];
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return CurrentTerm object
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
                url = baseURL + currentModifier + locationName + imperialModifier + APIKEY;
                break;
            }
            case METRIC:
            {
                //Generate and store the url based off constants
                url = baseURL + currentModifier + locationName + metricModifier + APIKEY;
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

        //Populate the data array with JSONElements
        data[0] = getDetails(JSONData);

        //Create a new longTerm object using the data array and return it
        return new CurrentTerm(data);
    }//End of parse method

    /**
     * Extract Specific data from each group and storing to CurrentTermData
     *
     * @param rawJSONData
     * @return CurrentTermData Object
     */
    @Override
    protected CurrentTermData getDetails(String rawJSONData)
    {
        //Takes the string and converts it into a JSONObject
        JSONObject forecast = new JSONObject(rawJSONData);

        //Extract the sys JSONObject from forecast
        JSONObject sys = forecast.getJSONObject("sys");

        //Takes the forecast JSONObject and extracts the first element of the json array
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        //Extract the main JSONObject from forecast
        JSONObject main = forecast.getJSONObject("main");

        //Extract the wind JSONObject from forecast
        JSONObject wind = forecast.getJSONObject("wind");

        //JSONObject rain = forecast.getJSONObject("rain");

        locationName = forecast.getString("name") + sys.getString("country");

        //Obtain required data from all extracted objects and return them in the form of a LongTermObject
        return new CurrentTermData(
                //rain.getInt("3h"),
                3,
                sys.getInt("sunrise"),
                sys.getInt("sunset"),
                main.getDouble("temp"),
                main.getDouble("temp_min"),
                main.getDouble("temp_max"),
                wind.getDouble("speed"),
                wind.getInt("deg"),
                main.getDouble("pressure"),
                weather.getString("icon"),
                weather.getString("description"),
                sys.getString("country"),
                forecast.getInt("dt"),
                main.getDouble("humidity")
        );
    }//End of getDetails method
}//End of CurrentTermParser class
