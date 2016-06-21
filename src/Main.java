import game.Game;

/**
 * Created by jzl on 16/4/2.
 */
public class Main {
    public static void main(String[] args) {
        Game.getInstance().initialStock();
        Game.getInstance().startGame();
    }
}
