package view.frame;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/4.
 */
public class SettingFrame extends JFrame {
    public SettingFrame(int playerNumber) {
        setTitle("游戏设定");
        setSize(400,30*(playerNumber+1)+80);
        setLayout(null);
        add(new SettingPanel(this, playerNumber));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class SettingPanel extends JPanel {
        public SettingPanel(JFrame frame, int playerNumber) {
            setLayout(null);
            setSize(400,30*(playerNumber+1)+80);

            JLabel dayLabel = new JLabel("请设置游戏天数: ");
            dayLabel.setBounds(20, 20, 150, 20);
            add(dayLabel);
            JTextField dayTextField = new JTextField();
            dayTextField.setBounds(200, 20, 150, 20);
            add(dayTextField);
            JLabel[] playerLabels = new JLabel[playerNumber];
            JTextField[] playerTextFields = new JTextField[playerNumber];
            for (int i=0;i<playerNumber;i++) {
                playerLabels[i] = new JLabel("请输入玩家"+(i+1)+"的名字: ");
                playerLabels[i].setBounds(20, 50+30*i, 150, 20);
                add(playerLabels[i]);
                playerTextFields[i] = new JTextField();
                playerTextFields[i].setBounds(200, 50+30*i, 150, 20);
                add(playerTextFields[i]);
            }
            JButton next = new JButton("确定");
            next.setBounds(170, 30*(playerNumber+1)+20, 60, 20);
            next.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        int day = Integer.parseInt(dayTextField.getText());
                        if (day <= 0)
                            throw new NumberFormatException();
                        boolean go = true;
                        for (int i=0;i<playerNumber;i++) {
                            if (playerTextFields[i].getText().equals("")) {
                                go = false;
                                break;
                            }
                        }
                        if (go) {
                            getInstance().setLifeTime(day);
                            String[] names = new String[playerNumber];
                            for (int i=0;i<names.length;i++) {
                                names[i] = playerTextFields[i].getText();
                            }
                            getInstance().setPlayerNames(names);
                            getInstance().buildMap();
                            new MainFrame();
                        } else {
                            JOptionPane.showMessageDialog(frame, "请输入玩家姓名", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(next);
        }

    }
}
