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
public class LineChartAtmFine extends Pane {
    private XYChart.Series<String, Number> series;
    private LineChart<String, Number> lineChartAtm;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    public LineChartAtmFine() {

    }

    public void init(ObservableList<XYChart.Data<String, Number>> dataList, ObservableList<String> axisList) {
        xAxis = new CategoryAxis();
        xAxis.setTickLabelFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 12));
        xAxis.setTickLabelFill(Color.BLUE);
        xAxis.setCategories(axisList);
        yAxis = new NumberAxis();
        yAxis.setTickLabelFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 12));
        yAxis.setTickLabelFill(Color.BLUE);
        lineChartAtm = new LineChart<>(xAxis, yAxis);
        lineChartAtm.setAnimated(true);
        series = new XYChart.Series<>();
        series.setName("Со штрафом за последний час");
        series.setData(dataList);
        lineChartAtm.getData().add(series);
        lineChartAtm.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 40, 200);
        getChildren().add(lineChartAtm);
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
