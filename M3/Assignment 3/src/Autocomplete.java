import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null){
            throw new NullPointerException();
        }
        this.terms = terms;
        Arrays.sort(terms);
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null){
            throw new NullPointerException();
        }
 ;
        // Using the binary search algorithm to find the first and last index of the prefix
        int first = BinarySearch.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        System.out.println(first);
        int last = BinarySearch.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        System.out.println(last);
        int size = (last - first) + 1;
        Term[] matches = new Term[size];
        if (first != -1 && last != -1){
            System.arraycopy(terms, first, matches, 0, size);
        }
        Arrays.sort(matches, Term.byDescendingWeightOrder());
        System.out.println(Arrays.toString(matches));
        return matches;
    }

}

