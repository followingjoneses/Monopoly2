package object;

import function.LotteryHouseService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/20.
 */
public class LotteryHouse extends Serving implements Visualizable {
    private LotteryHouseService lotteryHouseService;

    public LotteryHouse() {
        lotteryHouseService = new LotteryHouseService();
        this.name = "彩票点";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.lotteryHouseService.serve(players, currentPlayer);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '彩';
    }
}
