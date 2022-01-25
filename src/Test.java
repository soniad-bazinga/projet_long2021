 import java.awt.Color;

 public class Test {
    static void testRangeBase(){
        String toTest = "";
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                for (int k=0; k<3; k++){
                    toTest = toTest + Writer.rangeBase(i*126,j*126,k*126) + " ";
                }
            }
        }
        String expected = "D# A A C A# A C B A# E G G# C# F# G# C# B A# E F F# D# F G D D D ";
        boolean test = toTest.equals(expected);
        System.out.println("Test RangeBase : "+test);
        System.out.println("expected : " + expected +"\nresult : "+toTest);
    }
 }