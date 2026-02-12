//Annika Kumar, role B, FileHandler class
//import statements
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class FileHandler {
    //create variable and store file path that points to "data"
    private static final Path dataFileDirectory = Paths.get("data");
    //method returns available files in directory
    public static List<String> getAvailableFiles() throws IOException{
        //first part returns true if folder exists, second returns true if path is folder
        //otherwise throw exception
        if(!Files.exists(dataFileDirectory) || !Files.isDirectory(dataFileDirectory)){
            throw new IOException("Directory does not exist or is not a directory");
        }
        //openstax stream documentation: https://stackoverflow.com/questions/58331795/stream-and-filter-a-sortedmap
        try (Stream<Path> filePaths = Files.list(dataFileDirectory)){
            return filePaths
                    .filter(Files :: isRegularFile)
                    .map(filePath -> filePath.getFileName().toString())
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    //returns file content as strings
    public static String readFile(String fileName) throws IOException{
        //creates full path to file, openstax
        Path filePath = dataFileDirectory.resolve(fileName);
        //checks if it exists, checks if it is a regular file, otherwise exception
        if(!Files.exists(filePath) || !Files.isRegularFile(filePath)){
            throw new IOException("File not found");
        }
        //returns string
        return Files.readString(filePath);
    }
}

