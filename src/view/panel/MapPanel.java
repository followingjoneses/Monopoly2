package view.panel;

import game.Map;
import view.label.*;

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
        setSize(900, 400);
        int k=0;
        for (int i=0;i<map[0].length;i++) {
            switch (map[0][i]) {
                case '◎':
                    mapLabels[k] = new LandLabel(-1, 0);
                    break;
                case '新':
                    mapLabels[k] = new NewsCentreLabel();
                    break;
                case '银':
                    mapLabels[k] = new BankLabel();
                    break;
                case '券':
                    mapLabels[k] = new PointGetterLabel();
                    break;
                case '道':
                    mapLabels[k] = new ItemShopLabel();
                    break;
                case '卡':
                    mapLabels[k] = new ItemGetterLabel();
                    break;
                case '空':
                    mapLabels[k] = new OpeningLabel();
                    break;
            }
            mapLabels[k].setLocation(Map.COORDINATE[k][0]*40, Map.COORDINATE[k][1]*40);
            add(mapLabels[k++]);
        }
        for (int i=1;i<map.length-1;i++) {
            switch (map[i][Map.MAP_WIDTH-1]) {
                case '◎':
                    mapLabels[k] = new LandLabel(-1, 0);
                    break;
                case '新':
                    mapLabels[k] = new NewsCentreLabel();
                    break;
                case '银':
                    mapLabels[k] = new BankLabel();
                    break;
                case '券':
                    mapLabels[k] = new PointGetterLabel();
                    break;
                case '道':
                    mapLabels[k] = new ItemShopLabel();
                    break;
                case '卡':
                    mapLabels[k] = new ItemGetterLabel();
                    break;
                case '空':
                    mapLabels[k] = new OpeningLabel();
                    break;
            }
            mapLabels[k].setLocation(Map.COORDINATE[k][0]*40, Map.COORDINATE[k][1]*40);
            add(mapLabels[k++]);
        }
        for (int i=map[Map.MAP_HEIGHT-1].length-1;i>=0;i--) {
            switch (map[Map.MAP_HEIGHT-1][i]) {
                case '◎':
                    mapLabels[k] = new LandLabel(-1, 0);
                    break;
                case '新':
                    mapLabels[k] = new NewsCentreLabel();
                    break;
                case '银':
                    mapLabels[k] = new BankLabel();
                    break;
                case '券':
                    mapLabels[k] = new PointGetterLabel();
                    break;
                case '道':
                    mapLabels[k] = new ItemShopLabel();
                    break;
                case '卡':
                    mapLabels[k] = new ItemGetterLabel();
                    break;
                case '空':
                    mapLabels[k] = new OpeningLabel();
                    break;
            }
            mapLabels[k].setLocation(Map.COORDINATE[k][0]*40, Map.COORDINATE[k][1]*40);
            add(mapLabels[k++]);
        }
        for (int i=Map.MAP_HEIGHT-2;i>0;i--) {
            switch (map[i][0]) {
                case '◎':
                    mapLabels[k] = new LandLabel(-1, 0);
                    break;
                case '新':
                    mapLabels[k] = new NewsCentreLabel();
                    break;
                case '银':
                    mapLabels[k] = new BankLabel();
                    break;
                case '券':
                    mapLabels[k] = new PointGetterLabel();
                    break;
                case '道':
                    mapLabels[k] = new ItemShopLabel();
                    break;
                case '卡':
                    mapLabels[k] = new ItemGetterLabel();
                    break;
                case '空':
                    mapLabels[k] = new OpeningLabel();
                    break;
                case '彩':
                    mapLabels[k] = new LotteryHouseLabel();
                    break;
            }
            mapLabels[k].setLocation(Map.COORDINATE[k][0]*40, Map.COORDINATE[k][1]*40);
            add(mapLabels[k++]);
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
