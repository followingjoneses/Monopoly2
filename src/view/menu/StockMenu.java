package view.menu;

import static game.Game.getInstance;

import view.frame.StockTradeFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

/**
 * Created by jzl on 16/6/9.
 */
public class StockMenu extends JMenu {
    public StockMenu() {
        super("股票");
        JMenuItem stockItem = new JMenuItem("股票");
        stockItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (getInstance().getCalendar().get(Calendar.DAY_OF_WEEK)  == Calendar.SATURDAY
                        || getInstance().getCalendar().get(Calendar.DAY_OF_WEEK)  == Calendar.SUNDAY) {
                    JOptionPane.showMessageDialog(null, "周末股市休市!");
                } else {
                    new StockTradeFrame();
                }
            }
        });
        add(stockItem);
    }
}
