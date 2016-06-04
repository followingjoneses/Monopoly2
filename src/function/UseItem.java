package function;

import game.*;
import object.Player;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/2.
 */
public class UseItem {
    private static final String NEARBY = "附近的玩家有:\n",
        SELECTION = "请输入相应玩家编号,按x返回上一层:\n",
        WARNING = "请输入符合要求的字符\n";

    public void turnAround(Player[] players, int currentPlayer) {
        Player[] nearby = findPlayers(players, currentPlayer, 5, true);
        System.out.print(NEARBY);
        printPlayers(nearby);
        System.out.print(SELECTION);

        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        if (option.equals("x"))
            return;

        try {
            int index = Integer.parseInt(option) - 1;
            if (index >= 0 && index < nearby.length)
                nearby[index].changeDirection();
            else
                System.out.print(WARNING);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }

    public void barrier() {

    }

    public int controlDice() {
        int dice = (int)(Math.random()*6) + 1;

        return dice;
    }

    public void divide(Player[] players) {
        int sum = 0;
        for (int i=0;i<players.length;i++)
            sum += players[i].getCash();
        int mean = sum/players.length;
        for (int i=0;i<players.length;i++)
            players[i].setCash(mean);
    }

    public void tax(Player[] players, int currentPlayer) {
        Player[] nearby = findPlayers(players, currentPlayer, 5, false);

        if (nearby.length == 0) {
            System.out.println("无");
            return;
        }

        System.out.print(NEARBY);
        printPlayers(nearby);
        System.out.print(SELECTION);

        Scanner sc = new Scanner(System.in);
        String option = sc.next();

        if (option.equals("x"))
            return;

        try {
            int index = Integer.parseInt(option) - 1;
            if (index >= 0 && index < nearby.length)
                nearby[index].addDeposit(-(int)(nearby[index].getDeposit()*0.3));
            else
                System.out.print(WARNING);
        } catch (NumberFormatException e) {
            System.out.print(WARNING);
        }
    }

    private Player[] findPlayers(Player[] players, int currentPlayer, int step, boolean includeSelf) {
        int length = 0;
        boolean[] playerIsNearby = new boolean[players.length];
        int location = players[currentPlayer].getLocation();

        for (int i=0;i<players.length && i!=currentPlayer;i++) {
            int delta = Math.abs(players[i].getLocation() - location + Map.MAP_LENGTH) % Map.MAP_LENGTH;
            if (delta <= step || delta>=(Map.MAP_LENGTH-step)) {
                length++;
                playerIsNearby[i] = true;
            }
        }
        if (includeSelf) {
            length++;
            playerIsNearby[currentPlayer] = true;
        }

        Player[] nearby = new Player[length];

        int index = 0;
        for (int i=0;i<players.length;i++) {
            if (playerIsNearby[i]) {
                nearby[index] = players[i];
                index++;
            }
        }

        return nearby;
    }

    private void printPlayers(Player[] nearby) {
        for (int i=0;i<nearby.length;i++)
            System.out.print(i+" "+nearby[i].getName()+"\t");
        System.out.println();
    }
}
