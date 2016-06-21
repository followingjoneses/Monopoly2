package view.frame;

import static game.Game.getInstance;

import game.Menu;
import object.Player;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/9.
 */
public class UseItemFrame extends JFrame {
    public UseItemFrame() {
        setSize(150,300);
        setLayout(null);
        add(createPanel(this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("使用道具");
        setVisible(true);
    }

    private JPanel createPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 150, 260);
        Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
        JRadioButton[] itemButtons = new JRadioButton[7];
        for (int i=0;i<itemButtons.length;i++) {
            itemButtons[i] = new JRadioButton(Menu.ITEM_NAMES[i]);
            itemButtons[i].setBounds(20, 20+30*i, 100, 20);
            int index = i;
            itemButtons[i].addActionListener(e -> {
                if (player.getItemNumber(index) > 0) {
                    player.getItem(index).use(getInstance().getStocks(), getInstance().getMap(),
                            getInstance().getPlayers(), getInstance().getCurrentPlayer());
                } else {
                    JOptionPane.showMessageDialog(null, "你没有该道具!", "道具使用", JOptionPane.WARNING_MESSAGE);
                }
            });
            panel.add(itemButtons[i]);
        }
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(45, 230, 60, 20);
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
