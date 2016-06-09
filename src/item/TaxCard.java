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
//    private static final String NEARBY = "附近的玩家有:\n",
//            SELECTION = "请输入相应玩家编号,按x返回上一层:\n",
//            WARNING = "请输入符合要求的字符\n",
//            USE = "你对玩家%s使用了查税卡\n";

    public TaxCard() {
        this.itemIndex = 4;
        this.name = "查税卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer) {
        Player[] nearby = findPlayers(players, currentPlayer, 5, false);

//        System.out.print(NEARBY);

        String nearbyPlayers = "";
        for (int i=0;i<nearby.length;i++) {
//            System.out.println(i + " " + nearby[i].getName());
            nearbyPlayers += i + "." + nearby[i].getName()+",";
        }

//        System.out.print(SELECTION);
//        Scanner sc = new Scanner(System.in);
//
//        String option = sc.next();

//        if (option.equals("x"))
//            return;
        String option = JOptionPane.showInputDialog(null, "附近的玩家有:"+nearbyPlayers, "查税卡", JOptionPane.INFORMATION_MESSAGE);

        try {
            int index = Integer.parseInt(option);
            if (index >= 0 && index < nearby.length) {
                super.use(stocks, map, players, currentPlayer);
                nearby[index].addDeposit(-(int)(0.3 * nearby[index].getDeposit()));
//                System.out.printf(USE, nearby[index].getName());
                JOptionPane.showMessageDialog(null, "你对"+nearby[index].getName()+"使用了查税卡", "查税卡", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
//            System.out.print(WARNING);
            JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
