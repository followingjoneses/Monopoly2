package item;

import game.*;
import object.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jzl on 16/4/25.
 */
public class Barrier extends Item {
//    private static final String USE = "你使用了路障\n",
//        SET_BARRIER = "请属于你想设置路障的位置(8步以内,正为顺时针,负为逆时针),按x返回上一层:\n",
//        WARNING = "请输入符合要求的字符\n";

    public Barrier() {
        this.itemIndex = 2;
        this.name = "路障";
    }

    @Override
    public void use(Stock[] stocks, Map map, ArrayList<Player> players, int currentPlayer){
        Player player = players.get(currentPlayer);

//        System.out.print(SET_BARRIER);
//        Scanner sc = new Scanner(System.in);
//
//        String option = sc.next();
//
//        if (option.equals("x"))
//            return;
        String option = JOptionPane.showInputDialog(null, "请属于你想设置路障的位置(8步以内,正为顺时针,负为逆时针)", "路障", JOptionPane.INFORMATION_MESSAGE);

        try {
            int step = Integer.parseInt(option);
            if (step >= -8 && step <= 8) {
                super.use(stocks, map, players, currentPlayer);
                int location = (player.getLocation() + step + Map.MAP_LENGTH) % Map.MAP_LENGTH;
                Cell cell = map.getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
                cell.getServing().setBarrier();
//                System.out.print(USE);
                JOptionPane.showMessageDialog(null, "你使用了路障", "路障", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
//                System.out.print(WARNING);
                JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
//            System.out.print(WARNING);
            JOptionPane.showMessageDialog(null, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}
