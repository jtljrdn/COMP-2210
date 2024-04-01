import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Provides a factory method for creating word search games.
 */
public class WordSearchGameFactory {

    private static class WordSearchGameClass implements WordSearchGame{

        private String[][] board;
        int size;
        private TreeSet<String> lexicon;
        private boolean lexiconLoaded;
        private SortedSet<String> foundWords;

        public WordSearchGameClass(){
            size = 0;
            board = new String[size][size];
            lexiconLoaded = false;
        }

        /**
         * Loads the lexicon into a data structure for later use.
         *
         * @param fileName A string containing the name of the file to be opened.
         * @throws IllegalArgumentException if fileName is null
         * @throws IllegalArgumentException if fileName cannot be opened.
         */
        public void loadLexicon(String fileName) {
            if (fileName == null) {
                throw new IllegalArgumentException();
            }
            lexicon = new TreeSet<String>();
            try {
                Scanner s =
                        new Scanner(new BufferedReader(new FileReader(new File(fileName))));
                while (s.hasNext()) {
                    String str = s.next();
                    boolean added = lexicon.add(str.toUpperCase());
                    s.nextLine();
                }
                lexiconLoaded = true;
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
            }
        }

        /**
         * Stores the incoming array of Strings in a data structure that will make
         * it convenient to find words.
         *
         * @param letterArray This array of length N^2 stores the contents of the
         *     game board in row-major order. Thus, index 0 stores the contents of board
         *     position (0,0) and index length-1 stores the contents of board position
         *     (N-1,N-1). Note that the board must be square and that the strings inside
         *     may be longer than one character.
         * @throws IllegalArgumentException if letterArray is null, or is  not
         *     square.
         */
        public void setBoard(String[] letterArray){
            if (letterArray == null || !isPerfectSquare(letterArray.length)){
                throw new IllegalArgumentException("letterArray must not be null and must be square");
            }
            int boardDem = (int)Math.sqrt(letterArray.length);
            board = new String[boardDem][boardDem];
            int i = 0;
            for (int j = 0; j < boardDem; j++) {
                for (int k = 0; k < boardDem; k++) {
                    board[j][k] = letterArray[i++];
                }
            }
            size = boardDem;
        }

        /**
         * Creates a String representation of the board, suitable for printing to
         *   standard out. Note that this method can always be called since
         *   implementing classes should have a default board.
         */
        public String getBoard(){
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < size; i++) {
                string.append("\n| ");
                for (int j = 0; j < size; j++) {
                    string.append(board[i][j]).append(" ");
                }
                string.append("|");
            }
            return string.toString();
        }


        /**
         * Retrieves all scorable words on the game board, according to the stated game
         * rules.
         *
         * @param minimumWordLength The minimum allowed length (i.e., number of
         *     characters) for any word found on the board.
         * @return java.util.SortedSet which contains all the words of minimum length
         *     found on the game board and in the lexicon.
         * @throws IllegalArgumentException if minimumWordLength < 1
         * @throws IllegalStateException if loadLexicon has not been called.
         */
        public SortedSet<String> getAllScorableWords(int minimumWordLength){
            if (!lexiconLoaded){
                throw new IllegalStateException("A valid lexicon has not been loaded");
            }
            if (minimumWordLength < 1){
                throw new IllegalArgumentException("minimumWordLength must be greater than or equal to 1");
            }
            foundWords = new TreeSet<>();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0; i < (size * size); i++) {
                temp.add(i);
                if (isValidWord(toWord(temp)) && toWord(temp).length() >= minimumWordLength) {
                    foundWords.add(toWord(temp));
                }
                if (isValidPrefix(toWord(temp))) {
                    searchAllWords(temp, minimumWordLength);
                }
                temp.clear();
            }
            return foundWords;
        }

        /**
         * Computes the cumulative score for the scorable words in the given set.
         * To be scorable, a word must (1) have at least the minimum number of characters,
         * (2) be in the lexicon, and (3) be on the board. Each scorable word is
         * awarded one point for the minimum number of characters, and one point for
         * each character beyond the minimum number.
         *
         * @param words The set of words that are to be scored.
         * @param minimumWordLength The minimum number of characters required per word
         * @return the cumulative score of all scorable words in the set
         * @throws IllegalArgumentException if minimumWordLength < 1
         * @throws IllegalStateException if loadLexicon has not been called.
         */
        public int getScoreForWords(SortedSet<String> words, int minimumWordLength){
            if (!lexiconLoaded){
                throw new IllegalStateException("A valid lexicon has not been loaded");
            }
            if (minimumWordLength < 1){
                throw new IllegalArgumentException("minimumWordLength must be greater than or equal to 1");
            }
            int score = 0;
            for (String word : words) {
                if (word.length() >= minimumWordLength && isValidWord(word)
                        && !isOnBoard(word).isEmpty()) {
                    score += (word.length() - minimumWordLength) + 1;
                }
            }
            return score;
        }

        /**
         * Determines if the given word is in the lexicon.
         *
         * @param wordToCheck The word to validate
         * @return true if wordToCheck appears in lexicon, false otherwise.
         * @throws IllegalArgumentException if wordToCheck is null.
         * @throws IllegalStateException if loadLexicon has not been called.
         */
        public boolean isValidWord(String wordToCheck){
            if (wordToCheck == null) {
                throw new IllegalArgumentException("Word must not be null");
            }
            if (!lexiconLoaded) {
                throw new IllegalStateException("A valid lexicon has not been loaded");
            }
            return lexicon.contains(wordToCheck);
        }

        /**
         * Determines if there is at least one word in the lexicon with the
         * given prefix.
         *
         * @param prefixToCheck The prefix to validate
         * @return true if prefixToCheck appears in lexicon, false otherwise.
         * @throws IllegalArgumentException if prefixToCheck is null.
         * @throws IllegalStateException if loadLexicon has not been called.
         */
        public boolean isValidPrefix(String prefixToCheck){
            if (!lexiconLoaded){
                throw new IllegalStateException("A valid lexicon has not been loaded");
            }
            if (prefixToCheck == null){
                throw new IllegalArgumentException("prefixToCheck cannot be null");
            }
            String ceiling = lexicon.ceiling(prefixToCheck);
            if (ceiling != null){
                return ceiling.startsWith(prefixToCheck);
            }
            return false;
        }

        /**
         * Determines if the given word is in on the game board. If so, it returns
         * the path that makes up the word.
         * @param wordToCheck The word to validate
         * @return java.util.List containing java.lang.Integer objects with  the path
         *     that makes up the word on the game board. If word is not on the game
         *     board, return an empty list. Positions on the board are numbered from zero
         *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
         *     board, the upper left position is numbered 0 and the lower right position
         *     is numbered N^2 - 1.
         * @throws IllegalArgumentException if wordToCheck is null.
         * @throws IllegalStateException if loadLexicon has not been called.
         */
        public List<Integer> isOnBoard(String wordToCheck){
            if (!lexiconLoaded){
                throw new IllegalStateException("A valid lexicon has not been loaded");
            }
            if (wordToCheck == null){
                throw new IllegalArgumentException("wordToCheck cannot be null");
            }
            LinkedList<Integer> temp = new LinkedList<>();
            return searchForWord(wordToCheck, temp, 0 );

        }

        // HELPER METHODS //

        // Recursive function to search for a word
        private LinkedList<Integer> searchForWord(String wordToCheck, LinkedList<Integer> listProg, int posIndex) {
            if (!listProg.isEmpty() && !wordToCheck.equals(toWord(listProg))) {
                Position[] adjArray =
                        new Position(posIndex).adjacent(listProg);
                for (Position p : adjArray) {
                    if (p == null) {
                        break;
                    }
                    listProg.add(p.getIndex());
                    if (wordToCheck.equals(toWord(listProg))) {
                        break;
                    }
                    if (wordToCheck.startsWith(toWord(listProg))) {
                        searchForWord(wordToCheck, listProg, p.getIndex());
                    }
                    else {
                        listProg.removeLast();
                    }
                }
            }
            if (listProg.isEmpty()) {
                while (posIndex < (size * size)) {
                    if (wordToCheck.startsWith(new Position(posIndex).getLetter())) {
                        listProg.add(posIndex);
                        searchForWord(wordToCheck, listProg, posIndex);
                    }
                    posIndex++;
                }
                return listProg;
            }
            if (!toWord(listProg).equals(wordToCheck)) {
                listProg.removeLast();
            }
            return listProg;
        }

        // Recursive function to find all words
        private LinkedList<Integer> searchAllWords(LinkedList<Integer> wordProg,
                                                    int min) {
            Position[] adjArray = new Position(wordProg.getLast()).adjacent(wordProg);
            for (Position p : adjArray) {
                if (p == null) {
                    break;
                }
                wordProg.add(p.getIndex());
                if (isValidPrefix(toWord(wordProg))) {
                    if (isValidWord(toWord(wordProg))
                            && toWord(wordProg).length() >= min) {
                        foundWords.add(toWord(wordProg));
                    }
                    searchAllWords(wordProg, min);
                }
                else {
                    wordProg.removeLast();
                }
            }
            wordProg.removeLast();
            return wordProg;
        }

        // Builds the string of characters into a word
        public String toWord(LinkedList<Integer> listIn) {
            StringBuilder word = new StringBuilder();
            for (int i : listIn) {
                word.append(new Position(i).getLetter());
            }
            return word.toString();
        }
        // Checks if a # is a perfect square, used to determine if the board itself is square.
        private boolean isPerfectSquare(int num){
            if (num >= 0) {
                int sr = (int)Math.sqrt(num);
                return ((sr * sr) == num);
            }
            return false;
        }

        // HELPER CLASSES

        private class Position {
            private int x;
            private int y;
            private int index;
            private String letter;
            private static final int maxAdjacent = 8;

            Position(int indexIn) {
                this.index = indexIn;
                if (index == 0) {
                    this.x = 0;
                    this.y = 0;
                }
                else {
                    this.x = index % size;
                    this.y = index / size;
                }
                this.letter = board[y][x];
            }

            Position(int xIn, int yIn) {
                this.x = xIn;
                this.y = yIn;
                this.index = (y * size) + x;
                this.letter = board[y][x];
            }

            public Position[] adjacent(LinkedList<Integer> visited) {
                Position[] adj = new Position[maxAdjacent];
                int k = 0;
                for (int i = this.x - 1; i <= this.x + 1; i++) {
                    for (int j = this.y - 1; j <= this.y + 1; j++) {
                        if (!(i == this.x && j == this.y)) {
                            if (isValid(i, j) && !visited.contains((j * size) + i)) {
                                Position aux = new Position(i, j);
                                adj[k++] = aux;
                            }
                        }
                    }
                }
                return adj;
            }

            public boolean isValid(int xTest, int yTest) {
                return xTest >= 0 && xTest < size && yTest >= 0 && yTest < size;
            }

            public String getLetter() {
                return letter;
            }

            public int getIndex() {
                return index;
            }
        }
    }

    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame() {
        return new WordSearchGameClass();
        }
    }
