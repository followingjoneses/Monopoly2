package view.menu;

import game.Cell;
import game.Map;
import object.Land;
import object.Player;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by jzl on 16/6/11.
 */
public class FunctionMenu extends JMenu {
    public FunctionMenu() {
        super("功能");
        JMenuItem giveUp = new JMenuItem("认输");
        giveUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
                JOptionPane.showMessageDialog(null, "玩家"+
                        getInstance().getPlayers().get(getInstance().getCurrentPlayer()).getName()+"认输了!");
                ArrayList<Land> lands = player.getLands();
                for (int i=0;i<lands.size();i++) {
                    lands.get(i).setOwner(-1);
                    lands.remove(lands.get(0));
                }
                int location = player.getLocation();
                Cell cell = getInstance().getMap().getCell(Map.COORDINATE[location][0], Map.COORDINATE[location][1]);
                cell.dismissView(player);
                cell.getView(getInstance().getCurrentPlayer());
                getInstance().getPlayers().remove(player);
                getInstance().nextPlayer(7);
            }
        });
        add(giveUp);
    }
}
