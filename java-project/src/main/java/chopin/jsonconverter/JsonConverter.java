package chopin.jsonconverter;

import chopin.filereader.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;
 
public final class JsonConverter {

    /**
     * Logger.
     */
    static Logger log = Logger.getLogger("Log");

    private JsonConverter () {}

    /**
     * Return list color from JSONFile file
     * @param file file
     * @return Map of (color, frequency)
     */
    public static Map<String, Float> getColor (String file) {
        Logger.getLogger("Log").setLevel(Level.ALL);
        JSONObject content = parseFile(file);
        Map<String, Float> colorMap = new HashMap<String, Float>();
        JSONArray array = content.getJSONArray("colorList");

        array.forEach( pair -> 
            colorMap.put(
                ((JSONObject) pair).getString("color").toString(), 
                Double
                .valueOf(((JSONObject) pair)
                .getDouble("freq")).floatValue()
            )
        );
        if (log.isLoggable(Level.INFO)){
            log.info(String.valueOf(colorMap));
        }
        return colorMap;
    }

    /**
     * Convert a file into JSONObject
     * @param file path file
     * @return JSONObject corresponding
     */    
    private static JSONObject parseFile(String file) 
    {
        String fileContent;
        try {
            fileContent = FileReader.read(file);
            JSONObject content = new JSONObject(fileContent);
            JSONObject ret = content.getJSONObject("data");
            if (log.isLoggable(Level.INFO)){
                log.info(String.valueOf(ret));
            }
            return ret; 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}