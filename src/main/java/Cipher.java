import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Cipher {
    private final static String cipherFilePath = "ciphers/key.txt";
    private final static File cipherFile = new File(cipherFilePath);

    private static String _commandLineCipher = "";

    public static void setCommandLineCipher(String commandLineCipher) {
        _commandLineCipher = commandLineCipher;
    }

    public static boolean validateCipher(String characters) {
        boolean containsCorrectCharacters = characters.replaceAll("\\w", "").isEmpty();
        boolean correctLength = characters.length() == (26+26+10);
        return containsCorrectCharacters && correctLength;
    }

    public static String getDecipheredCharacters() throws IOException {
        String decipheredCharacters = Files.readAllLines(cipherFile.toPath()).getFirst();
        if (!validateCipher(decipheredCharacters))
            throw new Error("Deciphered characters is missing/has too many characters");
        return decipheredCharacters;
    }

    public static String getCipheredCharacters() throws IOException {
        String cipheredCharacters = _commandLineCipher.isEmpty() ? Files.readAllLines(cipherFile.toPath()).get(1) : _commandLineCipher;
        if (!validateCipher(cipheredCharacters))
            throw new Error("Ciphered characters is missing/has too many characters");
        return cipheredCharacters;
    }

    public static char decipherChar(char cipheredChar) throws IOException {
        String decipheredCharacters = getDecipheredCharacters();
        String cipheredCharacters = getCipheredCharacters();

        int cipheredCharInFile = cipheredCharacters.indexOf(cipheredChar); // index of cipheredChar from cipheredCharacters if found, else -1

        if (cipheredCharInFile > -1)
            return decipheredCharacters.charAt(cipheredCharInFile);
        else
            return cipheredChar;
    }

    public static String decipherText(String cipheredText) throws IOException {
        String decipheredText = "";

        for (char cipheredChar : cipheredText.toCharArray()) {
            char decipheredChar = decipherChar(cipheredChar);
            decipheredText += decipheredChar;
        }
        return decipheredText;
    }
}
