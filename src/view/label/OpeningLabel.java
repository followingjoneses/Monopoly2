package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class OpeningLabel extends MapLabel{
    private final static ImageIcon OPENING_ICON =
            new ImageIcon(new ImageIcon("image/opening.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));


    public OpeningLabel() {
        setIcon(OPENING_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(OPENING_ICON);
    }
}
