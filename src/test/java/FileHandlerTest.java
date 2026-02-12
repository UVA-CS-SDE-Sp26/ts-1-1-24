//Annika Kumar, Member B, FileHandlerTest class
//import statements
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    //Test 1
    @Test
    public void getAvailableFilesAndReturn() throws IOException {
        //make new instance of FileHandler
        FileHandler fileHandler = new FileHandler();
        //call method to get files from data folder
        List<String> files = fileHandler.getAvailableFiles();
        //ensure files are not null, not empty, and contain carnivore.txt
        assertNotNull(files);
        assertFalse(files.isEmpty());
        assertTrue(files.contains("carnivore.txt"));
    }

    //Test 2
    @Test
    public void readFilesAndReturnContent() throws IOException {
        //new instance
        FileHandler fileHandler = new FileHandler();
        //test that readFile actually reads a file
        String fileContent = fileHandler.readFile("carnivore.txt");
        //assert that the file content in not null and not empty
        assertNotNull(fileContent);
        assertFalse(fileContent.isEmpty());
    }

    //Test 3
    @Test
    public void checkMissingFile() throws IOException {
        //new instance
        FileHandler fileHandler = new FileHandler();
        //checks that a nonexistent file throws an exception
        //lambda used to pass code that throws exception
        assertThrows(IOException.class, () -> {
            fileHandler.readFile("sample.txt");
        });
    }

    //Test 4
    @Test
    public void CheckDirectoryExists() throws IOException {
        //new instance
        FileHandler fileHandler = new FileHandler();
        //asserts that an exception is not thrown when method is run
        assertDoesNotThrow(() -> {
            fileHandler.getAvailableFiles();
        });
    }
}

