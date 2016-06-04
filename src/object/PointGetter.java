package object;

import function.PointGetterService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/20.
 */
public class PointGetter extends Serving implements Visualizable {
    private PointGetterService pointGetterService;

    public PointGetter() {
        this.pointGetterService = new PointGetterService();
        this.name = "赠券点";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.pointGetterService.serve(players, currentPlayer);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '券';
    }
}
