package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class LandLabel extends MapLabel {
    public void setLandIcon(int owner, int level) {
        this.owner = owner;
        this.level = level;
        landIcon = new ImageIcon("image/owner"+owner+"_"+level+".png");
    }

    private ImageIcon landIcon;

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getOwner() {
        return owner;
    }

    private int owner = -1;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    private int level;

    public LandLabel(int owner, int level) {
        this.owner = owner;
        this.level = level;
        if (owner != -1) {
            landIcon = new ImageIcon("image/owner"+owner+"_"+level+".png");
        }
        setIcon(landIcon);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(landIcon);
    }
}
