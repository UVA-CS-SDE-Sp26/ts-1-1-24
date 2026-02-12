import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProgramControllerTest {
    private Cipher mockCipher;
    private FileHandler mockFileHandler;
    private ProgramController controller;

    @BeforeEach
    public void setup() {
        mockCipher = mock(Cipher.class);
        mockFileHandler = mock(FileHandler.class);
        controller = new ProgramController(mockFileHandler, mockCipher);
    }

    // listFiles() Tests:
    @Test
    // Test if file exists:
    public void fileExists() throws IOException {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt", "file02.txt"));

        List<String> result = controller.listFiles();

        assertEquals(List.of("01 file01.txt", "02 file02.txt"), controller.listFiles());
    }

    @Test
    // Test if file does not exist:
    public void fileDoesNotExist() throws IOException {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of());

        List<String> files = controller.listFiles();

        assertTrue(files.isEmpty());
    }

    // getContent() Tests:
    @Test
    public void validFileNumberAndDefaultKey() throws IOException {
        // Gets the available files
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));
        // Reads the contents within the file to get the cipher
        when(mockFileHandler.readFile("file01.txt")).thenReturn("abc");
        // Takes the ciphered text and deciphers them
        when(mockCipher.decipherText("abc")).thenReturn("def");

        // Checks if the result is equal to the deciphered text
        String result = controller.getContent("1", null);
        assertEquals("def", result);
    }

    @Test
    void validFileNumberAndCustomKey() throws IOException {
        // Gets the available files
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file1.txt"));
        // Reads the contents within the file to get the cipher
        when(mockFileHandler.readFile("file01.txt")).thenReturn("abc");
        // Takes the ciphered text and deciphers them
        when(mockCipher.decipherText("abc", "Key")).thenReturn("def");

        // Checks if the result is equal to the deciphered text
        String result = controller.getContent("1", "Key");
        assertEquals("def", result);
    }

    @Test
    public void invalidFileNumberLessThanOne() throws IOException {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));

        assertThrows(IllegalArgumentException.class, () -> controller.getContent("0", null));
    }

    @Test
    public void invalidFileNumberGreaterThanSize() throws IOException {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));

        assertThrows(IllegalArgumentException.class, () -> controller.getContent("1", null));
    }

    @Test
    public void invalidFileNumberNotANumber() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> controller.getContent("abc", null));
    }

    @Test
    public void listFilesNotEmpty() throws IOException {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));
        List<String> notEmpty = controller.listFiles();
        assertFalse(notEmpty.isEmpty());
    }
}
