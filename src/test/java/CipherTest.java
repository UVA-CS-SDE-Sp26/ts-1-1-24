import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {
    @Test
    public void testValidateCipher() {
        assertFalse(Cipher.validateCipher("Abc"));
        assertFalse(Cipher.validateCipher("qwertyuioplkjhgfdsazxcvbnm1234567890"));
        assertFalse(Cipher.validateCipher("zaq12wsxcde34rfvbgt56yhnmju78iki9olp0_+"));
        assertTrue(Cipher.validateCipher("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"));
        assertFalse(Cipher.validateCipher("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM+"));
        assertFalse(Cipher.validateCipher("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBN"));
    }

    @Test
    public void testGetDecipheredCharacters() throws IOException {
        assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", Cipher.getDecipheredCharacters());
    }

    @Test
    public void testGetCipheredCharacters() throws IOException {
        assertEquals("bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a", Cipher.getCipheredCharacters());
    }

    @Test
    public void testDecipherChar() throws IOException {
        assertEquals('a', Cipher.decipherChar('b'));
        assertEquals('.', Cipher.decipherChar('.'));
        assertEquals(' ', Cipher.decipherChar(' '));
        assertEquals('Z', Cipher.decipherChar('1'));
        assertEquals('F', Cipher.decipherChar('G'));
    }

    @Test
    public void testDecipherText() throws IOException {
        assertEquals("0ab zAB wxy WXY. Z123!", Cipher.decipherText("abc ABC xyz XYZ. 1234!"));
        assertEquals("Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with improved commercial software.", Cipher.decipherText("Dbsojwpsf, mbufs sfobnfe EDT2aaa, xbt b tztufn jnqmfnfoufe cz uif Gfefsbm Cvsfbv pg Jowftujhbujpo (GCJ) uibu xbt eftjhofe up npojups fnbjm boe fmfduspojd dpnnvojdbujpot. Ju vtfe b dvtupnjAbcmf qbdlfu tojggfs uibu dpvme npojups bmm pg b ubshfu vtfs't Joufsofu usbggjd. Dbsojwpsf xbt jnqmfnfoufe jo Pdupcfs 2008. Cz 3aa6 ju ibe cffo sfqmbdfe xjui jnqspwfe dpnnfsdjbm tpguxbsf."));
    }
}
