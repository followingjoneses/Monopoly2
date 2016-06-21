package view.frame;

import static game.Game.getInstance;

import game.Cell;
import game.Map;
import object.Land;
import object.Player;
import view.label.LandLabel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/8.
 */
public class LandServiceFrame extends JFrame {
    public LandServiceFrame(Land land) {
        setSize(400,150);
        setLayout(null);
        add(new LandPanel(land, this));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("房产");
        setVisible(true);
    }

    class LandPanel extends JPanel {
        LandPanel(Land land, JFrame frame) {
            setLayout(null);
            setBounds(0, 0, 400, 150);
            JLabel welcomeLabel = new JLabel("欢迎来到"+land.getName());
            welcomeLabel.setBounds(20, 20, 150, 20);
            add(welcomeLabel);
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            int landOwner = land.getOwner();
            int price = land.getPrice();
            int level = land.getLevel();
            if (landOwner == -1) {
                JLabel buyLabel = new JLabel("该土地无主,是否购买?");
                buyLabel.setBounds(20, 50, 150, 20);
                add(buyLabel);
                JRadioButton yes = new JRadioButton("是");
                JRadioButton no = new JRadioButton("否");
                yes.setBounds(200, 50, 60, 20);
                no.setBounds(300, 50, 60, 20);
                yes.addActionListener(e -> {
                    if (player.getCash() >= price) {
                        player.addCash(-price);
                        player.addHouseProperty(price);
                        land.setOwner(player.getNumber());
                        player.addLand(land);
                        LandLabel curLabel = (LandLabel) getInstance().getMap().
                                getMapLabels()[Map.COORDINATE[player.getLocation()][1]][Map.COORDINATE[player.getLocation()][0]];
                        curLabel.setLandIcon(player.getNumber(), 1);
                        JOptionPane.showMessageDialog(null, "购买成功", "房产", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        getInstance().nextPlayer(6);
                    } else {
                        JOptionPane.showMessageDialog(null, "现金不足", "房产", JOptionPane.ERROR_MESSAGE);
                        frame.dispose();
                        getInstance().nextPlayer(6);
                    }
                });
                no.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, "未购买", "房产", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    getInstance().nextPlayer(6);
                });
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(yes);
                buttonGroup.add(no);
                add(yes);
                add(no);
            } else if (landOwner == player.getNumber()) {
                JLabel upgradeLabel = new JLabel("该土地的主人是你,是否升级?");
                upgradeLabel.setBounds(20, 50, 150, 20);
                add(upgradeLabel);
                JRadioButton yes = new JRadioButton("是");
                JRadioButton no = new JRadioButton("否");
                yes.setBounds(200, 50, 40, 20);
                no.setBounds(250, 50, 40, 20);
                yes.addActionListener(e -> {
                    if (land.getLevel() < Land.MAX_LEVEL) {
                        if (player.getCash() >= price) {
                            player.addCash(-price);
                            player.addHouseProperty(price);
                            land.upgrade();
                            LandLabel curLabel = (LandLabel) getInstance().getMap().
                                    getMapLabels()[Map.COORDINATE[player.getLocation()][0]][Map.COORDINATE[player.getLocation()][1]];
                            curLabel.setLandIcon(player.getNumber(), land.getLevel());
                            JOptionPane.showMessageDialog(null, "升级成功", "房产", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            getInstance().nextPlayer(6);
                        } else {
                            JOptionPane.showMessageDialog(null, "现金不足", "房产", JOptionPane.ERROR_MESSAGE);
                            frame.dispose();
                            getInstance().nextPlayer(6);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "该房屋等级已经最高!", "房产", JOptionPane.WARNING_MESSAGE);
                        frame.dispose();
                        getInstance().nextPlayer(6);
                    }
                });
                no.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, "未升级", "房产", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    getInstance().nextPlayer(6);
                });
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(yes);
                buttonGroup.add(no);
                add(yes);
                add(no);
            } else {
                Player owner1 = null;
                for (int i=0;i<getInstance().getPlayers().size();i++) {
                    if (getInstance().getPlayers().get(i).getNumber() == landOwner) {
                        owner1 = getInstance().getPlayers().get(i);
                    }
                }
                final Player owner = owner1;
                int fee1 = (int)(price * level * 0.3);
                for (int i=0;i<owner.getLands().size();i++)
                    if (land != owner.getLands().get(i) && land.getStreet() == owner.getLands().get(i).getStreet())
                        fee1 += (int)(owner.getLands().get(i).getPrice() * owner.getLands().get(i).getLevel() * 0.1);
                final int fee = fee1;
                JLabel payLabel = new JLabel("你需要支付"+fee+"过路费");
                payLabel.setBounds(20, 50, 150, 20);
                add(payLabel);
                JButton confirm = new JButton("确定");
                confirm.setBounds(140, 80, 60, 20);
                confirm.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frame.dispose();
                        if (player.getCash() >= fee) {
                            player.addCash(-fee);
                            owner.addCash(fee);
                            getInstance().nextPlayer(6);
                        } else if (player.getCash() + player.getDeposit() >= fee) {
                            player.addDeposit(player.getCash() - fee);
                            player.setCash(0);
                            owner.addCash(fee);
                            getInstance().nextPlayer(6);
                        } else if (player.getCash() + player.getDeposit() + player.getHouseProperty() >= fee) {
                            int delta = fee - player.getCash() - player.getDeposit();
                            for (int i=0;i<player.getLands().size();i++) {
                                Land tempLand = player.getLands().get(0);
                                if (tempLand.getLevel()*tempLand.getPrice()>=delta) {
                                    int cash = tempLand.getLevel()*tempLand.getPrice() - delta;
                                    player.addCash(cash);
                                    player.addHouseProperty(-tempLand.getLevel()*tempLand.getPrice());
                                    player.getLands().remove(tempLand);
                                    tempLand.setOwner(-1);
                                    tempLand.setLevel(1);
                                    break;
                                } else {
                                    delta -= tempLand.getLevel()*tempLand.getPrice();
                                    player.getLands().remove(tempLand);
                                    tempLand.setOwner(-1);
                                    tempLand.setLevel(1);
                                }
                            }
                            owner.addCash(fee);
                            getInstance().nextPlayer(6);
                        } else {
                            int location = player.getLocation();
                            Cell cell = getInstance().getMap().getCell(game.Map.COORDINATE[location][0], game.Map.COORDINATE[location][1]);
                            cell.dismissView(player);
                            cell.getView(getInstance().getCurrentPlayer());
                            getInstance().getPlayers().remove(player);
                            JOptionPane.showMessageDialog(null, "玩家"+player.getName()+"破产了!");
                            getInstance().nextPlayer(7);
                        }
                    }
                });
                add(confirm);
            }
        }
    }
}
