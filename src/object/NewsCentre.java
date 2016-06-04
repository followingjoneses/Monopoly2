package object;

import function.NewsCentreService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/19.
 */
public class NewsCentre extends Serving implements Visualizable {
    private NewsCentreService newsCentreService;

    public NewsCentre() {
        newsCentreService = new NewsCentreService();
        this.name = "新闻中心";
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.newsCentreService.serve(players, currentPlayer);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);
    }

    @Override
    public char toTexture() {
        return '新';
    }
}
