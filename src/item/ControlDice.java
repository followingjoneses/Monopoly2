package item;

import game.Map;
import object.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class ControlDice extends Item{
    private static final String USE = "你使用了遥控骰子\n",
        STEP = "请输入你想掷的点数(1-6),按x返回上一层:\n",
        WARNING = "请输入符合要求的字符\n";

    public ControlDice() {
        this.itemIndex = 1;
        this.name = "遥控骰子";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        System.out.print(STEP);
        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        if (option.equals("x"))
            return;

        try {
            int step = Integer.parseInt(option);
            if (step >= 1 && step <= 6) {
                super.use(stocks, map, players, currentPlayer);
                player.setNextDice(step);
                System.out.print(USE);
            }
            else
                System.out.print(WARNING);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }
}
