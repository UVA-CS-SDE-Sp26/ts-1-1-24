import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class UserInterfaceTest {

    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface();
    }

    @Test
    public void inputNoArgs() {
        String args[] = new String[0]; // empty array
        assertDoesNotThrow(() -> ui.input(args));
    }

    @Test
    public void inputOneArg() {
        String[] args = {"01"};
        assertDoesNotThrow(() -> ui.input(args));
    }

    @Test
    public void inputTwoArgs() {
        String[] args = {"01", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"};
        assertDoesNotThrow(() -> ui.input(args));
    }

    @Test
    public void inputTooManyArgs() {
        String[] args = {"01", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", "thirdargument"};
        assertDoesNotThrow(() -> ui.input(args));
    }

    @Test
    public void testInvalidArguments() {
        String [] args = {"notanumber"}; // first argument is not a number
        assertDoesNotThrow(() -> ui.input(args));
    }
}