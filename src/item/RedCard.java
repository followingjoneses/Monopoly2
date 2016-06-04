package item;

import game.Map;
import object.Player;
import object.Stock;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class RedCard extends Item {
    private static final String USE = "你使用了红卡\n",
        STOCK = "编号\t名称\t单股价格\t涨幅/跌幅\n",
        SELECT_STOCK = "请选择股票编号:\n",
        WARNING = "请输入符合要求的字符\n";

    public RedCard() {
        this.itemIndex = 5;
        this.name = "红卡";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        System.out.print(STOCK);
        for (int i=0;i<stocks.length;i++)
            System.out.println(i + " \t" + stocks[i].getName() + "\t" +
                    stocks[i].getPrice() + "\t" + stocks[i].getRate());

        System.out.print(SELECT_STOCK);
        Scanner sc = new Scanner(System.in);
        try {
            int index = sc.nextInt();
            if (index >=0 && index < stocks.length) {
                super.use(stocks, map, players, currentPlayer);
                stocks[index].setRedOrBlack(1);
                System.out.print(USE);
            } else
                System.out.print(WARNING);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }
}
