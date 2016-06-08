package function;

import object.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by jzl on 16/4/23.
 */
public class PointGetterService {
//    private static final String WELCOME = "欢迎来到点券赠送点\n",
//        GET_POINT = "你获得了%d点券\n";

    public void serve(ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

//        System.out.print(WELCOME);
        int point = (int)(Math.random()*100);
        player.addPoint(point);
        String message = "你获得了"+point+"点券";
        JOptionPane.showMessageDialog(null, message, "点券赠送点", JOptionPane.INFORMATION_MESSAGE);
//        System.out.printf(GET_POINT, point);
    }
}
