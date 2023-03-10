package chopin.jsonconverter;

import static chopin.jsonconverter.JsonConverter.getColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class JsonConverterTest {
     String FILE = "src/main/java/chopin/resources/test.json";

    Logger log = Logger.getLogger("Log");

    @Test
    public void testGetColors(){
        Map<String, Float> expected = new HashMap<String, Float>();
        expected.put( "#8a6c91", (float) 0.10);
        expected.put( "#f46c91", (float) 0.15);
        expected.put( "#f48f91", (float) 0.20);
        expected.put( "#f4f091", (float) 0.05);
        expected.put( "#f4f042", (float) 0.30);
        expected.put( "#644c42", (float) 0.03);
        expected.put( "#644cca", (float) 0.02);
        expected.put( "#58d2ca", (float) 0.07);
        expected.put( "#58d223", (float) 0.09);
        expected.put( "#58d2ff", (float) 0.08);
        expected.put( "#d8d2ff", (float) 0.01);
        expected.put( "#d85e09", (float) 0.03);
        expected.put( "#d85ea2", (float) 0.07);
        
        Map<String, Float> toTest = getColor(FILE);
        for (Map.Entry<String, Float> e : toTest.entrySet()) {
            if (log.isLoggable(Level.INFO)){
                log.info("Key : " + e.getKey());
                log.info("Expected Value : " + expected.get(e.getKey()));
                log.info("Real Value : " + e.getValue());
            }
            assertEquals(expected.get(e.getKey()), e.getValue(), 
            "Echec de la convertion JSON : certaines paires ne sont pas les mêmes");
        }

        assertEquals(expected.size(), toTest.size(), 
        "Echec de la convertion JSON : la taille n'est pas la même");
    }
}