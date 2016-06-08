package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class MapLabel extends JLabel {
    protected ImageIcon[] playerIcons = new ImageIcon[4];
    protected static final int SIZE = 30;

    public MapLabel() {
        setSize(SIZE, SIZE);
        for (int i=0;i<playerIcons.length;i++) {
            playerIcons[i] =
                    new ImageIcon(new ImageIcon("image/player"+i+"头像.png").getImage().
                            getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));

        }
    }

    public void setPlayerIcon(int playerNumber) {
        setIcon(playerIcons[playerNumber]);
    }

    public void setInitialIcon() {}
}
