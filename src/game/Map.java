package game;

import object.*;

import java.io.*;
import java.util.*;

/**
 * Created by jzl on 16/4/4.
 */
public class Map {
    public static final int MAP_LENGTH = 60;
    static final int MAP_HEIGHT = 10, MAP_WIDTH = 22;
    static final char[][] INITIAL_MAP = {
        {'◎', '◎', '◎', '◎', '◎', '◎', '◎', '新', '◎', '◎', '◎', '◎', '银', '◎', '◎', '◎', '◎', '◎', '券', '券', '◎', '◎'},
        {'彩', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'◎', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'◎', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'◎', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '道'},
        {'◎', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'空', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'卡', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'券', '　', '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　',  '　', '◎'},
        {'券', '券', '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券',  '券'}
    };
    public static final int[][] COORDINATE = {
            {0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0},
            {10, 0}, {11, 0}, {12, 0}, {13, 0}, {14, 0}, {15, 0}, {16, 0}, {17, 0}, {18, 0}, {19, 0},
            {20, 0}, {21, 0}, {21, 1}, {21, 2}, {21, 3}, {21, 4}, {21, 5}, {21, 6}, {21, 7}, {21, 8},
            {21, 9}, {20, 9}, {19, 9}, {18, 9}, {17, 9}, {16, 9}, {15, 9}, {14, 9}, {13, 9}, {12, 9},
            {11, 9}, {10, 9}, {9, 9}, {8, 9}, {7, 9}, {6, 9}, {5, 9}, {4, 9}, {3, 9}, {2, 9},
            {1, 9}, {0, 9}, {0, 8}, {0, 7}, {0, 6}, {0, 5}, {0, 4}, {0, 3}, {0, 2}, {0, 1}
    };
    static final String[][] LAND_NAME = {
        {"摩羯座1号", "摩羯座2号", "摩羯座3号", "摩羯座4号",  "摩羯座5号", "摩羯座6号", "摩羯座7号"},
        {"水瓶座1号", "水瓶座2号", "水瓶座3号", "水瓶座4号"},
        {"双鱼座1号", "双鱼座2号", "双鱼座3号", "双鱼座4号", "双鱼座5号"},
        {"白羊座1号", "白羊座2号", "白羊座3号", "白羊座4号", "白羊座5号"},
        {"金牛座1号", "金牛座2号", "金牛座3号", "金牛座4号"},
        {"双子座1号", "双子座2号", "双子座3号", "双子座4号"}
    };

    private Collection<Cell> cells;
    private char[][] curMap;

    public Map() {
        this.cells = new ArrayList<Cell>();
        this.curMap = new char[MAP_HEIGHT][MAP_WIDTH];
        for (int y=1;y<MAP_HEIGHT-1;y++)
            for (int x=1;x<MAP_WIDTH-1;x++)
                curMap[y][x] = '\u3000';
    }

    public Cell getCell(int x, int y) {
        Iterator<Cell> iterator = cells.iterator();

        for (;iterator.hasNext();) {
            Cell cell = iterator.next();
            if (cell.getX() == x && cell.getY() == y)
                return cell;
        }
        return null;
    }

    public Cell createCell(int x, int y) {
        Cell cell = new Cell(x,y);
        cells.add(cell);
        return cell;
    }

    private void setCurMap(int currentPlayer) {
        Iterator<Cell> iterator = cells.iterator();

        for (;iterator.hasNext();) {
            Cell cell = iterator.next();
            curMap[cell.getY()][cell.getX()] = cell.getView(currentPlayer);
        }
    }

    public void printInitialMap() {
        for (int y=0;y<MAP_HEIGHT;y++) {
            for (int x=0;x<MAP_WIDTH;x++)
                System.out.print(INITIAL_MAP[y][x] + "　");
            System.out.println();
        }
    }

    public void printCurMap(int currentPlayer) {
        setCurMap(currentPlayer);
        for (int y=0;y<MAP_HEIGHT;y++) {
            for (int x=0;x<MAP_WIDTH;x++)
                System.out.print(curMap[y][x] + "　");
            System.out.println();
        }
    }
}
