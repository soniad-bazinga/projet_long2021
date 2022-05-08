package chopin.filewriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

/**
 * write string content in file text to disk.
 */
public final class FileWriter {

    private FileWriter(){};
    /**
     * log4J.
     */
    private static final Logger log = Logger.getLogger("Log");

    /**
     * write string content in file text to disk.
     *
     * @param file file to write on disk.
     * @param content content to write on file.
     * @throws IOException if an error happened while writing file.
     */
    public static void write(String file, String content) throws IOException {
        log.info("write(): " + file + " attempt to write file");
        try {
            Files.writeString(Path.of(file), content,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            log.info("write() : " + file + " was successfully write ");
        } catch (IOException e) {
            log.info("write(): exception raised while writing " + file);
            throw e;
        }
    }
}