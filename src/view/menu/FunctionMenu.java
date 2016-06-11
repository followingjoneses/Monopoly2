package view.menu;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                JOptionPane.showMessageDialog(null, "玩家"+
                        getInstance().getPlayers().get(getInstance().getCurrentPlayer()).getName()+"认输了!");
                getInstance().nextPlayer(7);
            }
        });
        add(giveUp);
    }
}
