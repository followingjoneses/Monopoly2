package function;

import object.Player;

import javax.swing.*;
import java.lang.ref.PhantomReference;
import java.util.*;

import static game.Game.getInstance;

/**
 * Created by jzl on 16/4/20.
 */
public class LotteryHouseService {
    private static final int PRICE = 100;

    public void serve(ArrayList<Player> players, int currentPlayer) {
        int option = JOptionPane.showConfirmDialog(null, "你是否购买彩票?(100元)", "彩票点", JOptionPane.INFORMATION_MESSAGE);
        if (option==0) {
            Random random = new Random();
            boolean win = random.nextBoolean();
            Player player = players.get(currentPlayer);
            if (player.getCash() >= PRICE) {
                player.addCash(-PRICE);

                if (win) {
                    int money = (int)(Math.random() * 10000);
                    JOptionPane.showMessageDialog(null, "恭喜,你中了"+money+"元!", "彩票点", JOptionPane.INFORMATION_MESSAGE);
                    player.addCash(money);
                    getInstance().nextPlayer(6);
                } else {
                    JOptionPane.showMessageDialog(null, "很遗憾,你没有中奖", "彩票点", JOptionPane.INFORMATION_MESSAGE);
                    getInstance().nextPlayer(6);
                }
            } else {
                JOptionPane.showMessageDialog(null, "现金不足!", "彩票点", JOptionPane.WARNING_MESSAGE);
                getInstance().nextPlayer(6);
            }
        } else {
            JOptionPane.showMessageDialog(null, "未购买", "彩票点", JOptionPane.INFORMATION_MESSAGE);
            getInstance().nextPlayer(6);
        }
    }
}
