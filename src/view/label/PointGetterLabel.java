package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class PointGetterLabel extends MapLabel {
    private final static ImageIcon POINT_GETTER_ICON =
            new ImageIcon(new ImageIcon("image/point_getter.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));


    public PointGetterLabel() {
        setIcon(POINT_GETTER_ICON);
    }

    @Override
    public void setInitialIcon() {
        setIcon(POINT_GETTER_ICON);
    }
}
