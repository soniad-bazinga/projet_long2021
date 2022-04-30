package chopin;

import chopin.jsonconverter.JsonConverter;

/**
 * Hello world!
 *
 */
public final class App {

    private App() {}
    public static void main( String[] args )
    {
        String file = "src/main/java/chopin/resources/test.json";

        JsonConverter.getColor(file);
    }
}
