package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class ItemGetterLabel extends MapLabel {
    private final static ImageIcon ITEM_GETTER_ICON = new ImageIcon("image/item_getter.png");
    
    public ItemGetterLabel() {
        setIcon(ITEM_GETTER_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(ITEM_GETTER_ICON);
    }
}
