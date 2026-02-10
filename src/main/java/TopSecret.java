/**
 * Commmand Line Utility
 * part a --> command line interface
 * checking the number of arguments the user uses
 */
public class TopSecret {
    public static void main(String[] args) {
        ProgramController user = new ProgramController();

        //if there are no arguments, list the numbered files available to display
        if (args.length == 0){
            user.listFiles();
        }
        //if there is one argument, open the file at that index (use the default key)
        else if (args.length == 1){
            user.displayFile(args[0],null);
        }
        //if two args, display the file at the index of the first argument and the key provided by the second argument
        else if (args.length == 2){
            user.displayFile(args[0],args[1]);
        }
        //if an invalid argument is used by user gives an error message and directions
        else{
            System.out.println("Invalid number of arguments");
            System.out.println("Correct Format: java topsecret [fileNumber] [optionalKey]");
        }

    }
}
