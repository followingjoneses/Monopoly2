package item;

import game.Map;
import object.Player;
import object.Stock;
import view.frame.StockItemFrame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class RedCard extends Item {
    public RedCard() {
        this.itemIndex = 5;
        this.name = "红卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        super.use(stocks, map, players, currentPlayer);
        new StockItemFrame(1);
    }
}
