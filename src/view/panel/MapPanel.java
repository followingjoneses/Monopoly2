package view.panel;

import static game.Game.getInstance;

import game.Cell;
import game.Map;
import object.Bank;
import object.Player;
import object.Serving;
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
    public static boolean ON_BANK = false;
    static {
        DICE_ICON[0] = new ImageIcon("image/dice1.png").getImage();
        DICE_ICON[1] = new ImageIcon("image/dice2.png").getImage();
        DICE_ICON[2] = new ImageIcon("image/dice3.png").getImage();
        DICE_ICON[3] = new ImageIcon("image/dice4.png").getImage();
        DICE_ICON[4] = new ImageIcon("image/dice5.png").getImage();
        DICE_ICON[5] = new ImageIcon("image/dice6.png").getImage();
    }

    private DiceButton diceButton = new DiceButton();

    public int getCurDiceNumber() {
        return curDiceNumber;
    }

    private int curDiceNumber = 1, movementCount;
    private Timer movementTimer = new Timer(100, e -> {
        if (movementCount < curDiceNumber) {
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            Cell startCell =
                    getInstance().getMap().getCell(Map.COORDINATE[player.getLocation()][0], Map.COORDINATE[player.getLocation()][1]);
            startCell.dismissView(player);
            startCell.getView(getInstance().getCurrentPlayer());
            player.addLocation(1);
            Cell endCell = getInstance().getMap().getCell(Map.COORDINATE[player.getLocation()][0], Map.COORDINATE[player.getLocation()][1]);
            Serving serving = endCell.getServing();
            endCell.addView(player);
            endCell.getView(getInstance().getCurrentPlayer());
            if (serving.isHasBarrier()) {
                JOptionPane.showMessageDialog(null, "你遇到了路障!");
                curDiceNumber = movementCount+1;
                serving.removeBarrier();
            } else if (serving instanceof Bank) {
                if (curDiceNumber != movementCount+1) {
                    ON_BANK = false;
                    serving.serve(getInstance().getPlayers(), getInstance().getCurrentPlayer(), getInstance().getMap());
                }
            }
            movementCount++;
            triggerEvent();
        }
    });

    public MapPanel() {
        setLayout(null);
        setSize(900, 400);
        MapLabel[][] mapLabels = getInstance().getMap().getMapLabels();
        for (int y=0;y<Map.MAP_HEIGHT;y++) {
            for (int x = 0; x < Map.MAP_WIDTH; x++) {
                if (mapLabels[y][x] == null) {
                    continue;
                }
                mapLabels[y][x].setLocation(x*40, y*40);
                add(mapLabels[y][x]);
            }
        }
        diceButton.setLocation(390, 140);
        add(diceButton);
    }

    private void triggerEvent() {
        if (movementCount == curDiceNumber) {
            movementTimer.stop();
            movementCount = 0;
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            if (player.getLocation() == 12) {
                ON_BANK = true;
            }
            Cell curCell =
                    getInstance().getMap().getCell(Map.COORDINATE[player.getLocation()][0], Map.COORDINATE[player.getLocation()][1]);
            curCell.getServing().serve(getInstance().getPlayers(), getInstance().getCurrentPlayer(), getInstance().getMap());
//            getInstance().nextPlayer(6);
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
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());

            super.paintComponent(g);
            if(diceCount==0){
                g.drawImage(DICE_ICON[curDiceNumber-1],0,0,getWidth(),getHeight(),this);
            }
            else{
                curDiceNumber = (int)(Math.random()*6)+1;
                g.drawImage(DICE_ICON[curDiceNumber-1],0,0,getWidth(),getHeight(),this);
            }
            if(diceCount==40){
                if (player.getNextDice() != 0) {
                    curDiceNumber = player.getNextDice();
                    g.drawImage(DICE_ICON[curDiceNumber-1],0,0,getWidth(),getHeight(),this);
                    repaint();
                    player.setNextDice(0);
                }
                diceTimer.stop();
                diceCount = 0;
                movementTimer.start();
            }
        }
    }
}
