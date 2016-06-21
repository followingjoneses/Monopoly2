package view.panel;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created by jzl on 16/6/9.
 */
public class TimePanel extends JPanel {
    public TimePanel() {
        setLayout(null);
        setBounds(0,0,400,100);
        setName("游戏信息");
        setVisible(true);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "时间信息"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("微软雅黑",Font.PLAIN,30));
        g.setColor(Color.BLACK);
        g.drawString("今天是"+(new SimpleDateFormat("yyyy年M月d日 EEEE")).format(getInstance().getCalendar().getTime()),5, 60);
    }
}
