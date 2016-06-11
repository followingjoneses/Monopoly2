package view.label;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jzl on 16/6/11.
 */
public class HospitalLabel extends MapLabel {
    private final static ImageIcon HOSPITAL_ICON
            = new ImageIcon(new ImageIcon("image/hospital.png").getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));

    public HospitalLabel() {
        setIcon(HOSPITAL_ICON);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void setInitialIcon() {
        setIcon(HOSPITAL_ICON);
    }
}
