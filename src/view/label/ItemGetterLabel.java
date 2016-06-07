package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class ItemGetterLabel extends MapLabel {
    private final static Image ITEM_GETTER_ICON = new ImageIcon("image/item_getter.png").getImage();
    
    public ItemGetterLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(ITEM_GETTER_ICON!=null)
            g.drawImage(ITEM_GETTER_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
