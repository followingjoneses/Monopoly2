package view.panel;

import static game.Game.getInstance;

import object.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/9.
 */
public class PlayerPanel extends JPanel {
    private int playerNumber;

    public PlayerPanel(int playerNumber) {
        this.playerNumber = playerNumber;
        setLayout(null);
        setSize(140,230);
        setVisible(true);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "玩家信息"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Player player = getInstance().getPlayers().get(playerNumber);
        g.setFont(new Font("楷体",Font.PLAIN,15));
        g.drawString("玩家："+player.getName(), 10, 30);
        g.drawString("现金："+player.getCash(),10,45);
        g.drawString("存款："+player.getDeposit(),10,60);
        g.drawString("房产："+player.getHouseProperty(), 10, 75);
        g.drawString("点券："+player.getPoint(),10,90);
        g.drawString("方向："+(player.isClockWise()?"顺时针":"逆时针"),10,105);
        g.drawString("转向卡："+player.getItemNumber(0),10,120);
        g.drawString("遥控骰子："+player.getItemNumber(1),10,135);
        g.drawString("路障："+player.getItemNumber(2),10,150);
        g.drawString("均富卡："+player.getItemNumber(3),10,165);
        g.drawString("查税卡："+player.getItemNumber(4),10,180);
        g.drawString("红卡："+player.getItemNumber(5),10,195);
        g.drawString("黑卡："+player.getItemNumber(6),10,210);
    }
}
