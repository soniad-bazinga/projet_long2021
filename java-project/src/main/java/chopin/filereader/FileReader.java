package chopin.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FileReader {
    
    private FileReader() {};

    static Logger log = Logger.getLogger("Log");

    public static String read(String file) throws IOException {
        log.info("read(): attempt to read file " + file);
        try {
            String content = Files.readString(Path.of(file));
            log.info("read(): " + file + " was successfully read");
            return content;
        } catch (IOException e) {
            log.info("read(): exception raised while reading " + file + e);
            throw e;
        }       
    }
}
