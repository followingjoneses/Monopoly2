package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class LotteryHouseLabel extends MapLabel {
    private final static Image LOTTERY_HOUSE_ICON = new ImageIcon("image/lottery_house.png").getImage();
    
    public LotteryHouseLabel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(LOTTERY_HOUSE_ICON!=null)
            g.drawImage(LOTTERY_HOUSE_ICON, 0, 0, getWidth(),getHeight(),this);
    }
}
