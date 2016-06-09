package view.frame;

import static game.Game.getInstance;

import game.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/9.
 */
public class StockItemFrame extends JFrame {
    public StockItemFrame(int type) {
        setSize(150,380);
        setLayout(null);
        add(createPanel(type, this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String title = type==1 ? "红卡" : "黑卡";
        setTitle(title);
        setVisible(true);
    }

    private JPanel createPanel(int type, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 150, 380);
        JRadioButton[] radioButtons = new JRadioButton[10];
        for (int i=0;i<radioButtons.length;i++) {
            radioButtons[i] = new JRadioButton(Game.STOCK_NAME[i]);
            radioButtons[i].setBounds(20, 20+30*i, 100, 20);
            int index = i;
            radioButtons[i].addActionListener(e -> {
                getInstance().getStocks()[index].setRedOrBlack(type);
                JOptionPane.showMessageDialog(null, "成功使用!");
            });
            panel.add(radioButtons[i]);
        }
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(45, 320, 60, 20);
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        panel.add(returnButton);
        return panel;
    }
}
