package object;

import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/23.
 */
public abstract class Serving {
    protected String name;
    protected boolean hasBarrier;

    public void serve(ArrayList<Player> players, int currentPlayer, Map map){}

    public void printCellInfo(ArrayList<Player> players){}

    public boolean isHasBarrier() {
        return this.hasBarrier;
    }

    public void setBarrier() {
        this.hasBarrier = true;
    }

    public void removeBarrier() {
        this.hasBarrier = false;
    }
}
