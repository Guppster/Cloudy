package team21.Cloudy;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
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
        String url = "";

        switch (config.getDegrees())
        {
            case IMPERIAL:
            {
                url = baseURL + shortModifier + locationName + imperialModifier;
                break;
            }
            case METRIC:
            {
                url = baseURL + shortModifier + locationName + metricModifier;
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
                        System.out.println("ERROR: REQUEST UNSUCCESSFUL!");
                    }
                    else
                    {
                        getArray(JSONData);
                    }
                } catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

        return new ShortTerm(data);
    }//End of parse method

    /**
     * Extracting groups of data from the JSONObject. (there are 8 sets of data -- 3 hour forecasts for 24 hours)
     *
     * @param jsonData
     */
    private void getArray(String jsonData)
    {
        JSONObject forecast = new JSONObject(jsonData);

        JSONArray arr = forecast.getJSONArray("list");

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
        JSONObject forecast = new JSONObject(rawJSONData);
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        JSONObject main = forecast.getJSONObject("main");
        JSONObject clouds = forecast.getJSONObject("clouds");
        JSONObject wind = forecast.getJSONObject("wind");
        //JSONObject snow = forecast.getJSONObject("snow");


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
