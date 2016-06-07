package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class BankLabel extends MapLabel{
    private final static Image BANK_ICON = new ImageIcon("image/bank.png").getImage();

    public BankLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(BANK_ICON!=null)
            g.drawImage(BANK_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
