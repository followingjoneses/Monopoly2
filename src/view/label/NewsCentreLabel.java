package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class NewsCentreLabel extends MapLabel {
    private final static Image NEWS_CENTRE_ICON = new ImageIcon("image/news_center.png").getImage();

    public NewsCentreLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(NEWS_CENTRE_ICON!=null)
            g.drawImage(NEWS_CENTRE_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
