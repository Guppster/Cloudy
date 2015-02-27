
import com.squareup.okhttp.OkHttpClient;
import org.json.JSONObject;

/**
 * @author: Gurpreet
 */
public abstract class Parser
{
    protected OkHttpClient client;
    protected JSONObject dataRaw;
    protected BaseData dataFormatted;
    protected final String baseURL = "http://api.openweathermap.org/data/2.5/";
    protected final String currentModifier = "weather?q=";
    protected final String shortModifier = "forecast?q=";
    protected final String longModifier = "daily?q=";

    protected Parser()
    {
        client = new OkHttpClient();
        dataRaw = new JSONObject();
    }
    
    protected abstract TermObject parse();

    protected abstract BaseData getDetails(String rawJSONData);

}//End of parser abstract class

/*
                Template for fetching all types of data 
                
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
 */