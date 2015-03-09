package team21;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class obtains the JSONObjects from the website and store it into LongTermData
 *
 * @author: Gurpreet
 */
public class LongTermParser extends Parser
{
    ///// Attributes /////
    private String locationName; // location
    private LongTermData[] data; // an array of forecasts (each element represent the forecast for that day)
    private Configuration config; // user preference

    /**
     * Constructor
     *
     * @param locationName A formatted (city, province) string
     */
    public LongTermParser(String locationName)
    {
        this.locationName = locationName;
        config = new Configuration();
        config.load();
        data = new LongTermData[5];
    }//End of constructor

    /**
     * use the preferred units to get specific versions of data in the given units and extract the JSONObject
     *
     * @return a LongTerm object
     */
    @Override
    protected TermObject parse()
    {
        String url = "";

        switch (config.getDegrees())
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

                    } else
                    {
                        getArray(JSONData);
                    }
                } catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        });

        return new LongTerm(data);
    }//End of parse method

    /**
     * Extracting groups of data from the JSONObject. (there are 5 sets of data -- forecasts for the next 5 days)
     *
     * @param jsonData
     */
    private void getArray(String jsonData)
    {
        JSONObject forecast = new JSONObject(jsonData);

        JSONArray arr = forecast.getJSONArray("list");

        for (int i = 0; i < 5; i++)
        {
            data[i] = getDetails(arr.getJSONObject(i).toString());
        }
    }

    /**
     * * Extract Specific data from each group and storing to LongTermData
     *
     * @param rawJSONData
     * @return LongTermData Object
     */
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
