package view.frame;

import view.chart.StockChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jzl on 16/6/21.
 */
public class StockTrendFrame extends JFrame {
    private StockChart stockChart;

    public StockTrendFrame(int index) {
        stockChart = new StockChart(index);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ChartPanel());
        JButton quit = new JButton("返回");
        quit.setBounds(270, 540, 60, 20);
        JFrame frame = this;
        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        add(quit);
        setTitle("股票走势图");
        setVisible(true);
    }

    class ChartPanel extends JPanel {
        public ChartPanel() {
            setBounds(50, 20, 500, 500);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(stockChart.getImage(),0,0,getWidth(),getHeight(),this);
        }
    }
}
