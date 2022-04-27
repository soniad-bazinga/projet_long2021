package chopin.jsonreader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public final class JsonReader {

    private JsonReader () {}

    public static String getMainColor (String file) {
        JSONObject content = parseFile(file);
        return (String) content.get("mainColor");
    }

    public static Map<String, Float> getColor (String file) {
        JSONObject content = parseFile(file);
        Map<String, Float> colorMap = new HashMap<String, Float>();
        JSONArray colorArray = (JSONArray) content.get("ColorList");
        List<JSONArray> colorList = convertArray(colorArray);

        colorList.forEach(pair -> 
            colorMap.put(
                (String) ((JSONArray) pair).get(0), 
                Float.parseFloat((String) ((JSONArray) pair).get(1))
            )
        );

        return colorMap;
    }

    private static JSONObject parseFile(String file) 
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(file))
        {
            //Read JSON file
            Object parsed = jsonParser.parse(reader);
            return (JSONObject) ((JSONObject) parsed).get("data"); 
 
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<JSONArray> convertArray (JSONArray array){
        List<JSONArray> arrayList = new ArrayList<JSONArray>();
        if (array != null) { 
            for (int i=0; i < array.size(); i++){ 
                arrayList.add((JSONArray) array.get(i));
            } 
        } 
        return arrayList;
    }

}