package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class PlayerLabel extends MapLabel {
    private Image playerIcon;
    private int playerNumber;

    public PlayerLabel(int playerNumber) {
        this.playerNumber = playerNumber;
        playerIcon = new ImageIcon("image/player"+playerNumber+"头像.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(playerIcon!=null)
            g.drawImage(playerIcon, 0, 0, getWidth(),getHeight(),this);
    }
}
