package view.chart;

import static game.Game.getInstance;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import object.Stock;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

public class StockChart {
	private JFreeChart jFreeChart;

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage image;
	public static XYSeriesCollection[] collections = new XYSeriesCollection[10];
	static{
		for(int i=0;i<10;i++)
			collections[i] = new XYSeriesCollection();
	}

    public StockChart(int index) {
        Stock stock = getInstance().getStocks()[index];
        jFreeChart = ChartFactory.createXYLineChart(
                "Trend Chart",
                "Day",
                "Transaction price",
                createDataset(index),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) jFreeChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLowerBound(stock.getLowestPrice()-5);
        NumberAxis Yaxis = (NumberAxis) plot.getRangeAxis();
        Yaxis.setTickUnit(new NumberTickUnit(1));

        NumberAxis Xaxis = (NumberAxis)plot.getDomainAxis();
        Xaxis.setTickUnit(new NumberTickUnit(1));

        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)plot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(true);
        image = jFreeChart.createBufferedImage(600, 300,BufferedImage.TYPE_INT_RGB, null);
    }
	
//	public static void refresh(){
//		jfreechart = ChartFactory.createXYLineChart(
//				"Trend Chart",
//				"Day",
//				"Transaction price",
//				createDataset(),
//				PlotOrientation.VERTICAL,
//				true,
//				true,
//				false
//		);
//		XYPlot plot = (XYPlot) jfreechart.getPlot();
//		plot.setBackgroundPaint(Color.WHITE);
//		ValueAxis rangeAxis = plot.getRangeAxis();
//		rangeAxis.setLowerBound(Stock.getStock(StockFrame.selectedRow).getLowestPrice()-5);
//		NumberAxis Yaxis = (NumberAxis) plot.getRangeAxis();
//		Yaxis.setTickUnit(new NumberTickUnit(1));
//
//		NumberAxis Xaxis = (NumberAxis)plot.getDomainAxis();
//		Xaxis.setTickUnit(new NumberTickUnit(1));
//
//		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)plot.getRenderer();
//		xylineandshaperenderer.setBaseShapesVisible(true);
//		image = jfreechart.createBufferedImage(600, 300,BufferedImage.TYPE_INT_RGB, null);
//	}
	
	private static XYDataset createDataset(int index){
		XYSeries xySeries = new XYSeries("");
        ArrayList<Double> priceList = getInstance().getStocks()[index].getPriceList();
		for(int i=0;i<priceList.size();i++) {
            xySeries.add(i+1, (double) priceList.get(i));
        }
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(xySeries);
		return xySeriesCollection;
	}
}
