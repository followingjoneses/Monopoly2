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
    private static int BANK_LOCATION = 12;
//    private static final String WELCOME = "欢迎来到银行\n",
//        HINT = "玩家%s当前拥有现金%d,存款%d\n",
//        SELECTION = "按0选择存款,按1选择取款,按q退出:\n",
//        AMOUNT = "请输入%s金额:\n",
//        WARNING = "请输入符合要求的字符\n";

    public void serve(ArrayList<Player> players, int currentPlayer) {
//        Player player = players.get(currentPlayer);
//
//        System.out.print(WELCOME);
//        while (true) {
//            System.out.printf(HINT, player.getName(), player.getCash(), player.getDeposit());
//            System.out.print(SELECTION);
//            Scanner sc = new Scanner(System.in);
//            String option = sc.next();
//
//            if (option.equals("q"))
//                break;
//
//            if (option.equals("0")) {
//                System.out.printf(AMOUNT, "存款");
//                sc = new Scanner(System.in);
//                int amount = sc.nextInt();
//                save(player, amount);
//            } else if (option.equals("1")) {
//                System.out.printf(AMOUNT, "取款");
//                sc = new Scanner(System.in);
//                int amount = sc.nextInt();
//                withdraw(player, amount);
//            } else
//                System.out.print(WARNING);
//        }
//        new BankServiceFrame();
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

//    private void save(Player player, int money) {
//        if (money > player.getCash())
//            System.out.println("现金不足");
//        else {
//            player.addCash(-money);
//            player.addDeposit(money);
//        }
//    }
//
//    private void withdraw(Player player, int money) {
//        if (money > player.getDeposit())
//            System.out.println("储蓄不足");
//        else {
//            player.addCash(money);
//            player.addDeposit(-money);
//        }
//    }
}
