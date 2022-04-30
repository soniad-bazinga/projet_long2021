package chopin.filereader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Junit test class for FileReader.
 */
class FileReaderTest {

    /**
     * filename for testFile.
     */
    static final String testFileName = "test.txt";

    /**
     * filename for emptyFile.
     */
    static final String emptyFileName = "empty.md";

    /**
     * path for test files.
     */
    static final String testFilesPath = "build/";
    /**
     * Log4J Logger.
     */
    private static final Logger log = Logger.getLogger("Log");

    /**
     * Attempt to read a missing file.
     */
    @Test
    void readingMissingFileThrowsException() {
        assertThrows(IOException.class,
            () -> FileReader.read("missingFile.md"),
            "attempt to read missing file did raise IOException");
    }

    /**
     * Writing testFile on disk.
     */
    @BeforeAll
    public static void initTestFile() {
        String file = testFilesPath + testFileName;
        Path path = Paths.get(file);

        if(log.isLoggable(Level.INFO)) {
            log.info("initTestFile(): attempt to write test file " + file);
}

        try {
            Files.deleteIfExists(path);
            Files.writeString(path, testFileContent, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("initTestFile(): exception raised while writing " + file + " : " + e);
            }
            fail(e);
        }
    }

    /**
     * read content must equal file content.
     */
    @Test
    void readContentEqualsFileContent() {
        String file = testFilesPath + testFileName;

        if(log.isLoggable(Level.INFO)) {
            log.info("readContentEqualsFileContent(): attempt to read test file " + file);
        }

        try {
            String content = FileReader.read(file);
            assertEquals(testFileContent, content, "read content did not match file content");
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("readContentEqualsFileContent():"
                    + " exception raised while reading " + file + " : " + e);
            }
            fail(e);
        }
    }

    /**
     * Removing testFile from disk.
     */
    @AfterAll
    public static void cleaningTestFile() {
        String file = testFilesPath + testFileName;
        Path path = Paths.get(file);

        if(log.isLoggable(Level.INFO)) {
            log.info("cleaningTestFile(): attempt to clear test file " + file);
}

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("cleaningTestFile(): exception raised while cleaning " + file + " : " + e);
            }
            fail(e);
        }
    }

    /**
     * Writing emptyFile on disk.
     */
    @BeforeAll
    public static void initEmptyFile() {
        String file = testFilesPath + emptyFileName; 
        Path path = Paths.get(file);

        if(log.isLoggable(Level.INFO)) {
            log.info("initEmptyFile(): attempt to write test file " + file);
}

        try {
            Files.deleteIfExists(path);
            Files.writeString(path, "", StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("initEmptyFile(): exception raised while writing " + file + " : " + e);
            }
            fail(e);
        }
    }
    
    /**
     * empty file read content must equal file content.
     */
    @Test
    void emptyFileReadContentEqualsEmptyString() {
        String file = testFilesPath + emptyFileName;

        if(log.isLoggable(Level.INFO)) {
            log.info("emptyFileReadContentEqualsFileContent(): attempt to read test file " + file);
}

        try {
            String content = FileReader.read(file);
            assertEquals("", content, "read content did not match file content");
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("emptyFileReadContentEqualsFileContent():"
                    + " exception raised while reading " + file + " : " + e);
            }
            fail(e);
        }
    }

    /**
     * Removing emptyFile from disk.
     */
    @AfterAll
    public static void cleanTestFile() {
        String file = testFilesPath + emptyFileName;
        Path path = Paths.get(file);

        if(log.isLoggable(Level.INFO)) {
            log.info("cleanTestFile(): attempt to clear test file " + file);
}

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            if(log.isLoggable(Level.INFO)) {
                log.info("cleanTestFile(): exception raised while cleaning " + file + " : " + e);
            }
            fail(e);
        }
    }

    /**
     * content for testFile.
     */
    static final String testFileContent = """
            # Heading level 1
            ## Heading level 2
            ### Heading level 3
            #### Heading level 4
            ##### Heading level 5
            ###### Heading level 6"
            """;
}
