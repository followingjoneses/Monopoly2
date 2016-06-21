package function;

import object.Player;

import javax.swing.*;

import static game.Game.getInstance;

/**
 * Created by jzl on 16/4/23.
 */
public class OpeningService {
    public void serve() {
        JOptionPane.showMessageDialog(null, "你来到了空地,什么都做不了", "空地", JOptionPane.INFORMATION_MESSAGE);
        getInstance().nextPlayer(6);
    }
}
