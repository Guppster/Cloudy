
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
public class LongTermParser extends Parser 
{
    private String locationName;
    private LongTermData[] data;
    private Configuration config;

    /**
     * Constructor
     * @param locationName A formatted (city, province) string
     */
    public LongTermParser(String locationName)
    {
        this.locationName = locationName;
    }//End of constructor

    @Override
    protected TermObject parse()
    {
        String url = "";

        switch(config.getDegrees())
        {
            case IMPERIAL:
            {
                url = baseURL + longModifier + locationName + imperialModifier;
                break;
            }
            case METRIC:
            {
                url = baseURL + longModifier + locationName + metricModifier;
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

        return new LongTerm(data);
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
    protected LongTermData getDetails(String rawJSONData)
    {
        JSONObject forecast = new JSONObject(rawJSONData);
        JSONObject weather = forecast.getJSONArray("weather").getJSONObject(0);

        JSONObject temp = forecast.getJSONObject("temp");

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
                weather.getString("description")
        );
    }//End of getDetails method
}
