package view.frame;

import object.Player;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/8.
 */
public class BankServiceFrame extends JFrame {
    public BankServiceFrame(){
        setSize(440,150);
        setLayout(null);
        add(new BankPanel(this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("银行");
        setVisible(true);
    }

    class BankPanel extends JPanel {
        BankPanel(JFrame frame) {
            setLayout(null);
            setBounds(0, 0, 440,150);
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            JLabel saveLabel = new JLabel("存款(1-"+player.getCash()+")");
            saveLabel.setBounds(20, 20, 150, 20);
            add(saveLabel);
            JTextField saveField = new JTextField();
            saveField.setBounds(200, 20, 150, 20);
            add(saveField);
            JLabel takeLabel = new JLabel("取款(1-"+player.getDeposit()+")");
            takeLabel.setBounds(20, 50, 150, 20);
            add(takeLabel);
            JTextField takeField = new JTextField();
            takeField.setBounds(200, 50, 150, 20);
            add(takeField);
            JButton saveButton = new JButton("确定");
            saveButton.setBounds(360, 20, 60, 20);
            saveButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        int money = Integer.parseInt(saveField.getText());
                        if (money<=0 || money>player.getCash()) {
                            throw new NumberFormatException();
                        } else {
                            player.addCash(-money);
                            player.addDeposit(money);
                            saveLabel.setText("存款(1-"+player.getCash()+")");
                            takeLabel.setText("取款(1-"+player.getDeposit()+")");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "输入有误!", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(saveButton);
            JButton takeButton = new JButton("确定");
            takeButton.setBounds(360, 50, 60, 20);
            takeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        int money = Integer.parseInt(saveField.getText());
                        if (money<=0 || money>player.getDeposit()) {
                            throw new NumberFormatException();
                        } else {
                            player.addCash(money);
                            player.addDeposit(-money);
                            saveLabel.setText("存款(1-"+player.getCash()+")");
                            takeLabel.setText("取款(1-"+player.getDeposit()+")");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "输入有误!", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(takeButton);
            JButton returnButton = new JButton("返回");
            returnButton.setBounds(190, 80, 60, 20);
            returnButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                }
            });
            add(returnButton);
        }
    }
}
