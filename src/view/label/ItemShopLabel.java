package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class ItemShopLabel extends MapLabel {
    private final static ImageIcon ITEM_SHOP_ICON = new ImageIcon("image/item_shop.png");

    public ItemShopLabel() {
        setIcon(ITEM_SHOP_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(ITEM_SHOP_ICON);
    }
}
