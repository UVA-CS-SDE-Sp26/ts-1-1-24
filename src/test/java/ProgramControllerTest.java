// Helen Ayalew (qer4de)

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProgramControllerTest {
    // listFiles() Tests:
    @Test
    // Test if file exists:
    public void fileExists() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            // Checks if the available files exist from the method
            when(FileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt", "file02.txt"));
            // Stores the value from the list
            List<String> result = ProgramController.listFiles();
            // Checks if the files in getAvailableFiles are the same as what is expected
            assertEquals(List.of("01 file01.txt", "02 file02.txt"), result);
        }
    }

    @Test
    // Test if file does not exist:
    public void fileDoesNotExist() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            // Checks if the available files do not exist from the method
            when(FileHandler.getAvailableFiles()).thenReturn(List.of());
            // Stores the value from the list
            List<String> files = ProgramController.listFiles();
            // Checks if the files in getAvailableFiles is actually empty
            assertTrue(files.isEmpty());
        }
    }

    // getContent() Tests:
    @Test
    public void validFileNumberAndDefaultKey() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            try (MockedStatic<Cipher> mockedStaticCipher = Mockito.mockStatic(Cipher.class)) {
                // Gets the available files
                when(FileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));
                // Reads the contents within the file to get the cipher
                when(FileHandler.readFile("file01.txt")).thenReturn("abc");
                // Takes the ciphered text and deciphers them
                when(Cipher.decipherText("abc")).thenReturn("def");

                // Checks if the result is equal to the deciphered text
                String result = ProgramController.getContent("1", null);
                assertEquals("def", result);
            }
        }
    }

    @Test
    void validFileNumberAndCustomKey() throws IOException {
        try (MockedStatic<Cipher> mockedStatic = Mockito.mockStatic(Cipher.class)) {
            // Stores the file with its key
            ProgramController.getContent("file01", "Key");
            // Checks if the file matches it's corresponding key
            mockedStatic.verify(() -> Cipher.setCommandLineCipher("Key"));
        }
    }

    @Test
    public void invalidFileNumberLessThanOne() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            // Checks that the available files (such as file01.txt)
            when(FileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));
            // Checks if the file number is less than 0 and the key is null
            assertThrows(IllegalStateException.class, () -> ProgramController.getContent("0", null));
        }
    }

    @Test
    public void invalidFileNumberGreaterThanSize() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            // Checks to see if the file number corresponds with the file
            when(FileHandler.getAvailableFiles()).thenReturn(List.of("file01"));
            // Checks if the file number is greater than the size and the key is null
            assertThrows(IllegalArgumentException.class, () -> ProgramController.getContent("file01", null));
        }
    }

    @Test
    public void invalidFileNumberNotANumber() throws IOException {
        // Checks if the file number inputted is something other than a number
        assertThrows(IllegalArgumentException.class, () -> ProgramController.getContent("abc", null));
    }

    @Test
    public void listFilesNotEmpty() throws IOException {
        try (MockedStatic<FileHandler> mockedStatic = Mockito.mockStatic(FileHandler.class)) {
            // Checks if the available files are not empty
            when(FileHandler.getAvailableFiles()).thenReturn(List.of("file01.txt"));
            // Stores the available files
            List<String> notEmpty = ProgramController.listFiles();
            // Checks if it's not empty
            assertFalse(notEmpty.isEmpty());
        }
    }
}
