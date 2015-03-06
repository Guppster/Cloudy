package team21;

import com.squareup.okhttp.OkHttpClient;
import org.json.JSONObject;

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
    protected BaseData dataFormatted;
    protected final String baseURL = "http://api.openweathermap.org/data/2.5/";
    protected final String currentModifier = "weather?q=";
    protected final String shortModifier = "forecast?q=";
    protected final String longModifier = "daily?q=";
    protected final String imperialModifier = "&units=imperial";
    protected final String metricModifier = "&units=metric";

    /**
     *
     */
    protected Parser()
    {
        client = new OkHttpClient();
        dataRaw = new JSONObject();
    }

    /**
     *
     * @return
     */
    protected abstract TermObject parse();

    /**
     *
     * @param rawJSONData
     * @return
     */
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