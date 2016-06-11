package view.frame;

import object.Player;
import object.Stock;

import static game.Game.getInstance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by jzl on 16/6/11.
 */
public class StockTradeFrame extends JFrame {
    private static String[] headers = new String[4];
    static {
        headers[0] = "编号";
        headers[1] = "名称";
        headers[2] = "单股价格";
        headers[3] = "涨幅/跌幅";
    }

    private Vector columnNames = new Vector(), data = new Vector();
    private Vector[] stockVectors = new Vector[10];

    public StockTradeFrame() {
        for (int i=0;i<stockVectors.length;i++) {
            stockVectors[i] = new Vector();
            Stock stock = getInstance().getStocks()[i];
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            stockVectors[i].addElement(stock.getNumber());
            stockVectors[i].addElement(stock.getName());
            stockVectors[i].addElement(stock.getPrice());
            stockVectors[i].addElement(stock.getRate());
            data.addElement(stockVectors[i]);
        }
        for (int i=0;i<headers.length;i++) {
            columnNames.addElement(headers[i]);
        }
        Table table = new Table();
        DefaultTableModel model=new DefaultTableModel(data,columnNames);
        table.setModel(model);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int clickCount = e.getClickCount();
                if (clickCount == 2) {
                    new BuyOrSellFrame(table.getSelectedRow());
                }
            }
        });
        table.setRowSelectionAllowed(true);
        setLayout(null);
        setSize(500,300);
        setTitle("股票");
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 500, 200);
        add(scrollPane);
        JButton quit = new JButton("返回");
        quit.setBounds(220, 220, 60, 20);
        JFrame frame = this;
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        add(quit);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class BuyOrSellFrame extends JFrame {
        public BuyOrSellFrame(int index) {
            Player player = getInstance().getPlayers().get(getInstance().getCurrentPlayer());
            setLayout(null);
            setSize(400, 120);
            setTitle("股票");
            JLabel label = new JLabel("你目前持有"+getInstance().getStocks()[index].getName()+player.getStock(index)+"股");
            JButton buy = new JButton("买入");
            JButton sell = new JButton("卖出");
            JButton quit = new JButton("返回");
            label.setBounds(10, 10, 150, 20);
            add(label);
            buy.setBounds(70, 40, 60, 20);
            buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String input = JOptionPane.showInputDialog(null, "你想买入多少股?");
                    try {
                        int number = Integer.parseInt(input);
                        if (number <= 0) {
                            throw new NumberFormatException();
                        }
                        int sum = (int) getInstance().getStocks()[index].getPrice()*number;
                        if (player.getDeposit() >= sum) {
                            player.addDeposit(-sum);
                            player.addStock(index, number);
                            JOptionPane.showMessageDialog(null, "成功购买");
                        } else if (player.getCash() + player.getDeposit() >= sum) {
                            player.setDeposit(0);
                            player.addCash(-sum);
                            player.addStock(index, number);
                            JOptionPane.showMessageDialog(null, "成功购买");
                        } else {
                            JOptionPane.showMessageDialog(null, "现金不足");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "输入有误");
                    }
                }
            });
            add(buy);
            sell.setBounds(170, 40, 60, 20);
            sell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String input = JOptionPane.showInputDialog(null, "你想卖出多少股?");
                    try {
                        int number = Integer.parseInt(input);
                        if (number <= 0 || number > player.getStock(index)) {
                            throw new NumberFormatException();
                        }
                        int sum = (int) getInstance().getStocks()[index].getPrice()*number;
                        player.addStock(index, -number);
                        player.addDeposit(sum);
                        JOptionPane.showMessageDialog(null, "成功卖出");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "输入有误");
                    }
                }
            });
            add(sell);
            quit.setBounds(270, 40, 60, 20);
            JFrame frame = this;
            quit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                }
            });
            add(quit);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}

class Table extends JTable {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}


