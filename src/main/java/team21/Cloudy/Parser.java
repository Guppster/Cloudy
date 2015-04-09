package team21.Cloudy;

import com.squareup.okhttp.OkHttpClient;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Apstract class used to obtain Json objects from the website and decodes its content
 *
 * @author: Gurpreet
 */
public abstract class Parser
{
    /**** Fields ****/
    protected OkHttpClient client;
    protected JSONObject dataRaw;
    protected final String baseURL = "http://api.openweathermap.org/data/2.5/";
    protected final String currentModifier = "weather?q=";
    protected final String shortModifier = "forecast?q=";
    protected final String longModifier = "forecast/daily?q=";
    protected final String imperialModifier = "&units=imperial";
    protected final String metricModifier = "&units=metric";
    protected final String APIKEY = "&APPID=f71bff5f1401ac6b892251d425cea917";

    /**
     *Constructor
     */
    protected Parser()
    {
        client = new OkHttpClient();
        dataRaw = new JSONObject();
    }

    /**
     * Parses the data into an TermObject object
     * @return TermObject object
     * @throws IOException When the object cannot be fully parsed this exception is thrown
     */
    protected abstract TermObject parse() throws IOException;

    /**
     * @param rawJSONData The data retrieved from the API call
     * @return base data object
     */
    protected abstract BaseData getDetails(String rawJSONData);

}//End of parser abstract class
