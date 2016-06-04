package item;

import game.Map;
import object.Player;
import object.Stock;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/25.
 */
public class DividePropertyCard extends Item {
    private static final String USE = "你使用了均富卡\n";

    public DividePropertyCard() {
        this.itemIndex = 3;
        this.name = "均富卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        super.use(stocks, map, players, currentPlayer);

        int sigma = 0;

        for (int i=0;i<players.size();i++)
            sigma += players.get(i).getCash();

        int mean = sigma / players.size();

        for (int i=0;i<players.size();i++)
            players.get(i).setCash(mean);

        System.out.print(USE);
    }
}
