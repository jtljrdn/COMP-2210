import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Provides a factory method for creating word search games.
 */
public class WordSearchGameFactory {

    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame() {
        return new WordSearchGameClass();
        }
    }
