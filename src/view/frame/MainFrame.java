package view.frame;

import game.Map;
import view.panel.MapPanel;

import javax.swing.*;

/**
 * Created by jzl on 16/6/6.
 */
public class MainFrame extends JFrame {
    private MapPanel mapPanel = new MapPanel();

    public MainFrame() {
        setTitle("大富翁");
        setSize(1000, 500);
        setLayout(null);
        mapPanel.setLocation(20, 20);
        add(mapPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
