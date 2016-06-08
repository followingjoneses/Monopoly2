package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/6.
 */
public class LotteryHouseLabel extends MapLabel {
    private final static ImageIcon LOTTERY_HOUSE_ICON =
            new ImageIcon(new ImageIcon("image/lottery_house.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));
    
    public LotteryHouseLabel() {
        setIcon(LOTTERY_HOUSE_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void setInitialIcon() {
        setIcon(LOTTERY_HOUSE_ICON);
    }
}
