package chopin.filewriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



/**
 * JUnit test class for FileWriterImplementation.
 */
class FileWriterImplementationTest {
    /**
     * filename for testFile.
     */
    static final String testFilesName = "test.txt";

    /**
     * content for testFile.
     */
    static final String testFileContent =
            "TEST LINE 1"
                +
            "TEST LINE 2"
                +
            "TEST LINE 3";
            

    /**
     * content empty for empty File.
     */
    static final String empty = "";
    /**
     * filename for emptyFile.
     */
    static final String emptyFileName = "empty.html";

    /**
     * path for test files.
     */
    static final String testFilesPath = "build/";

    /**
     * Log4J Logger.
     */
    private static final Logger logger = Logger.getLogger("Log");

    /**
     * Writing testFile on disk.
     */
    @BeforeAll
    public static void initTestFile() {
        String file = testFilesPath + testFilesName;
        Path path = Paths.get(file);

        logger.info("initTestFile(): attemps to write test file " + file);

        try {
            Files.deleteIfExists(path);
            FileWriter.write(file,testFileContent);
        } catch (IOException e) {
            logger.info("initTestFile(): exception raised while writing " + file);
            fail(e);
        }
    }

    /**
     * write content must equal testFileContent.
     */
    @Test
    void writeContentEqualsReadContent() {
        String file = testFilesPath + testFilesName;
        Path path = Paths.get(file);

        logger.info("writeContentEqualsFileContent(): attempt to write test file " + file);

        try {
            String content = Files.readString(path);
            assertEquals(testFileContent,content,"write content did not read content");
        } catch (IOException e) {
            logger.info("writeContentEqualsReadContent"
                    + " exception raised while writing " + file);
            fail(e);
        }
    }

    /**
     * Removing testFile from disk.
     */
    @AfterAll
    public static void cleaningTestFile() {
        String file = testFilesPath + testFilesName;
        Path path = Paths.get(file);

        logger.info("cleaningTestFile(): attempt to clear test file " + file);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            logger.info("cleaningTestfile(): exception raised while cleaning " + file);
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

        logger.info("initEmpty(): attempt to write test files " + file);

        try {
            Files.deleteIfExists(path);
            FileWriter.write(file,empty);
        } catch (IOException e) {
            logger.info("initEmptyFile():exception raised while writing " + file);
            fail(e);
        }
    }

    @Test
    void emptyFileWriteContentEqualsReadContent() {
        String file = testFilesPath + emptyFileName;
        Path path = Paths.get(file);

        logger.info("emptyFileWriteContenteEqualsReadContent(): attempt to write test file " + file);

        try {
            String content = Files.readString(path);
            assertEquals(empty,content,"write content did not match read content");
        } catch (IOException e) {
            logger.info("emptyFileWriteContentEqualsReadContent():"
                + " exception raised while writing " + file);
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

        logger.info("cleanTestFile(): attempt to clear test file " + file);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            logger.info("cleanTestfile(): exception raised while cleaning " + file);
            fail(e);
        }
    }


}