// Helen Ayalew (qer4de)

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramController {
    public static List<String> listFiles() throws IOException {
        // Gets the available files from File Handler
        List<String> files = FileHandler.getAvailableFiles();
        // Creating a new list to store the files once they have been ordered
        List<String> numberedFiles = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            // Creates the correct file numbers
            String number = String.format("%02d", i + 1);
            // Displays the file number with its corresponding file
            String entry = number + " " + files.get(i);
            // Adds each entry (file number + corresponding file) to the new list
            numberedFiles.add(entry);
        }

        // returns the files with their corresponding file number
        return numberedFiles;
    }

    public static String getContent(String filename, String key) throws IOException {
        // Gets the available files from File Handler
        String fileName = getString(filename);
        // Getting the content within the file
        String content = FileHandler.readFile(fileName);

        // Checks to see if the cipher is valid (not null)
        if (key != null) {
                // Valid cipher + custom key
                Cipher.setCommandLineCipher(key);
        }

        // Gets the content deciphered text
        content = Cipher.decipherText(content);

        // returning the content from the file
        return content;
    }

    private static String getString(String filename) throws IOException {
        // Getting the files from file handler
        List<String> files = FileHandler.getAvailableFiles();

        // if the file is empty
        if (files.isEmpty()) {
            throw new IllegalStateException("The file does not exist");
        }

        // Converts the file number from a string into an int
        int index = Integer.parseInt(filename);

        // Checks if the index is less than 1 or greater than the size of the file
        if (index < 1 || index > files.size()) {
            throw new IllegalStateException("The file number is invalid");
        }

        // Storing the file name from the list
        String fileName = files.get(index - 1);
        return fileName;
    }
}
