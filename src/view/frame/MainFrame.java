package view.frame;

import game.Map;
import view.menu.CheckMenu;
import view.menu.ItemMenu;
import view.menu.StockMenu;
import view.panel.MapPanel;

import javax.swing.*;

/**
 * Created by jzl on 16/6/6.
 */
public class MainFrame extends JFrame {
    private MapPanel mapPanel = new MapPanel();

    public MainFrame() {
        setTitle("大富翁");
        setSize(1200, 600);
        setLayout(null);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new CheckMenu());
        menuBar.add(new ItemMenu());
        menuBar.add(new StockMenu());
        setJMenuBar(menuBar);
        mapPanel.setLocation(20, 20);
        add(mapPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
