package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class LandLabel extends MapLabel {
    private Image landIcon;
    private int owner = -1, level;

    public LandLabel(int owner, int level) {
        this.owner = owner;
        this.level = level;
        if (owner != -1) {
            landIcon = new ImageIcon("image/owner"+owner+"_"+level+".png").getImage();
        }
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(landIcon!=null)
            g.drawImage(landIcon, 0, 0, getWidth(),getHeight(),this);
    }
}
