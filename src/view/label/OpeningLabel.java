package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class OpeningLabel extends MapLabel{
    private final static Image OPENING_ICON = new ImageIcon("image/opening.png").getImage();

    public OpeningLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(OPENING_ICON!=null)
            g.drawImage(OPENING_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
