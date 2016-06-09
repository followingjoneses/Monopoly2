package view.menu;

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
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(checkPlayer);
        JMenuItem checkCell = new JMenuItem("查看前后指定步数的具体信息");
        checkCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(checkCell);
    }
}
