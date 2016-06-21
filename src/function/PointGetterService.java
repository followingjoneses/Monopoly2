package function;

import object.Player;

import javax.swing.*;
import java.util.ArrayList;

import static game.Game.getInstance;

/**
 * Created by jzl on 16/4/23.
 */
public class PointGetterService {
    public void serve(ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);
        int point = (int)(Math.random()*100);
        player.addPoint(point);
        String message = "你获得了"+point+"点券";
        JOptionPane.showMessageDialog(null, message, "点券赠送点", JOptionPane.INFORMATION_MESSAGE);
        getInstance().nextPlayer(6);
    }
}
