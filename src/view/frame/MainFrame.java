package view.frame;

import view.menu.CheckMenu;
import view.menu.ItemMenu;
import view.menu.StockMenu;
import view.panel.MapPanel;
import view.panel.PlayerPanel;
import view.panel.TimePanel;

import javax.swing.*;

/**
 * Created by jzl on 16/6/6.
 */
public class MainFrame extends JFrame {
    private MapPanel mapPanel = new MapPanel();
    private TimePanel timePanel = new TimePanel();
    private PlayerPanel playerPanel = new PlayerPanel();

    public MainFrame() {
        setTitle("大富翁");
        setSize(1200, 500);
        setLayout(null);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new CheckMenu());
        menuBar.add(new ItemMenu());
        menuBar.add(new StockMenu());
        setJMenuBar(menuBar);
        mapPanel.setLocation(20, 20);
        add(mapPanel);
        timePanel.setLocation(940, 20);
        add(timePanel);
        playerPanel.setLocation(940, 200);
        add(playerPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
