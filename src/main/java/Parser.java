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
    protected final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";

    protected Parser()
    {
        client = new OkHttpClient();
        dataRaw = new JSONObject();
    }
    
    protected abstract TermObject parse();

    protected abstract BaseData getDetails(String rawJSONData);

}//End of parser abstract class
