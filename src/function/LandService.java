package function;

import game.*;
import object.*;
import view.frame.LandServiceFrame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/23.
 */
public class LandService {
    public void serve(ArrayList<Player> players, int currentPlayer, Land land, Map map) {
        new LandServiceFrame(land);
    }
}
