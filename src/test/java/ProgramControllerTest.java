import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramControllerTest {
    @Test
    public void listFilesNotEmpty() {
        ArrayList<String> notEmpty = ProgramController.listFiles();
        assertFalse(notEmpty.isEmpty());
    }
}
