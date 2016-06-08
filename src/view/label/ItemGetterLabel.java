package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class ItemGetterLabel extends MapLabel {
    private final static ImageIcon ITEM_GETTER_ICON =
            new ImageIcon(new ImageIcon("image/item_getter.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));
    
    public ItemGetterLabel() {
        setIcon(ITEM_GETTER_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void setInitialIcon() {
        setIcon(ITEM_GETTER_ICON);
    }
}
