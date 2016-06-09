package view.menu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/9.
 */
public class StockMenu extends JMenu {
    public StockMenu() {
        super("股票");
        JMenuItem stockItem = new JMenuItem("股票");
        stockItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(stockItem);
    }
}
