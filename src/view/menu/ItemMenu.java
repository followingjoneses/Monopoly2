package view.menu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/9.
 */
public class ItemMenu extends JMenu {
    public ItemMenu() {
        super("道具");
        JMenuItem useItem = new JMenuItem("使用道具");
        useItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(useItem);
    }
}
