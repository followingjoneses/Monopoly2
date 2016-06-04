package object;

import java.math.*;

/**
 * Created by jzl on 16/4/26.
 */
public class Stock {
    private String name;
    private int number, redOrBlack;
    private double price, rate;

    public Stock(String name, int number) {
        this.name = name;
        this.number = number;

        BigDecimal bg = new BigDecimal(Math.random() * 20 + 10).setScale(2, RoundingMode.UP);
        this.price = bg.doubleValue();
        bg = new BigDecimal(Math.random() * 0.2 - 0.1).setScale(2, RoundingMode.UP);
        this.rate = bg.doubleValue();
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public double getRate() {
        return rate;
    }

    public void setRedOrBlack(int redOrBlack) {
        this.redOrBlack = redOrBlack;
    }

    public int getRedOrBlack() {
        return redOrBlack;
    }

    public void tomorrow() {
        double tempRate;

        if (redOrBlack == 1) {
            tempRate = 0.099;
            redOrBlack = 0;
        } else if (redOrBlack == 2) {
            tempRate = -0.099;
            redOrBlack = 0;
        } else
            tempRate = Math.random() * 0.2 - 0.1;

        BigDecimal bg = new BigDecimal(tempRate).setScale(2, RoundingMode.UP);
        rate = bg.doubleValue();
        price = new BigDecimal(price * (1 + rate)).setScale(2, RoundingMode.UP).doubleValue();
    }
}
