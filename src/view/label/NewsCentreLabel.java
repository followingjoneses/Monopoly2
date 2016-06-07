package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class NewsCentreLabel extends MapLabel {
    private final static ImageIcon NEWS_CENTRE_ICON =
            new ImageIcon(new ImageIcon("image/news_center.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));

    public NewsCentreLabel() {
        setIcon(NEWS_CENTRE_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(NEWS_CENTRE_ICON);
    }
}
