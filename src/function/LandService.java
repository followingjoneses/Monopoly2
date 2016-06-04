package function;

import game.*;
import object.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/23.
 */
public class LandService {
    private static final String WELCOME = "欢迎来到%s\n",
        NO_OWNER = "这块土地没有主人\n",
        PURCHASE = "你是否选择购买?\n按y购买,按n取消,请选择:\n",
        PURCHASE_SUCCESSFULLY = "购买成功\n",
        PURCHASE_UNSUCCESSFULLY = "未购买\n",
        NO_CASH = "现金不足\n",
        HAS_OWNER = "这块土地的主人是%s\n",
        MAX_LEVEL = "这块土地已经是最高级了\n",
        UPGRADE = "你是否选择升级?\n按y升级,按n取消,请选择:\n",
        UPGRADE_SUCCESSFULLY = "升级成功\n",
        UPGRADE_UNSUCCESSFULLY = "未升级\n",
        PAY = "你需要缴纳%d的过路费\n",
        PAY_SUCCESSFULLY = "缴纳成功\n",
        PAY_UNSUCCESSFULLY = "缴纳失败,玩家%s破产\n",
        WARNING = "请输入符合要求的字符\n";

    public void serve(ArrayList<Player> players, int currentPlayer, Land land, Map map) {
        String landName = land.getName();
        int landOwner = land.getOwner();
        Player player = players.get(currentPlayer);
        int price = land.getPrice();
        int level = land.getLevel();

        System.out.printf(WELCOME, landName);
        if (landOwner == -1) {
            System.out.print(NO_OWNER);
            System.out.print(PURCHASE);
            Scanner sc = new Scanner(System.in);
            String option = sc.next();
            if (option.equals("y")) {
                if (player.getCash() >= price) {
                    System.out.print(PURCHASE_SUCCESSFULLY);
                    player.addCash(-price);
                    player.addHouseProperty(price);
                    land.setOwner(player.getNumber());
                    player.addLand(land);
                } else
                    System.out.print(NO_CASH);
            } else if (option.equals("n"))
                System.out.print(PURCHASE_UNSUCCESSFULLY);
            else
                System.out.print(WARNING);
        } else if (landOwner == player.getNumber()) {
            System.out.printf(HAS_OWNER, player.getName());
            if (land.getLevel() < Land.MAX_LEVEL) {
                System.out.print(UPGRADE);
                Scanner sc = new Scanner(System.in);
                String option = sc.next();
                if (option.equals("y")) {
                    if (player.getCash() >= price) {
                        System.out.print(UPGRADE_SUCCESSFULLY);
                        player.addCash(-price);
                        player.addHouseProperty(price);
                        land.upgrade();
                    } else
                        System.out.print(NO_CASH);
                } else if (option.equals("n"))
                    System.out.print(UPGRADE_UNSUCCESSFULLY);
                else
                    System.out.print(WARNING);
            } else
                System.out.print(MAX_LEVEL);
        } else {
            Player owner = null;
            for (int i=0;i<players.size();i++)
                if (players.get(i).getNumber() == landOwner)
                    owner = players.get(i);

            System.out.printf(HAS_OWNER, owner.getName());
            int fee = (int)(price * level * 0.3);
            for (int i=0;i<owner.getLands().size();i++)
                if (land != owner.getLands().get(i) && land.getStreet() == owner.getLands().get(i).getStreet())
                    fee += (int)(owner.getLands().get(i).getPrice() * owner.getLands().get(i).getLevel() * 0.1);
            System.out.printf(PAY, fee);
            if (player.getCash() >= fee) {
                System.out.print(PAY_SUCCESSFULLY);
                player.addCash(-fee);
                owner.addCash(fee);
            } else if (player.getCash() + player.getDeposit() >= fee) {
                System.out.print(PAY_SUCCESSFULLY);
                player.addDeposit(player.getCash() - fee);
                player.setCash(0);
                owner.addCash(fee);
            } else if (player.getCash() + player.getDeposit() + player.getHouseProperty() >= fee) {
                System.out.print(PAY_SUCCESSFULLY);
                int delta = fee - player.getCash() - player.getDeposit();
                for (int i=0;i<player.getLands().size();i++) {
                    Land tempLand = player.getLands().get(0);
                    if (tempLand.getLevel()*tempLand.getPrice()>=delta) {
                        int cash = tempLand.getLevel()*tempLand.getPrice() - delta;
                        player.addCash(cash);
                        player.addHouseProperty(-tempLand.getLevel()*tempLand.getPrice());
                        player.getLands().remove(tempLand);
                        tempLand.setOwner(-1);
                        tempLand.setLevel(1);
                        break;
                    } else {
                        delta -= tempLand.getLevel()*tempLand.getPrice();
                        player.getLands().remove(tempLand);
                        tempLand.setOwner(-1);
                        tempLand.setLevel(1);
                    }
                }
                owner.addCash(fee);
            } else {
                System.out.printf(PAY_UNSUCCESSFULLY, player.getName());
                int location = player.getLocation();
                Cell cell = map.getCell(game.Map.COORDINATE[location][0], game.Map.COORDINATE[location][1]);
                cell.dismissView(player);
                players.remove(player);
            }
        }
    }
}
