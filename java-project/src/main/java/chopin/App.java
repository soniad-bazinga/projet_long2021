package chopin;

import chopin.jsonreader.JsonReader;

/**
 * Hello world!
 *
 */
public final class App {

    private App() {}
    public static void main( String[] args )
    {
        String file = "src/main/java/chopin/resources/test.json";

        JsonReader.getColor(file);
    }
}
