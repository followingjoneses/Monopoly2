package view.menu;

import static game.Game.getInstance;

import game.Cell;
import game.Map;
import object.Player;
import view.frame.PlayerInfoFrame;
import view.frame.TimeFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/9.
 */
public class CheckMenu extends JMenu {
    public CheckMenu() {
        super("查看");
        JMenuItem checkPlayer = new JMenuItem("查看玩家信息");
        checkPlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new PlayerInfoFrame();
            }
        });
        add(checkPlayer);
        JMenuItem checkCell = new JMenuItem("查看前后指定步数的具体信息");
        checkCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "请输入具体步数(顺时针为正,逆时针为负)");
                Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
                try {
                    int step = Integer.parseInt(input);
                    if (step >= -Map.MAP_LENGTH && step <= Map.MAP_LENGTH) {
                        int location = (player.getLocation() + step + Map.MAP_LENGTH) % Map.MAP_LENGTH;
                        Cell cell = getInstance().getMap().getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
                        cell.getServing().printCellInfo(getInstance().getPlayers());
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入有误!", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(checkCell);
        JMenuItem checkTime = new JMenuItem("查看时间");
        checkTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new TimeFrame();
            }
        });
        add(checkTime);
        JMenuItem checkBarrier = new JMenuItem("前方10步内示警");
        checkBarrier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getInstance().getMenu().showBarriers(getInstance().getMap(), getInstance().getPlayers(), getInstance().getCurrentPlayer());
            }
        });
        add(checkBarrier);
    }
}
