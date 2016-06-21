package function;

import static game.Game.getInstance;

import object.Player;
import view.frame.BankServiceFrame;
import view.panel.MapPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by jzl on 16/4/20.
 */
public class BankService {
    public void serve(ArrayList<Player> players, int currentPlayer) {
        Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
        String[] options = {"返回", "取款", "存款"};
        int rc = -1;
        while (rc != 0) {
            rc = JOptionPane.showOptionDialog(null, "请选择", "银行", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if (rc == 3) {
                String input = JOptionPane.showInputDialog(null, "你拥有现金"+player.getCash(), "存款");
                try {
                    int money = Integer.parseInt(input);
                    if (money<=0 || money>player.getCash()) {
                        throw new NumberFormatException();
                    } else {
                        player.addCash(-money);
                        player.addDeposit(money);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "输入有误!");
                }
            } else if (rc == 2) {
                String input = JOptionPane.showInputDialog(null, "你拥有存款"+player.getCash(), "取款");
                try {
                    int money = Integer.parseInt(input);
                    if (money<=0 || money>player.getCash()) {
                        throw new NumberFormatException();
                    } else {
                        player.addDeposit(-money);
                        player.addCash(money);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "输入有误!");
                }
            }
        }
        if (MapPanel.ON_BANK) {
            getInstance().nextPlayer(6);
        }
    }
}
