package by.bytechs.view;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.utils.interfaces.ListUtilService;
import by.bytechs.view.charts.LineChartPST;
import by.bytechs.view.charts.LineChartPstFine;
import by.bytechs.view.tables.PstTableNotPayment;
import by.bytechs.view.tables.PstTablePaymentDto;
import by.bytechs.view.tables.PstTableTerminalForFineDto;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
@Component
public class PSTPane extends Pane {

    private Text namePane;
    private Text namePayment;
    private Text countPaymentMin;
    private Text countPaymentHour;
    private Text delimiter;
    private Text nameNotPayment;
    private Text countNotPayment;
    private Text nameFine;
    private Text countFine;
    private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    @Autowired
    private LineChartPST lineChartsPST;
    @Autowired
    private LineChartPstFine lineChartPstFine;
    @Autowired
    private PstComponent pstComponent;
    @Autowired
    private ListUtilService listUtilService;

    public PSTPane() {
        namePane = new Text();
        namePayment = new Text();
        countPaymentMin = new Text();
        countPaymentHour = new Text();
        delimiter = new Text();
        nameNotPayment = new Text();
        countNotPayment = new Text();
        nameFine = new Text();
        countFine = new Text();
        setLayoutY(50);
        setLayoutX(0);
        setPrefSize(WIDTH, HEIGHT);
    }

    public void init() {
        getChildren().addAll(namePane, namePayment, countPaymentMin, countPaymentHour, delimiter, lineChartsPST,
                lineChartPstFine, nameNotPayment, nameFine, countNotPayment, countFine, pstComponent);

        lineChartsPST.setLayoutX(20);
        lineChartsPST.setLayoutY(70);

        lineChartPstFine.setLayoutX(20);
        lineChartPstFine.setLayoutY(380);

        pstComponent.setLayoutX(0);
        pstComponent.setLayoutY(600);
        pstComponent.init();

        namePane.setLayoutX(WIDTH / 2 - 30);
        namePane.setLayoutY(8);
        namePane.setText("ПСТ");
        namePane.setFont(Font.font("Helvetica", FontPosture.REGULAR, 25));
        namePayment.setLayoutX(20);
        namePayment.setLayoutY(50);
        namePayment.setText("Число платежей за 5 мин / предыдущий час");
        namePayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));

        countPaymentMin.setLayoutX(WIDTH - 200);
        countPaymentMin.setLayoutY(50);
        countPaymentMin.setText("0");
        countPaymentMin.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countPaymentMin.setFill(Color.BLUE);
        countPaymentHour.setLayoutX(WIDTH - 140);
        countPaymentHour.setLayoutY(50);
        countPaymentHour.setText("0");
        countPaymentHour.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countPaymentHour.setFill(Color.BLUE);

        delimiter.setLayoutX(WIDTH - 150);
        delimiter.setLayoutY(50);
        delimiter.setText("/");
        delimiter.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));

        nameNotPayment.setLayoutX(20);
        nameNotPayment.setLayoutY(300);
        nameNotPayment.setText("Количество ПСТ:\nбез операций за предыдущий час");
        nameNotPayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        countNotPayment.setLayoutX(WIDTH - 140);
        countNotPayment.setLayoutY(320);
        countNotPayment.setText("0");
        countNotPayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countNotPayment.setFill(Color.BLUE);

        nameFine.setLayoutX(20);
        nameFine.setLayoutY(340);
        nameFine.setText("со штрафом за предыдущий час");
        nameFine.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        countFine.setLayoutX(WIDTH - 140);
        countFine.setLayoutY(340);
        countFine.setText("0");
        countFine.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countFine.setFill(Color.BLUE);
    }

    public void initPayment(List<PaymentDto> dtoList) {
        countPaymentHour.setText(listUtilService.utilListCountPayment(dtoList));
        countPaymentHour.setOnMouseClicked(event1 -> Platform.runLater(() -> new PstTablePaymentDto
                (listUtilService.utilListPayment(dtoList), "ПСТ с числом платежей за предыдущий час").start(new Stage())));
        countNotPayment.setText(String.valueOf(listUtilService.utilListNotPayment(dtoList).size()));
        countNotPayment.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableNotPayment
                (listUtilService.utilListNotPayment(dtoList), "ПСТ без операций за предыдущий час").start(new Stage())));
    }

    public void initFine(List<TerminalForFineDto> dtoList) {
        countFine.setText(String.valueOf(dtoList.size()));
        countFine.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableTerminalForFineDto
                (listUtilService.utilListFineTerminal(dtoList), "ПСТ со штрафом за предыдущий час").start(new Stage())));
    }

    public void updatePaymentMin(String countPaymentMin, List<PaymentDto> dtoList) {
        this.countPaymentMin.setText(countPaymentMin);
        this.countPaymentMin.setOnMouseClicked(event -> Platform.runLater(() -> new PstTablePaymentDto
                (listUtilService.utilListPayment(dtoList), "ПСТ с числом платежей за текущий час").start(new Stage())));
    }


    public void updatePaymentHour(String countPaymentHour, List<PaymentDto> paymentList, List<PaymentDto> notPaymentList) {
        this.countPaymentHour.setText(countPaymentHour);
        this.countPaymentHour.setOnMouseClicked(event1 -> Platform.runLater(() -> new PstTablePaymentDto
                (listUtilService.utilListPayment(paymentList), "ПСТ с числом платежей за предыдущий час").start(new Stage())));
        countNotPayment.setText(String.valueOf(notPaymentList.size()));
        countNotPayment.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableNotPayment
                (listUtilService.utilListNotPayment(notPaymentList), "ПСТ без операций за предыдущий час").start(new Stage())));
    }

    public void updateFine(List<TerminalForFineDto> fineList) {
        countFine.setText(String.valueOf(fineList.size()));
        countFine.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableTerminalForFineDto
                (listUtilService.utilListFineTerminal(fineList), "ПСТ со штрафом за предыдущий час").start(new Stage())));
    }
}
