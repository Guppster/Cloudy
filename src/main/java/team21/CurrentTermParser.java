package team21;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author: Gurpreet
 */
public class CurrentTermParser extends Parser
{
    ///// Attributes /////
    private String locationName; // location
    private CurrentTermData[] data; // an array of forecast
    private Configuration config; // User preference

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public CurrentTermParser(String locationName)
    {
        this.locationName = locationName;
        config.load();
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return CurrentTerm object
     */
    @Override
    protected TermObject parse()
    {
        String url = "";

        switch (config.getDegrees())
        {
            case IMPERIAL:
            {
                url = baseURL + currentModifier + locationName + imperialModifier;
                break;
            }
            case METRIC:
            {
                url = baseURL + currentModifier + locationName + metricModifier;
            }
        }

        Request request = new Request.Builder().url(url).build();

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

                    } else
                    {
                        data[0] = getDetails(JSONData);
                    }
                } catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

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
        JSONObject forecast = new JSONObject(rawJSONData);

        JSONObject sys = forecast.getJSONObject("sys");
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);
        JSONObject main = forecast.getJSONObject("main");
        JSONObject wind = forecast.getJSONObject("wind");
        JSONObject rain = forecast.getJSONObject("rain");

        return new CurrentTermData(
                rain.getInt("3h"),
                sys.getInt("sunrise"),
                sys.getInt("sunset"),
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
}//End of CurrentTermParser class
