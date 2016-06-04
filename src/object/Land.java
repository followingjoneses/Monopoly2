package object;

import function.LandService;
import game.Map;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/2.
 */
public class Land extends Serving implements Visualizable {
    public static final int MAX_LEVEL = 6;

    private int level, price, street;
    private int owner = -1;
    private LandService landService;

    public Land(int street, String name) {
        this.street = street;
        this.name = name;
        this.level = 1;
        this.landService = new LandService();
        this.price = 500;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void upgrade() {
        this.level++;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStreet() {
        return this.street;
    }

    public int getOwner() {
        return this.owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void serve(ArrayList<Player> players, int currentPlayer, Map map) {
        this.landService.serve(players, currentPlayer, this, map);
    }

    @Override
    public void printCellInfo(ArrayList<Player> players) {
        System.out.println(this.name);

        String landOwner = (owner == -1) ? "无主" : "主人是"+ players.get(owner).getName();

        System.out.println(landOwner);
        System.out.println("初始价格:"+price);
        System.out.println("等级:"+level + "级");

    }

    @Override
    public char toTexture() {
        switch (this.owner) {
            case -1:
                return '〇';
            case 0:
                return '①';
            case 1:
                return '②';
            case 2:
                return '③';
            case 3:
                return '④';
            default:
                return 0;
        }
    }
}
