import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;    //Guessing I'll have to use this somewhere along the line
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    //                             shelf     book    key      value
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //get the shelf of the book we want
        // for (int i = 0; i < someJobs.size(); i++) {
        //check for empty
        //if (someJobs.size() == 0)  ----scratched this. Found the isEmpty

        if (someJobs.size() == 0) {
            //alert that no jobs matched searched location
            System.out.printf("No Results Were Found With Given Location");

        } else {

            //loop through location and find location values matching searched term
            //job in someJobs
            //need to iterate over an ArrayList of jobs. Each job is itself a HashMap.

            for (int i = 0; i < someJobs.size(); i++) {         //looping through the jobs
                System.out.println("\n" + "*****");

                for (Map.Entry<String, String> job : someJobs.get(i).entrySet()) {
                    System.out.printf(job.getKey() + ": " + job.getValue() + "\n");
                }

                System.out.println("*****");
            }
        }
    }
}















        /*
        System.out.println("***All Data Value***");
        JobData theJobs = new JobData();

        HashMap<String, String> currentJobs = null;
        for (int i = 0; i < someJobs.size(); i++) {
            //System.out.println(someJobs.get(i));
            currentJobs = someJobs.get(i);
            System.out.print("Position Type: ");
            System.out.println(currentJobs.get("position type"));

            System.out.print("Name: ");
            System.out.println(currentJobs.get("name"));

            System.out.print("Employer: ");
            System.out.println(currentJobs.get("employer"));

            System.out.print("Location: ");
            System.out.println(currentJobs.get("location"));

            System.out.print("Core Competency: ");
            System.out.println(currentJobs.get("core competency"));
            System.out.println("****\n");
        }



        System.out.println("printJobs is not implemented yet");*/






            /* RESTARTING NESTED FOR LOOP
            for (HashMap<String, String> job : someJobs) {
                System.out.println("\n" + "****");

                //think instead about creating a nested loop to loop over each HashMap

                for (String key : job.keySet()) {
                    //keyset : returns set view of all the keys present in the hashmap
                   // System.out.println(job.get(key));
                    System.out.println(key + ": " + job.get(key));
                }

                System.out.println("*******");
            }

             */
