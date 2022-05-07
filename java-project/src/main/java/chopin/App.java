package chopin;

import java.util.logging.Logger;

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
        String file = "src/main/java/chopin/resources/test.json";

        log.info(Convert.convert(file));
    }
}
