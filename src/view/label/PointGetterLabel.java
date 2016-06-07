package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class PointGetterLabel extends MapLabel {
    private final static Image POINT_GETTER_ICON = new ImageIcon("image/point_getter.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(POINT_GETTER_ICON!=null)
            g.drawImage(POINT_GETTER_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
