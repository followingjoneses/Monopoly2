package object;

import game.Map;
import item.*;

import java.util.ArrayList;

/**
 * Created by jzl on 16/4/2.
 */
public class Player implements Visualizable{
    private static final int ITEM_NUMBER = 7;

    private int cash, deposit, houseProperty, point, location, number, nextDice;
    private boolean isClockWise;
    private String name;
    private ArrayList<ArrayList<Item>> items;
    private ArrayList<Land> lands;
    private int[] stock;

    public Player(int number) {
        items = new ArrayList<>();
        for (int i=0;i<ITEM_NUMBER;i++) {
            items.add(new ArrayList<>());
            addItem(i, 2);
        }
        lands = new ArrayList<>();
        this.number = number;
        isClockWise = true;
        cash = 2000;
        point = 100;
        deposit = 2000;
        stock = new int[10];
    }

    public int getCash() {
        return this.cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public int getDeposit() {
        return this.deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void addDeposit(int deposit) {
        this.deposit += deposit;
    }

    public int getHouseProperty() {
        return this.houseProperty;
    }

    public void setHouseProperty(int houseProperty) {
        this.houseProperty = houseProperty;
    }

    public void addHouseProperty(int houseProperty) {
        this.houseProperty += houseProperty;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public int getLocation() {
        return this.location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void addLocation(int dice) {
        if (isClockWise)
            location = (location + dice) % Map.MAP_LENGTH;
        else
            location = (location - dice + Map.MAP_LENGTH) % Map.MAP_LENGTH;
    }

    public int getNumber() {
        return this.number;
    }

    public int getNextDice() {
        return this.nextDice;
    }

    public void setNextDice(int nextDice) {
        this.nextDice = nextDice;
    }

    public boolean isClockWise() {
        return this.isClockWise;
    }

    public void setClockWise(boolean isClockWise) {
        this.isClockWise = isClockWise;
    }

    public void changeDirection() {
        this.isClockWise = !this.isClockWise;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Item>> getItems() {
        return this.items;
    }

    public Item getItem(int index) {
        return items.get(index).get(0);
    }

    public void dismissItem(int index) {
        items.get(index).remove(items.get(index).get(0));
    }

    public void addItem(int index, int number) {
        ArrayList<Item> item = items.get(index);

        switch (index) {
            case 0:
                for (int i=0;i<number;i++)
                    item.add(new turnAroundCard());
                break;
            case 1:
                for (int i=0;i<number;i++)
                    item.add(new ControlDice());
                break;
            case 2:
                for (int i=0;i<number;i++)
                    item.add(new Barrier());
                break;
            case 3:
                for (int i=0;i<number;i++)
                    item.add(new DividePropertyCard());
                break;
            case 4:
                for (int i=0;i<number;i++)
                    item.add(new TaxCard());
                break;
            case 5:
                for (int i=0;i<number;i++)
                    item.add(new RedCard());
                break;
            case 6:
                for (int i=0;i<number;i++)
                    item.add(new BlackCard());
                break;
        }
    }

    public void addStock(int index, int number) {
        stock[index] += number;
    }

    public int getStock(int index) {
        return stock[index];
    }

    public void addLand(Land land) {
        lands.add(land);
    }

    public ArrayList<Land> getLands() {
        return this.lands;
    }

    @Override
    public char toTexture() {
        switch (number) {
            case 0:
                return '⑴';
            case 1:
                return '⑵';
            case 2:
                return '⑶';
            case 3:
                return '⑷';
        }
        return 0;
    }
}
