package function;

import object.Player;

import javax.swing.*;
import java.lang.ref.PhantomReference;
import java.util.*;

/**
 * Created by jzl on 16/4/20.
 */
public class LotteryHouseService {
    private static final String WELCOME = "欢迎来到彩票点\n",
        BUY = "你是否购买彩票?(100元)按y确认,按n取消:\n",
        WIN = "恭喜,你中了%d元!\n",
        NOT_WIN = "很遗憾,你没有中奖\n",
        NOT_BUY = "未购买彩票\n",
        WARNING = "请输入符合要求的字符\n";
    private static final int PRICE = 100;

    public void serve(ArrayList<Player> players, int currentPlayer) {
//        System.out.print(WELCOME);
//        System.out.print(BUY);

//        Scanner sc = new Scanner(System.in);
//        String option = sc.next();
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
                } else {
                    JOptionPane.showMessageDialog(null, "很遗憾,你没有中奖", "彩票点", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "现金不足!", "彩票点", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "未购买", "彩票点", JOptionPane.INFORMATION_MESSAGE);
        }

//        if (option.equals("y")) {
//            Random random = new Random();
//            boolean win = random.nextBoolean();
//            Player player = players.get(currentPlayer);
//            if (player.getCash() >= PRICE) {
//                player.addCash(-PRICE);
//
//                if (win) {
//                    int money = (int)(Math.random() * 10000);
//                    System.out.printf(WIN, money);
//                    player.addCash(money);
//                } else
//                    System.out.print(NOT_WIN);
//            } else
//                System.out.println("现金不足");
//        } else if (option.equals("n"))
//            System.out.print(NOT_BUY);
//        else
//            System.out.print(WARNING);
    }
}
