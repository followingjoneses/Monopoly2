package object;

import function.ItemShopService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/19.
 */
public class ItemShop extends Serving implements Visualizable {
    private ItemShopService itemShopService;

    public ItemShop() {
        itemShopService = new ItemShopService();
        this.name = "道具店";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.itemShopService.serve(players, currentPlayer);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '道';
    }
}
