package object;

import function.ItemGetterService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/20.
 */
public class ItemGetter extends Serving implements Visualizable {
    private ItemGetterService itemGetterService;

    public ItemGetter() {
        this.itemGetterService = new ItemGetterService();
        this.name = "赠卡点";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.itemGetterService.serve(players, currentPlayer);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '卡';
    }
}
