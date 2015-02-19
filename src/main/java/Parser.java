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
    
    protected Parser()
    {
        client = new OkHttpClient();
        dataRaw = new JSONObject();
    }
    
    protected abstract TermObject parse();
    
    protected abstract void fetchForecast(String str);

}//End of parser abstract class
