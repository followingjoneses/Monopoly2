package object;

import function.OpeningService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/20.
 */
public class Opening extends Serving implements Visualizable {
    private OpeningService openingService;

    public Opening() {
        this.openingService = new OpeningService();
        this.name = "空地";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.openingService.serve();
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '空';
    }
}
