package game;

import object.*;
import view.frame.SettingFrame;

import javax.swing.*;
import java.util.*;
import java.text.*;

/**
 * Created by jzl on 16/4/2.
 */
public class Game {
    private static Game GAME = new Game();

    private static final int MAX_PLAYER = 4,
        MIN_STOCK = 10;
    public static final String[] STOCK_NAME =
            {"Nike", "Oracle", "Apple", "Citi", "Fort",
                    "Boeing", "Toyota", "Intel", "Yahoo", "Cisco"};

    private int lifetime, day = 1, playerNumber;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer;
    private Calendar calendar;

    public Menu getMenu() {
        return menu;
    }

    private Menu menu;

    public Map getMap() {
        return map;
    }

    private Map map;

    public Stock[] getStocks() {
        return stocks;
    }

    private Stock[] stocks;

    public Game() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日 EEEE");
        calendar = Calendar.getInstance();
        try {
            Date date = simpleDateFormat.parse("2014年1月1日 星期三");
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        menu = new Menu();
        map = new Map();
    }

    public void initialStock() {
        stocks = new Stock[MIN_STOCK];
        for (int i=0;i<MIN_STOCK;i++) {
            stocks[i] = new Stock(STOCK_NAME[i], i);
        }
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public void tomorrow() {
        day++;
        if (day > lifetime) {
            JOptionPane.showMessageDialog(null, "时间到");
            int maxProperty = 0, winner = 0;
            for (int i=0;i<playerNumber;i++) {
                int property = players.get(i).getCash()+players.get(i).getDeposit()+players.get(i).getHouseProperty();
                if (property>maxProperty) {
                    maxProperty = property;
                    winner = i;
                }
            }
            JOptionPane.showMessageDialog(null, "玩家"+players.get(winner).getName()+"资产最多,获胜!");
            System.exit(0);
        }
        calendar.add(Calendar.DATE, 1);
        if (calendar.get(Calendar.DATE)==calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            JOptionPane.showMessageDialog(null, "月底银行发利息了!");
            players.forEach(player -> player.addDeposit((int)(0.1 * player.getDeposit())));
        }
        if (calendar.get(Calendar.DAY_OF_WEEK)  != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK)  != Calendar.SUNDAY) {
            for (int i = 0; i < stocks.length; i++) {
                stocks[i].tomorrow();
            }
        }
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
        for (int i=0;i<playerNumber;i++) {
            players.add(new Player(i));
        }
    }

    public void setPlayerNames(String[] names) {
        for (int i=0;i<players.size();i++) {
            players.get(i).setName(names[i]);
        }
    }

    public void setLifeTime(int day) {
        lifetime = day;
    }

    public void startGame() {
        String input = JOptionPane.showInputDialog(null, "请输入玩家人数(2-4)", "设置", JOptionPane.WARNING_MESSAGE);
        try {
            int playerNumber = Integer.parseInt(input);
            if (playerNumber<2 || playerNumber>4)
                throw new NumberFormatException();
            else {
                getInstance().setPlayerNumber(playerNumber);
                new SettingFrame(playerNumber);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buildMap() {
        int landNameRow = 0, landNameColumn = 0;

        for (int y=0;y<Map.MAP_HEIGHT;y++) {
            for (int x=0;x<Map.MAP_WIDTH;x++) {
                if (Map.INITIAL_MAP[y][x] == '\u3000')
                    continue;
                Cell curCell = this.map.createCell(x, y);
                switch(Map.INITIAL_MAP[y][x]) {
                    case '◎':
                        if (landNameColumn == Map.LAND_NAME[landNameRow].length) {
                            landNameColumn = 0;
                            landNameRow++;
                        }
                        Land land = new Land(landNameRow, Map.LAND_NAME[landNameRow][landNameColumn++]);
                        curCell.addView(land);
                        curCell.setServing(land);
                        break;
                    case '新':
                        NewsCentre newsCentre = new NewsCentre();
                        curCell.addView(newsCentre);
                        curCell.setServing(newsCentre);
                        break;
                    case '银':
                        Bank bank = new Bank();
                        curCell.addView(bank);
                        curCell.setServing(bank);
                        break;
                    case '道':
                        ItemShop itemShop = new ItemShop();
                        curCell.addView(itemShop);
                        curCell.setServing(itemShop);
                        break;
                    case '券':
                        PointGetter pointGetter = new PointGetter();
                        curCell.addView(pointGetter);
                        curCell.setServing(pointGetter);
                        break;
                    case '空':
                        Opening opening = new Opening();
                        curCell.addView(opening);
                        curCell.setServing(opening);
                        break;
                    case '卡':
                        ItemGetter itemGetter = new ItemGetter();
                        curCell.addView(itemGetter);
                        curCell.setServing(itemGetter);
                        break;
                    case '彩':
                        LotteryHouse lotteryHouse = new LotteryHouse();
                        curCell.addView(lotteryHouse);
                        curCell.setServing(lotteryHouse);
                        break;
                    case '医':
                        Hospital hospital = new Hospital();
                        curCell.addView(hospital);
                        curCell.setServing(hospital);
                        break;
                }
                curCell.setMapLabel(map.getMapLabels()[y][x]);
            }
        }
        for (int i=0;i<players.size();i++) {
            map.getCell(0, 0).addView(players.get(i));
            map.getCell(0, 0).getView(currentPlayer);
        }
    }

    public void nextPlayer(int option) {
        if (players.size() == 1) {
            JOptionPane.showMessageDialog(null, "玩家"+players.get(0).getName()+"获胜!");
            System.exit(0);
        }
        if (currentPlayer == players.size()-1) {
            tomorrow();
        }
        if (option == 6) {
            currentPlayer = (currentPlayer + 1) % players.size();
        }
        else if (option == 7) {
            currentPlayer %= players.size();
        }
        JOptionPane.showMessageDialog(null, "现在是玩家"+players.get(currentPlayer).getName()+"的操作时间");
        int location = players.get(currentPlayer).getLocation();
        map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]).getView(currentPlayer);
        if (players.get(currentPlayer).getInHospital() != 0) {
            JOptionPane.showMessageDialog(null, "你还在医院里!");
            players.get(currentPlayer).inHospitalminus();
            currentPlayer = (currentPlayer + 1) % players.size();
            JOptionPane.showMessageDialog(null, "现在是玩家"+players.get(currentPlayer).getName()+"的操作时间");
            map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]).getView(currentPlayer);
        }
    }

    public static Game getInstance() {
        return GAME;
    }
}
