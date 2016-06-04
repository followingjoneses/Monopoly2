package item;

import game.*;
import object.Player;
import object.Stock;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class turnAroundCard extends Item {
    private static final String NEARBY = "附近的玩家有:\n",
        SELECTION = "请输入相应玩家编号,按x返回上一层:\n",
        WARNING = "请输入符合要求的字符\n",
        USE = "你对玩家%s使用了转向卡\n";

    public turnAroundCard() {
        this.itemIndex = 0;
        this.name = "转向卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        Player[] nearby = findPlayers(players, currentPlayer, 5, true);

        System.out.print(NEARBY);

        for (int i=0;i<nearby.length;i++)
            System.out.println(i + " " + nearby[i].getName());

        System.out.print(SELECTION);
        Scanner sc = new Scanner(System.in);

        String option = sc.next();

        if (option.equals("x"))
            return;

        try {
            int index = Integer.parseInt(option);
            if (index >= 0 && index < nearby.length) {
                super.use(stocks, map, players, currentPlayer);
                nearby[index].changeDirection();
                System.out.printf(USE, nearby[index].getName());
            }
            else
                System.out.print(WARNING);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }
}
