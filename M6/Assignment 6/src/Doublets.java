import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Jordan Lee (jtl0071@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OR YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
    TreeSet<String> lexicon;
    private int wordCount;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            lexicon = new TreeSet<>();

            Scanner s =
                    new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                wordCount++;
                lexicon.add(str.toLowerCase());
                s.nextLine();
            }
            in.close();
        } catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////

    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
    public int getWordCount() {
        return lexicon.size();
    }


    /**
     * Checks to see if the given string is a word.
     *
     * @param str the string to check
     * @return true if str is a word, false otherwise
     */
    public boolean isWord(String str) {
        return lexicon.contains(str);
    }


    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return the Hamming distance between str1 and str2 if they are the
     * same length, -1 otherwise
     */
    public int getHammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return -1;
        }
        int distCounter = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distCounter++;
            }
        }
        return distCounter;
    }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param word the given word
     * @return the neighbors of the given word
     */
    public List<String> getNeighbors(String word) {
        LinkedList<String> neighbors = new LinkedList<>();
        for (String i : lexicon) {
            if (getHammingDistance(i, word) == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param sequence the given sequence of strings
     * @return true if the given sequence is a valid word ladder,
     * false otherwise
     */
    public boolean isWordLadder(List<String> sequence) {
        if (sequence.isEmpty()) {
            return false;
        }
        for (int i = 1; i < sequence.size(); i++) {
            if (!isWord(sequence.get(i))) {
                return false;
            }
            if (getHammingDistance(sequence.get(i), sequence.get(i - 1)) != 1) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     * <p>
     * Breadth-first search must be used in all implementing classes.
     *
     * @param start the starting word
     * @param end   the ending word
     * @return a minimum length word ladder from start to end
     */
    public List<String> getMinLadder(String start, String end) {
        LinkedList<String> ladder = new LinkedList<>();
        if (start == null || end == null) {
            return ladder;
        }
        if (getHammingDistance(start, end) == -1) {
            return ladder;
        }
        if (!isWord(start) || !isWord(end)) {
            return ladder;
        }
        if (start.equals(end)) {
            ladder.add(start);
            return ladder;
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        Node startingWord = new Node(start);
        visited.add(startingWord.word);
        queue.add(startingWord);

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            List<String> neighbors = current.neighborWords;
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    Node neighborNode = new Node(neighbor, current);
                    visited.add(neighbor);
                    queue.addLast(neighborNode);
                    if (neighbor.equals(end)) {
                        return backtrack(new Node(neighbor, current));
                    }
                }
            }
        }
        return ladder;
    }

    // Graph for BFS
    private class Node {
        private String word;
        private Node next;
        private List<String> neighborWords;

        Node(String word) {
            this.word = word;
            this.neighborWords = getNeighbors(word);
        }

        Node(String word, Node node) {
            this.word = word;
            this.next = node;
            this.neighborWords = getNeighbors(word);
        }

    }

    // Backtrack utility class to get create a list of all words from the first to last node in graph
    private List<String> backtrack(Node n) {
        List<String> ladder = new ArrayList<String>();
        String word = n.word;
        Node prev = n.next;
        ladder.add(word);
        while (prev != null) {
            word = prev.word;
            ladder.add(0, word);
            prev = prev.next;
        }
        return ladder;
    }

}
