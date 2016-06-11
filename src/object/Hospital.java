package object;

import function.HospitalService;
import game.Map;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by jzl on 16/6/11.
 */
public class Hospital extends Serving implements Visualizable {
    private HospitalService hospitalService;

    public Hospital() {
        hospitalService = new HospitalService();
        this.name = "医院";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.hospitalService.serve();
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        JOptionPane.showMessageDialog(null, this.name);
    }

    @Override
    public char toTexture() {
        return '医';
    }
}
