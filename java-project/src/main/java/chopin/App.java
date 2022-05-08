package chopin;

import java.io.IOException;
import java.util.logging.Logger;

import chopin.filewriter.FileWriter;
import chopin.imageconverter.Convert;

/**
 * Hello world!
 *
 */
public final class App {

    private App() {}

    /**
     * Logger.
     */
    static Logger log = Logger.getLogger("Log");
    
    public static void main( String[] args )
    {
        String file = "../colors.json";
        
        try {
            FileWriter.write("../conversion.txt", Convert.convert(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
