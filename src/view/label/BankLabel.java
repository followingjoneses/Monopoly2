package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class BankLabel extends MapLabel{
    private final static ImageIcon BANK_ICON
            = new ImageIcon(new ImageIcon("image/bank.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));

    public BankLabel() {
        setIcon(BANK_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void setInitialIcon() {
        setIcon(BANK_ICON);
    }
}
