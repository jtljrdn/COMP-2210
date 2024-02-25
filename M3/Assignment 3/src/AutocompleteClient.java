import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Client for Autocomplete.
 */
public class AutocompleteClient {

    /** Drives execution. */
    public static void main(String[] args) {
        Term[] terms = null;
        String filename = "/Users/admin/Desktop/COMP-2210/M3/Assignment 3/src/norvig-count1w.txt";
        try {
            Scanner scanner = new Scanner(new File(filename));
            int numLines = scanner.nextInt();
            System.out.println("Added file, parsing...");
            terms = new Term[numLines];
            for (int i = 0; i < numLines; i++) {
                String query = scanner.next().strip();
                long weight = scanner.nextLong();
                scanner.nextLine();
                terms[i] = new Term(query, weight);
            }
            System.out.println("Parsing Completed...");
        } catch (Exception e) {
            System.out.println("*****ERROR**** " + e.toString());
        }
        System.out.println("Looking for autocomplete");
        Autocomplete auto = new Autocomplete(terms);
        Term[] matches = auto.allMatches("comp");
        for (Term term : matches) {
            System.out.println(term);
        }
        System.out.println("Done");
    }

}
