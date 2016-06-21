package view.frame;

import view.panel.TimePanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/11.
 */
public class TimeFrame extends JFrame{
    public TimeFrame() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new TimePanel());
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(170, 120, 60,20);
        JFrame frame = this;
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        add(returnButton);
        setTitle("时间");
        setVisible(true);
    }
}
