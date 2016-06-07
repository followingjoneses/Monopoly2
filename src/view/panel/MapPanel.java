package view.panel;

import game.Map;
import view.label.MapLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/6.
 */
public class MapPanel extends JPanel {
    private static final Image[] DICE_ICON = new Image[6];
    static {
        DICE_ICON[0] = new ImageIcon("image/dice1.png").getImage();
        DICE_ICON[1] = new ImageIcon("image/dice2.png").getImage();
        DICE_ICON[2] = new ImageIcon("image/dice3.png").getImage();
        DICE_ICON[3] = new ImageIcon("image/dice4.png").getImage();
        DICE_ICON[4] = new ImageIcon("image/dice5.png").getImage();
        DICE_ICON[5] = new ImageIcon("image/dice6.png").getImage();
    }

    private MapLabel[] mapLabels = new MapLabel[Map.MAP_LENGTH];

    public MapPanel(char[][] map) {
        setLayout(null);
        int k=0;
        for (int i=0;i<map.length;i++) {
            for (int j=0;j<map[i].length;j++) {
                switch (map[i][j]) {
                    
                }
            }
        }
    }

    class DiceButton extends JButton {
        private int diceCount;
        private Timer diceTimer = new Timer(25, e -> {
            diceCount++;
            repaint();
        });

        DiceButton() {
            setBorder(null);
            setContentAreaFilled(false);
            setSize(120, 120);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    diceTimer.start();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (diceCount == 0) {

            }
        }
    }
}
