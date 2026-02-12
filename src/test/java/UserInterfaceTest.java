import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface();
    }

    @Test
    void inputNoArgs() {
        String args[] = new String[0]; // no args
        assertDoesNotThrow(() -> ui.input(args));
    }

    @Test
    void displayFiles() {

    }

    @Test
    void displayText() {
    }

    @Test
    void displayError() {
    }
}