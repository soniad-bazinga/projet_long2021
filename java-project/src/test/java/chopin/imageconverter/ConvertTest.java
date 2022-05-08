package chopin.imageconverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ConvertTest {

    @Test
    public void convertTest(){
        
    }

    @Test
    public void rangeBaseTest(){
        Map<String, Float> colors = new HashMap<String, Float>();
        colors.put( "#8a6c91", (float) 0.10);
        colors.put( "#f46c91", (float) 0.15);
        colors.put( "#f48f91", (float) 0.20);
        colors.put( "#f4f091", (float) 0.05);
        colors.put( "#f4f042", (float) 0.30);
        colors.put( "#644c42", (float) 0.03);
        colors.put( "#644cca", (float) 0.02);
        colors.put( "#58d2ca", (float) 0.07);
        colors.put( "#58d223", (float) 0.09);
        colors.put( "#58d2ff", (float) 0.08);
        colors.put( "#d8d2ff", (float) 0.01);
        colors.put( "#d85e09", (float) 0.03);
        colors.put( "#d85ea2", (float) 0.07);

        Convert.setColors(colors);
        int range = Convert.rangeBase(Convert.mainColor());
        String toTest = Convert.notes[range];
        assertEquals(toTest, "D", "rangeBase failed");
    }

    @Test
    public void modeTest(){
        assertEquals(true, Convert.mode("#ffffff"));
        assertEquals(false, Convert.mode("#000000"));
        assertThrows(
            IllegalArgumentException.class, 
            () -> {
                Convert.mode("zkjfdz");
            });
    }

    @Test
    public void mainColorTest(){
        Map<String, Float> colors = new HashMap<String, Float>();
        colors.put( "#8a6c91", (float) 0.10);
        colors.put( "#f46c91", (float) 0.11);
        colors.put( "#f48f91", (float) 0.20);
        colors.put( "#f4f091", (float) 0.301);
        colors.put( "#f4f042", (float) 0.30);
        colors.put( "#644c42", (float) 0.03);
        colors.put( "#644cca", (float) 0.02);
        colors.put( "#58d2ca", (float) 0.07);
        colors.put( "#58d223", (float) 0.09);
        colors.put( "#58d2ff", (float) 0.08);
        colors.put( "#d8d2ff", (float) 0.01);
        colors.put( "#d85e09", (float) 0.03);
        colors.put( "#d85ea2", (float) 0.07);

        Convert.setColors(colors);
        assertEquals(Convert.mainColor(), "#f4f091", "mainColor failed");
        colors.remove("#f4f091");
        assertNotEquals(Convert.mainColor(), "#f4f091", "mainColor failed");
    }
}
