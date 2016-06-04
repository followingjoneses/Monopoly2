package function;

import object.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/20.
 */
public class BankService {
    private static final String WELCOME = "欢迎来到银行\n",
        HINT = "玩家%s当前拥有现金%d,存款%d\n",
        SELECTION = "按0选择存款,按1选择取款,按q退出:\n",
        AMOUNT = "请输入%s金额:\n",
        WARNING = "请输入符合要求的字符\n";

    public void serve(ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        System.out.print(WELCOME);
        while (true) {
            System.out.printf(HINT, player.getName(), player.getCash(), player.getDeposit());
            System.out.print(SELECTION);
            Scanner sc = new Scanner(System.in);
            String option = sc.next();

            if (option.equals("q"))
                break;

            if (option.equals("0")) {
                System.out.printf(AMOUNT, "存款");
                sc = new Scanner(System.in);
                int amount = sc.nextInt();
                save(player, amount);
            } else if (option.equals("1")) {
                System.out.printf(AMOUNT, "取款");
                sc = new Scanner(System.in);
                int amount = sc.nextInt();
                withdraw(player, amount);
            } else
                System.out.print(WARNING);
        }
    }

    private void save(Player player, int money) {
        if (money > player.getCash())
            System.out.println("现金不足");
        else {
            player.addCash(-money);
            player.addDeposit(money);
        }
    }

    private void withdraw(Player player, int money) {
        if (money > player.getDeposit())
            System.out.println("储蓄不足");
        else {
            player.addCash(money);
            player.addDeposit(-money);
        }
    }
}
