import java.util.ArrayList;

public class ProgramController {
    // Team member C:
        // create the two methods that everyone else is going to be using
    public static ArrayList<String> listFiles(){
        ArrayList<String> files = new ArrayList<>();
        files.add("C:\\Users\\"+System.getProperty("user.name"));
        return files;
    }
}
