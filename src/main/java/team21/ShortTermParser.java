package team21;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author: Gurpreet
 */
public class ShortTermParser extends Parser
{
    private String locationName;
    private ShortTermData[] data;
    private Configuration config;

    /**
     * Constructor
     * @param locationName A formatted (city, province) string
     */
    public ShortTermParser(String locationName)
    {
        this.locationName = locationName;
        config.load();
    }//End of constructor

    @Override
    protected TermObject parse()
    {
        String url = "";
        
        switch(config.getDegrees())
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

                    }
                    else
                    {
                        getArray(JSONData);
                    }
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

        return new ShortTerm(data);
    }//End of parse method

    private void getArray(String jsonData)
    {
        JSONObject forecast = new JSONObject(jsonData);

        JSONArray arr = forecast.getJSONArray("list");

        for (int i = 0; i < arr.length(); i++)
        {
            data[i] = getDetails(arr.getJSONObject(i).toString());
        }
    }

    @Override
    protected ShortTermData getDetails(String rawJSONData)
    {
        JSONObject forecast = new JSONObject(rawJSONData);
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        JSONObject main = forecast.getJSONObject("main");
        JSONObject clouds = forecast.getJSONObject("clouds");
        JSONObject wind = forecast.getJSONObject("wind");
        JSONObject snow = forecast.getJSONObject("snow");
        

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
