import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.Range;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class FrameChart extends JFrame{
    private Currency cur;
    boolean sma;
    FrameChart(int index,SiteAPI api, boolean sm)throws Exception {
        sma = sm;
        api.initCurr();
        cur = api.getCurr();
        XYDataset dataset = null;
        switch (index){
            case 0: dataset = CreateDataset1(); break;
            case 1: dataset = CreateDataset2(); break;
            case 2: dataset = CreateDataset3(); break;
            case 3: dataset = CreateDataset4(); break;
        }
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1400, 600));
        setContentPane(chartPanel);
        pack();
        setVisible(true);

    }
    private JFreeChart createChart(final XYDataset dataset)
    {
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "График курса валют",       // chart title
                "Year",                      // domain axis label
                "Value",
        dataset
        );
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setOrientation(PlotOrientation.VERTICAL);
        XYItemRenderer renderer;
        renderer = (XYItemRenderer) plot.getRenderer();
        plot.setRenderer(renderer);
        Rectangle rect = new Rectangle(1,3);
        renderer.setSeriesShape(0, rect);
        plot.setRenderer(renderer);

        plot.setAxisOffset(new RectangleInsets (1.0, 1.0, 1.0, 1.0));

        return chart;
    }


    private XYDataset CreateDataset1(){
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Dollar/rub");
        if(sma) {
            TimeSeries series1 = new TimeSeries("SMA - 10");
            TimeSeries series2 = new TimeSeries("SMA - 100");
            ArrayList<Double> sma10 = SMA10();
            ArrayList<Double> sma100 = SMA100();
            float tmp = (float) cur.date.size() / sma100.size();
            int step = (int) Math.ceil(tmp);

            System.out.println(sma100.size() + "    " + cur.date.size());

            for (int i = 0; i < sma100.size() - 1; i++) {
                series2.add(Day.parseDay(cur.date.get(i).toString()), sma100.get(i));
            }
            for (int i = 0; i < sma10.size() - 1; i++) {
                series1.add(Day.parseDay(cur.date.get(i).toString()), sma10.get(i));
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);

        }
        for(int i = 0; i < cur.value.size()-1;i++){
            series.add(Day.parseDay(cur.date.get(i).toString()), cur.value.get(i));
        }
        dataset.addSeries(series);

        return dataset;
    }

    private XYDataset CreateDataset2()throws Exception{
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Dollar/rub");
        if(sma) {
            TimeSeries series1 = new TimeSeries("SMA - 10");
            TimeSeries series2 = new TimeSeries("SMA - 100");
            ArrayList<Double> sma10 = SMA10();
            ArrayList<Double> sma100 = SMA100();
            float tmp = (float) cur.date.size() / sma100.size();
            int step = (int) Math.ceil(tmp);

            System.out.println(sma100.size() + "    " + cur.date.size());

            for (int i = 0; i < sma100.size() - 1; i += 6) {
                series2.add(Day.parseDay(cur.date.get(i).toString()), sma100.get(i));
            }
            for (int i = 0; i < sma10.size() - 1; i += 6) {
                series1.add(Day.parseDay(cur.date.get(i).toString()), sma10.get(i));
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);

        }
        for(int i = 0; i < cur.value.size()-1;i+=7){
            series.add(Day.parseDay(cur.date.get(i).toString()), cur.value.get(i));
        }
        //series.add(Day.parseDay(cur.date.get(cur.value.size()-1).toString()), cur.value.get(cur.value.size()-1));
        dataset.addSeries(series);
        return dataset;
    }
    private XYDataset CreateDataset3()throws Exception{
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Dollar/rub");
        if(sma) {
            TimeSeries series1 = new TimeSeries("SMA - 10");
            TimeSeries series2 = new TimeSeries("SMA - 100");
            ArrayList<Double> sma10 = SMA10();
            ArrayList<Double> sma100 = SMA100();
            float tmp = (float) cur.date.size() / sma100.size();
            int step = (int) Math.ceil(tmp);

            System.out.println(sma100.size() + "    " + cur.date.size());

            for (int i = 0; i < sma100.size() - 1; i += 24) {
                series2.add(Day.parseDay(cur.date.get(i).toString()), sma100.get(i));
            }
            for (int i = 0; i < sma10.size() - 1; i += 24) {
                series1.add(Day.parseDay(cur.date.get(i).toString()), sma10.get(i));
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);

        }
        for(int i = 0; i < cur.value.size()-1;i+=24){
            series.add(Day.parseDay(cur.date.get(i).toString()), cur.value.get(i));
        }
        series.add(Day.parseDay(cur.date.get(cur.value.size()-1).toString()), cur.value.get(cur.value.size()-1));
        dataset.addSeries(series);
        return dataset;
    }
    private XYDataset CreateDataset4()throws Exception{
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Dollar/rub");
        if(sma) {
            TimeSeries series1 = new TimeSeries("SMA - 10");
            TimeSeries series2 = new TimeSeries("SMA - 100");
            ArrayList<Double> sma10 = SMA10();
            ArrayList<Double> sma100 = SMA100();
            float tmp = (float) cur.date.size() / sma100.size();
            int step = (int) Math.ceil(tmp);

            System.out.println(sma100.size() + "    " + cur.date.size());

            for (int i = 0; i < sma100.size() - 1; i += 250) {
                series2.add(Day.parseDay(cur.date.get(i).toString()), sma100.get(i));
            }
            for (int i = 0; i < sma10.size() - 1; i += 250) {
                series1.add(Day.parseDay(cur.date.get(i).toString()), sma10.get(i));
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);

        }
        for(int i = 0; i < cur.value.size()-1;i+=250){
            series.add(Day.parseDay(cur.date.get(i).toString()), cur.value.get(i));
        }
        series.add(Day.parseDay(cur.date.get(cur.value.size()-1).toString()), cur.value.get(cur.value.size()-1));
        dataset.addSeries(series);
        return dataset;
    }


    private ArrayList<Double> SMA10(){
        int count = 0;
        double sum = 0;
        ArrayList<Double> res = new ArrayList<>();
        for(int i = 0; i < cur.value.size(); i++){
            count++;
            sum+=cur.value.get(i);
            System.out.println(sum);
            if(count == 10){
                res.add(sum/10);
                sum-=cur.value.get(i-9);
                count = 9;
            }

        }
        return res;
    }

    private ArrayList<Double> SMA100(){
        int count = 0;
        double sum = 0;
        ArrayList<Double> res = new ArrayList<>();
        for(int i = 0; i<cur.value.size(); i++){
            count++;
            sum+=cur.value.get(i);
            System.out.println(sum);
            if(count == 100){
                res.add(sum/100);
                sum-=cur.value.get(i-99);
                count = 99;
            }

        }
        for(var a:res)
            System.out.println(a);
        return res;
    }

}
