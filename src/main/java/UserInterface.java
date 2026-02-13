import java.io.FileNotFoundException;
import java.io.IOException;

public class UserInterface {

    public void input(String[] args) {
        if(args.length == 0)
             displayFiles();
        else if(args.length == 1)
            displayText(args[0], null); // display file with default key
        else if(args.length == 2)
            displayText(args[0], args[1]); // display file with given key
        else
            displayError("Error: Too many arguments. Use 2 arguments at most.");
    }

    public void displayFiles() {
        // use listFiles method in ProgramController
        try {
            for(String file : ProgramController.listFiles()) {
                System.out.println(file);
            }

            //System.out.println(ProgramController.listFiles().toString()); // list the numbered files available to display
        }
        catch(IOException e) {
            displayError(e.getMessage());
        }
    }

    public void displayText(String fileNumber, String key) {
        try {
            // use getContent in ProgramController and print the contents
            System.out.println(ProgramController.getContent(fileNumber, key));
        }
        catch(IllegalArgumentException e) {
            displayError("Error: File number must be a 2 digit integer.");
        }
        catch(FileNotFoundException e) {
            displayError("Error: File number" + fileNumber + " is not found.");
        }
        catch(IOException e) {
            displayError(e.getMessage());
        }
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
