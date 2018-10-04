public class GameManager {
    private static int countOfPlates;
    private static int countOfFlagged;
    private static Game game;

    public GameManager(Game game) {
        this.countOfPlates = game.getCols() * game.getRows();
        this.game = game;
        this.countOfFlagged = 0;
    }

    public static void plateOpen() {
        if (countOfPlates > 1)
            countOfPlates--;
        else
            game.win();

        System.out.println(countOfPlates);
    }

    public static void bombOpen() {
        game.go();
    }

    public static void flagged() {
        countOfFlagged++;
        if (countOfFlagged == game.getBombCount() && countOfPlates-countOfFlagged == 0)
            game.win();
    }

    public static void unflagged() {
        countOfFlagged--;
    }
}
