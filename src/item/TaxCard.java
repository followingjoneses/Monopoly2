package item;

import game.Map;
import object.Player;
import object.Stock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class TaxCard extends Item {
    public TaxCard() {
        this.itemIndex = 4;
        this.name = "查税卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer) {
        Player[] nearby = findPlayers(players, currentPlayer, 5, false);

        String nearbyPlayers = "";
        for (int i=0;i<nearby.length;i++) {
            nearbyPlayers += i + "." + nearby[i].getName()+",";
        }

        String option = JOptionPane.showInputDialog(null, "附近的玩家有:"+nearbyPlayers, "查税卡", JOptionPane.INFORMATION_MESSAGE);

        try {
            int index = Integer.parseInt(option);
            if (index >= 0 && index < nearby.length) {
                super.use(stocks, map, players, currentPlayer);
                nearby[index].addDeposit(-(int)(0.3 * nearby[index].getDeposit()));
                JOptionPane.showMessageDialog(null, "你对"+nearby[index].getName()+"使用了查税卡", "查税卡", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
