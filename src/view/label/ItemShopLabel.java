package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class ItemShopLabel extends MapLabel {
    private final static Image ITEM_SHOP_ICON = new ImageIcon("image/item_shop.png").getImage();

    public ItemShopLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(ITEM_SHOP_ICON!=null)
            g.drawImage(ITEM_SHOP_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
