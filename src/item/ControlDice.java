package item;

import game.Map;
import object.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class ControlDice extends Item{
//    private static final String USE = "你使用了遥控骰子\n",
//        STEP = "请输入你想掷的点数(1-6),按x返回上一层:\n",
//        WARNING = "请输入符合要求的字符\n";

    public ControlDice() {
        this.itemIndex = 1;
        this.name = "遥控骰子";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        String option = JOptionPane.showInputDialog(null, "请输入你想掷的点数(1-6)", "遥控骰子", JOptionPane.INFORMATION_MESSAGE);

        try {
            int step = Integer.parseInt(option);
            if (step >= 1 && step <= 6) {
                super.use(stocks, map, players, currentPlayer);
                player.setNextDice(step);
                JOptionPane.showMessageDialog(null, "你使用了遥控骰子", "遥控骰子", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
