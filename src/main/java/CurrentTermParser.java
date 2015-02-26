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
    private String locationName;
    private CurrentTermData[] data;

    //Constructor
    public CurrentTermParser(String locationName)
    {
        this.locationName = locationName;
    }//End of constructor
    
    @Override
    protected TermObject parse()
    {
        Request request = new Request.Builder().url(baseURL + currentModifier + locationName).build();

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
        
        return new CurrentTerm(data);
    }//End of parse method

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
