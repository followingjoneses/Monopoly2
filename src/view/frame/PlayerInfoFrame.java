package view.frame;

import view.panel.PlayerPanel;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/11.
 */
public class PlayerInfoFrame extends JFrame {
    public PlayerInfoFrame() {
        setSize(600, 320);
        setLocationRelativeTo(null);
        setLayout(null);
        for (int i=0;i<getInstance().getPlayers().size();i++) {
            PlayerPanel playerPanel = new PlayerPanel(getInstance().getPlayers().get(i).getNumber());
            playerPanel.setLocation(150*i,0);
            add(playerPanel);
        }
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(270, 270, 60,20);
        JFrame frame = this;
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        add(returnButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("玩家信息");
        setVisible(true);
    }
}
