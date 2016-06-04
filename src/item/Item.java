package item;

import game.Map;
import object.*;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/25.
 */
public abstract class Item {
    protected int itemIndex;
    protected String name;

    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        players.get(currentPlayer).dismissItem(this.itemIndex);
    }

    public int getItemIndex() {
        return this.itemIndex;
    }

    public String getName() {
        return this.name;
    }

    protected Player[] findPlayers(ArrayList<Player> players, int currentPlayer, int step, boolean includeSelf) {
        int length = 0;
        boolean[] playerIsNearby = new boolean[players.size()];
        int location = players.get(currentPlayer).getLocation();

        for (int i=0;i<players.size();i++) {
            int delta = Math.abs(players.get(i).getLocation() - location + Map.MAP_LENGTH) % Map.MAP_LENGTH;
            if (delta <= step || delta>=(Map.MAP_LENGTH-step)) {
                length++;
                playerIsNearby[i] = true;
            }
        }
        if (!includeSelf) {
            length--;
            playerIsNearby[currentPlayer] = false;
        }

        Player[] nearby = new Player[length];

        int index = 0;
        for (int i=0;i<players.size();i++) {
            if (playerIsNearby[i]) {
                nearby[index] = players.get(i);
                index++;
            }
        }

        return nearby;
    }
}
