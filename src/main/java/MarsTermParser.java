import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author: Gurpreet
 */
public class MarsTermParser extends Parser
{
    private String locationName;
    private MarsData[] data;
    private Configuration config;

    /**
     * *
     * @param locationName
     */
    //Constructor
    public MarsTermParser(String locationName)
    {
        this.locationName = locationName;
        config.load();
    }//End of constructor

    /**
     * * 
     * @return
     */
    @Override
    protected TermObject parse()
    {
        Request request = new Request.Builder().url("http://marsweather.ingenology.com/v1/latest/?format=json").build();

        Call call = client.newCall(request);

        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {

            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                try
                {
                    String JSONData = response.body().string();
                    System.out.println(JSONData);

                    if (!response.isSuccessful())
                    {

                    }
                    else
                    {
                        data[0] = getDetails(JSONData);
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

        return new MarsTerm(data);
    }

    /**
     * * 
     * @param rawJSONData
     * @return
     */
    @Override
    protected MarsData getDetails(String rawJSONData)
    {
        JSONObject forecast = new JSONObject(rawJSONData);
        JSONObject report = forecast.getJSONObject("report");

        return new MarsData(
                getConvertedTemp(report, report.getDouble("temp"), true),
                getConvertedTemp(report, report.getDouble("temp_min"), false),
                getConvertedTemp(report, report.getDouble("temp_max"), true),
                report.getDouble("wind_speed"),
                Integer.valueOf(report.getString("wind_direction")),
                report.getDouble("pressure"),
                report.getString("season"),
                report.getString("atmo_opacity")
        );
    }//End of getDetails method

    /**
     * * 
     * @param report
     * @param temp
     * @param minOrMax
     * @return
     */
    private double getConvertedTemp(JSONObject report, double temp, boolean minOrMax)
    {
        tempUnits units = config.getDegrees();
        
        switch(units)
        {
            case IMPERIAL:
                return minOrMax ? report.getDouble("min_temp_fahrenheit") : report.getDouble("max_temp_fahrenheit");
            case METRIC:
                return minOrMax ? report.getDouble("min_temp") : report.getDouble("max_temp");
            default:
                return 0;
        }
    }
}
