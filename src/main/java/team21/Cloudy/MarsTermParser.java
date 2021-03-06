package team21.Cloudy;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class obtains the JSONObjects from the website and store it into MarsData
 *
 * @author: Gurpreet
 */
public class MarsTermParser extends Parser
{
    /**
     * The name of the location
     */
    private String locationName;

    /**
     * The array of data
     */
    private MarsData[] data;

    /**
     * The configuration storing preferences
     */
    private Configuration config;

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public MarsTermParser(String locationName)
    {
        this.locationName = locationName;
        data = new MarsData[1];

        //Create a configuration object
        config = new Configuration();

        config.load();
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return MarsTerm object
     */
    @Override
    protected TermObject parse() throws IOException
    {
        //A string that will contain the generated URL
        String url = "http://marsweather.ingenology.com/v1/latest/?format=json";

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

        //Create a new MarsTerm object using the data array and return it
        return new MarsTerm(data);
    }

    /**
     * Extract Specific data from each group and storing to MarsData
     *
     * @param rawJSONData The data retrieved from the API Call
     * @return MarsData object
     */
    @Override
    protected MarsData getDetails(String rawJSONData)
    {
        JSONObject forecast = new JSONObject(rawJSONData);
        JSONObject report = forecast.getJSONObject("report");

        String tagMin = "";
        String tagMax = "";

        switch (config.getDegrees())
        {
            case IMPERIAL:
                tagMin = "min_temp_fahrenheit";
                tagMax = "max_temp_fahrenheit";
                break;
            case METRIC:
                tagMin = "min_temp";
                tagMax = "max_temp";
                break;
        }

        return new MarsData(
                getAvg(report.getDouble(tagMin), report.getDouble(tagMax)),
                report.getDouble(tagMin),
                report.getDouble(tagMax),
                //report.getDouble("wind_speed"),
                32,
                //Integer.valueOf(report.getString("wind_direction")),
                5,
                report.getDouble("pressure"),
                report.getString("season"),
                report.getString("atmo_opacity"),
                Integer.parseInt(report.getString("terrestrial_date").split("-")[0] + report.getString("terrestrial_date").split("-")[1] + report.getString("terrestrial_date").split("-")[2]),
                //report.getDouble("abs_humidity")
                0
        );
    }//End of getDetails method

    /**
     * Return the average of the two parameters
     * @param tempMin The minimum temperature
     * @param tempMax The maximum temperature
     * @return The average of the max and min temperatures
     */
    private double getAvg(double tempMin, double tempMax)
    {
        return ((tempMin + tempMax) / 2);
    }//End of getAvg method
}//End of MarsTermParser class
