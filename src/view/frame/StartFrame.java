package view.frame;

import static game.Game.getInstance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/4.
 */
public class StartFrame extends JFrame {
    private ImageIcon[] startIcons = new ImageIcon[2];

    public StartFrame() {
        for (int i=0;i<startIcons.length;i++) {
            startIcons[i] = new ImageIcon("image/开始游戏"+i+".png");
        }
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GameStartPanel(this));
        setTitle("大富翁");
        setVisible(true);
    }

    class GameStartPanel extends JPanel{
        private final Image GAME_START_BACKGROUND  = new ImageIcon("image/GAME_START_BACKGROUND.jpg").getImage();

        GameStartPanel(JFrame frame){
            setLayout(null);
            setSize(800,450);
            setVisible(true);

            final GameStartButton GAME_START_BUTTON = new GameStartButton(frame, startIcons[0]);
            add(GAME_START_BUTTON);
            GAME_START_BUTTON.setFont(new Font("TimesRoman",Font.ITALIC,20));
            GAME_START_BUTTON.setLocation(650,350);
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            if(GAME_START_BACKGROUND!=null)
                g.drawImage(GAME_START_BACKGROUND,0,0,getWidth(),getHeight(),this);
        }

        class GameStartButton extends JButton{
            public GameStartButton(JFrame frame, Icon icon){
                super(icon);
                setBorder(null);
                setContentAreaFilled(false);
                setSize(108,30);
                setRolloverIcon(startIcons[1]);
                setPressedIcon(startIcons[1]);
                addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        String input = JOptionPane.showInputDialog(frame, "请输入玩家人数(2-4)", "设置", JOptionPane.WARNING_MESSAGE);
                        try {
                            int playerNumber = Integer.parseInt(input);
                            if (playerNumber<2 || playerNumber>4)
                                throw new NumberFormatException();
                            else {
                                getInstance().setPlayerNumber(playerNumber);
                                new SettingFrame(playerNumber);
                                frame.dispose();
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "输入有误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        }
    }
}
