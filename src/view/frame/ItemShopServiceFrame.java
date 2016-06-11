package view.frame;

import game.Menu;
import object.Player;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/8.
 */
public class ItemShopServiceFrame extends JFrame {
    private static final int PRICE = 50;

    public ItemShopServiceFrame() {
        setSize(300,280);
        setLayout(null);
        add(new ItemShopPanel(this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("道具店");
        setVisible(true);
    }

    class ItemShopPanel extends JPanel {
        ItemShopPanel(JFrame frame) {
            setLayout(null);
            setBounds(0, 0, 300, 280);
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            JRadioButton[] itemButtons = new JRadioButton[Menu.ITEM_NAMES.length];
            ButtonGroup buttonGroup = new ButtonGroup();
            for (int i=0;i<itemButtons.length;i++) {
                itemButtons[i] = new JRadioButton(Menu.ITEM_NAMES[i]);
                itemButtons[i].setBounds(20, 20+30*i, 150, 20);
                buttonGroup.add(itemButtons[i]);
                add(itemButtons[i]);
            }
            JButton confirm = new JButton("确定");
            confirm.setBounds(45, 230, 60, 20);
            confirm.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selected = getSelected(itemButtons);
                    if (selected==-1) {
                        JOptionPane.showMessageDialog(null, "请选择一个道具", "道具店", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (player.getPoint() < PRICE)
                            JOptionPane.showMessageDialog(null, "点券不足", "道具店", JOptionPane.WARNING_MESSAGE);
                        else {
                            player.addItem(selected, 1);
                            player.addPoint(-PRICE);
                            JOptionPane.showMessageDialog(null, "购买成功", "道具店", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            add(confirm);
            JButton returnButton = new JButton("返回");
            returnButton.setBounds(195, 230, 60, 20);
            returnButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                }
            });
            add(returnButton);
        }

        private int getSelected(JRadioButton[] buttons) {
            for (int i=0;i<buttons.length;i++)
                if (buttons[i].isSelected())
                    return i;

            return -1;
        }
    }
}
