
 public class Test {
    static boolean testRangeBase(){
        String toTest = "";
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                for (int k=0; k<3; k++){
                    toTest = toTest + Convert.notes[Convert.rangeBase(i*126,j*126,k*126)] + " ";
                }
            }
        }
        String expected = "D# A A C A# A C B A# E G G# C# F# G# C# B A# E F F# D# F G D D D ";
        return toTest.equals(expected);
        
    }

    //TODO
    static void testSetRangeBase(){
        
    }
 }