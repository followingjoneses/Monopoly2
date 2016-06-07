package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class MapLabel extends JLabel {
    protected ImageIcon[] playerIcons = new ImageIcon[4];

    public MapLabel() {
        setSize(30, 30);
        for (int i=0;i<playerIcons.length;i++) {
            playerIcons[i] = new ImageIcon("image/player"+i+"头像.png");
        }
    }

    protected void setPlayerIcon(int playerNumber) {
        setIcon(playerIcons[playerNumber]);
    }

    protected void setInitialIcon() {}
}
