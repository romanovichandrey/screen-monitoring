package by.bytechs.view.charts;

import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;

import java.awt.*;

/**
 * @author Romanovich Andrei
 */
@org.springframework.stereotype.Component
public class LineChartPstFine extends Pane {
    private XYChart.Series<String, Number> series;
    private LineChart<String, Number> lineChartPst;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    public LineChartPstFine() {

    }

    public void init(ObservableList<XYChart.Data<String, Number>> dataList, ObservableList<String> axisList) {
        xAxis = new CategoryAxis();
        xAxis.setTickLabelFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 12));
        xAxis.setTickLabelFill(Color.BLUE);
        xAxis.setCategories(axisList);
        yAxis = new NumberAxis();
        yAxis.setTickLabelFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 12));
        yAxis.setTickLabelFill(Color.BLUE);
        lineChartPst = new LineChart<>(xAxis, yAxis);
        series = new XYChart.Series<>();
        series.setData(dataList);
        series.setName("Со штрафом за последний час");
        lineChartPst.getData().add(series);
        lineChartPst.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 40, 200);
        getChildren().add(lineChartPst);
    }

    public void update(String time, long count) {
        ObservableList<String> axisList = xAxis.getCategories();
        for (String s : axisList) {
            if (s.equals(time)) {
                axisList.remove(s);
                break;
            }
        }
        axisList.add(time);
        ObservableList<XYChart.Data<String, Number>> dataList = series.getData();
        for(XYChart.Data<String, Number> data : dataList) {
            if(data.getXValue().equals(time)) {
                dataList.remove(data);
                break;
            }
        }
        dataList.add(new XYChart.Data<String, Number>(time, count));
    }

}
