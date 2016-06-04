package game;

import item.Item;
import object.*;

import java.util.*;
import java.text.*;

/**
 * Created by jzl on 16/4/21.
 */
public class Menu {
    private static final String CURRENT_PLAYER = "现在是玩家%s的操作时间,你的前进方向是%s\n",
        OPTION = "你现在可以进行如下操作:\n"+
                "0 - 查看地图\n"+
                "1 - 查看原始地图\n"+
                "2 - 使用道具\n"+
                "3 - 前方10步内示警\n"+
                "4 - 查看前后指定步数的具体信息\n"+
                "5 - 查看玩家的资产信息\n"+
                "6 - 扔骰子\n"+
                "7 - 认输\n"+
                "8 - 股票\n"+
                "请选择:\n",
        WARNING = "请输入符合要求的字符\n",
        ITEM = "你现在拥有的道具如下:\n",
        CARD_NUMBER = "请输入想使用的卡片编号,按x返回上一层:\n",
        CHECK_CELL_INFO = "请输入具体步数(顺时针为正,逆时针为负):\n",
        HAS_BARRIER = "前方%d步有路障\n",
        NO_BARRIER = "前方10步无路障\n",
        PLAYERS_INFO = "昵称\t现金\t存款\t房产\t\n",
        DICE = "你掷出了%d\n",
        TRAPPED = "你碰到了路障!\n",
        GIVE_UP = "玩家%s认输了!\n",
        MARKET_CLOSE = "今天是周末,股市休市\n",
        STOCK = "编号\t名称\t\t单股价格\t涨幅/跌幅\n",
        YOUR_STOCK = "编号\t名称\t\t单股价格\t涨幅/跌幅\t股数\n",
        BUY_OR_SELL = "你想要买还是卖股票,按y买,按n卖,按x返回上一层:\n",
        BUY_STOCK = "请选择你想要买的股票编号,按x返回上一层:\n",
        STOCK_NUMBER = "你想%s多少股:\n",
        SELL_STOCK = "请选择你想要卖的股票编号,按x返回上一层:\n",
        BUY_SUCCESSFULLY = "购买成功\n",
        NO_CASH = "现金不足\n",
        SELL_SUCCESSFULLY = "售出成功\n",
        NO_STOCK = "没那么多股\n",
        END_OF_MONTH = "今天是月末,银行发放利息\n";
    public static final String[] ITEM_NAMES;

    static {
        ITEM_NAMES = new String[7];
        ITEM_NAMES[0] = "转向卡";
        ITEM_NAMES[1] = "遥控骰子";
        ITEM_NAMES[2] = "路障";
        ITEM_NAMES[3] = "均富卡";
        ITEM_NAMES[4] = "查税卡";
        ITEM_NAMES[5] = "红卡";
        ITEM_NAMES[6] = "黑卡";
    }

    int printMainMenu(Stock[] stocks, Map map, Calendar calendar, ArrayList<Player> players, int currentPlayer) {
        String date = (new SimpleDateFormat("yyyy年M月d日")).format(calendar.getTime());
        System.out.println("今天是"+date);

        if (calendar.get(Calendar.DATE)==calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.print(END_OF_MONTH);
            players.forEach(player -> player.addDeposit((int)(0.1 * player.getDeposit())));
        }

        Player player = players.get(currentPlayer);

        String direction = player.isClockWise() ? "顺时针" : "逆时针";
        System.out.printf(CURRENT_PLAYER, player.getName(), direction);
        System.out.print(OPTION);

        int option = -1;
        Scanner sc = new Scanner(System.in);
        try {
            option = sc.nextInt();
            if (option >= 0 && option <=8) {
                printSubmenu(calendar, stocks, map, option, players, currentPlayer);
            } else
                System.out.print(WARNING);
        } catch (InputMismatchException e) {
            System.out.print(WARNING);
        }

        return option;
    }

    private void printSubmenu(Calendar calendar, Stock[] stocks, Map map, int option, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        switch (option) {
            case 0:
                map.printCurMap(currentPlayer);
                break;
            case 1:
                map.printInitialMap();
                break;
            case 2:
                printUseItem(stocks, map, players, currentPlayer);
                break;
            case 3:
                showBarriers(map, players, currentPlayer);
                break;
            case 4:
                checkCellInfo(map, players, currentPlayer);
                break;
            case 5:
                printPlayerProperties(players);
                break;
            case 6:
                rollDice(map, players, currentPlayer);
                break;
            case 7:
                giveUp(map, players, currentPlayer);
                break;
            case 8:
                tradeStock(calendar, stocks, players, currentPlayer);
                break;
        }
    }

    private void showBarriers(Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);
        int location = player.getLocation();
        boolean hasBarrier = false;

        for (int i=0;i<10;i++) {
            location = (location + 1) % Map.MAP_LENGTH;
            Cell cell = map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
            if (cell.getServing().isHasBarrier()) {
                hasBarrier = true;
                System.out.printf(HAS_BARRIER, i + 1);
            }
        }

        if (!hasBarrier)
            System.out.print(NO_BARRIER);
    }

    private void printUseItem(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);
        ArrayList<ArrayList<Item>> items = player.getItems();

        System.out.print(ITEM);

        for (int i=0;i<items.size();i++)
            System.out.println(i + " " + ITEM_NAMES[i] + "×" + items.get(i).size());

        System.out.print(CARD_NUMBER);
        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        if (option.equals("x"))
            return;

        try {
            int index = Integer.parseInt(option);
            if (index < 0 || index > 6 || player.getItems().get(index).size() == 0)
                System.out.print(WARNING);
            else
                player.getItem(index).use(stocks, map, players, currentPlayer);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }

    private void checkCellInfo(Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        System.out.print(CHECK_CELL_INFO);
        try {
            Scanner sc = new Scanner(System.in);
            int step = sc.nextInt();
            if (step >= -Map.MAP_LENGTH && step <= Map.MAP_LENGTH) {
                int location = (player.getLocation() + step + Map.MAP_LENGTH) % Map.MAP_LENGTH;
                Cell cell = map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
                cell.getServing().printCellInfo(players);
            } else
                System.out.print(WARNING);
        } catch (InputMismatchException e) {
            System.out.print(WARNING);
        }
    }

    private void printPlayerProperties(ArrayList<Player> players) {
        System.out.print(PLAYERS_INFO);
        players.forEach(player -> {
            System.out.println(player.getName() + "\t" + player.getCash() + "\t" +
                    player.getDeposit() + "\t" + player.getHouseProperty());
        });
    }

    private void rollDice(Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);
        int dice;
        if (player.getNextDice() == 0)
            dice = (int)(Math.random()*6) + 1;
        else {
            dice = player.getNextDice();
            player.setNextDice(0);
        }
        System.out.printf(DICE, dice);
        map.getCell(Map.COORDINATE[player.getLocation()][0], Map.COORDINATE[player.getLocation()][1]).dismissView(player);
        for (int i=0;i<dice;i++) {
            int location =
                    player.isClockWise() ? (player.getLocation() + i) % Map.MAP_LENGTH
                            : (player.getLocation() - i + Map.MAP_LENGTH) % Map.MAP_LENGTH;
            Serving serving =
                map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]).getServing();
            if (serving.isHasBarrier()) {
                dice = i;
                System.out.print(TRAPPED);
                serving.removeBarrier();
                break;
            } else if (serving instanceof Bank) {
                serving.serve(players, currentPlayer, map);
            }
        }
        player.addLocation(dice);

        Cell curCell = map.getCell(Map.COORDINATE[player.getLocation()][0], Map.COORDINATE[player.getLocation()][1]);

        curCell.addView(player);
        map.printCurMap(currentPlayer);

        curCell.getServing().serve(players, currentPlayer, map);
    }

    private void giveUp(Map map, ArrayList<Player> players, int currentPlayer) {
        Player player = players.get(currentPlayer);

        System.out.printf(GIVE_UP, player.getName());

        ArrayList<Land> lands = player.getLands();
        for (int i=0;i<lands.size();i++) {
            lands.get(i).setOwner(-1);
            lands.remove(lands.get(0));
        }
        int location = player.getLocation();
        Cell cell = map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
        cell.dismissView(player);
        players.remove(player);
    }

    private void tradeStock(Calendar calendar, Stock[] stocks, ArrayList<Player> players, int currentPlayer) {
        if (calendar.get(Calendar.DAY_OF_WEEK)  == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)  == Calendar.SUNDAY) {
            System.out.print(MARKET_CLOSE);
            return;
        }

        Player player = players.get(currentPlayer);

        System.out.print(STOCK);
        for (int i=0;i<stocks.length;i++)
            System.out.println(i + " \t" + stocks[i].getName() + "\t" +
                stocks[i].getPrice() + "\t" + stocks[i].getRate());
        System.out.print(BUY_OR_SELL);
        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        if (option.equals("x"))
            return;

        if (option.equals("y")) {
            System.out.print(BUY_STOCK);
            sc = new Scanner(System.in);
            option = sc.next();

            if (option.equals("x"))
                return;

            try {
                int index = Integer.parseInt(option);
                if (index >=0 && index < stocks.length) {
                    System.out.printf(STOCK_NUMBER, "买");
                    sc = new Scanner(System.in);
                    int number = sc.nextInt();
                    if (number >= 0) {
                        int sum = (int)(number * stocks[index].getPrice());
                        if (player.getDeposit() >= sum) {
                            player.addDeposit(-sum);
                            player.addStock(index, number);
                            System.out.print(BUY_SUCCESSFULLY);
                        } else if (player.getCash() + player.getDeposit() >= sum) {
                            player.setDeposit(0);
                            player.addCash(-sum);
                            player.addStock(index, number);
                            System.out.print(BUY_SUCCESSFULLY);
                        }
                        else
                            System.out.print(NO_CASH);
                    } else
                        System.out.print(WARNING);
                } else
                    System.out.print(WARNING);
            } catch (NumberFormatException e) {
                System.out.print(WARNING);
            }
        } else if (option.equals("n")) {
            System.out.print(YOUR_STOCK);
            for (int i=0;i<stocks.length;i++)
            System.out.println(i + " \t" + stocks[i].getName() + "\t" +
                    stocks[i].getPrice() + "\t" + stocks[i].getRate() + "\t" + player.getStock(i));
            System.out.print(SELL_STOCK);
            sc = new Scanner(System.in);
            option = sc.next();

            if (option.equals("x"))
                return;

            try {
                int index = Integer.parseInt(option);
                if (index >=0 && index < stocks.length && player.getStock(index) != 0) {
                    System.out.printf(STOCK_NUMBER, "卖");
                    sc = new Scanner(System.in);
                    int number = sc.nextInt();
                    if (number >= 0 && number <= player.getStock(index)) {
                        int sum = (int)(number * stocks[index].getPrice());
                        player.addStock(index, -number);
                        player.addCash(sum);
                        System.out.print(SELL_SUCCESSFULLY);
                    } else
                        System.out.print(NO_STOCK);
                } else
                    System.out.print(WARNING);
            } catch (NumberFormatException e) {
                System.out.print(WARNING);
            }
        } else
            System.out.print(WARNING);
    }
}
