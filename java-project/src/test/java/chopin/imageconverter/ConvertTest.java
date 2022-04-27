package chopin.imageconverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ConvertTest {

    @Test
    public void testRangeBase(){
        try{
            String toTest = "";
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    for (int k=0; k<3; k++){
                        toTest = toTest + Convert.notes[Convert.rangeBase(i*126,j*126,k*126)] + " ";
                    }
                }
            }
            String expected = "D# A A C A# A C B A# E G G# C# F# G# C# B A# E F F# D# F G D D D ";
            assertEquals(toTest, expected, "rangeBase failed");
        }catch(PatternNotExhaustive e){
            e.printStackTrace();         
        }
    }
}
