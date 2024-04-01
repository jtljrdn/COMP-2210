public class WordSearchGameClient {
    public static void main(String[] args) {
        WordSearchGame game = WordSearchGameFactory.createGame();
        game.loadLexicon("/Users/admin/Desktop/COMP-2210/M5/Assignment 5/wordfiles/words.txt");
        game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H",
                "N", "B", "O", "Q", "T", "T", "Y"});
        System.out.println(game.getBoard());
        System.out.print("LENT is on the board at the following positions: ");
        System.out.println(game.isOnBoard("LENT"));
        System.out.print("POPE is not on the board: ");
        System.out.println(game.isOnBoard("POPE"));
        System.out.println("All words of length 6 or more: ");
        System.out.println(game.getAllScorableWords(6));
    }
}