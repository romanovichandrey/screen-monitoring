package by.bytechs.view;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.utils.interfaces.ListUtilService;
import by.bytechs.view.charts.LineChartATM;
import by.bytechs.view.charts.LineChartAtmFine;
import by.bytechs.view.tables.AtmTableNotPayment;
import by.bytechs.view.tables.AtmTablePaymentDto;
import by.bytechs.view.tables.AtmTableTerminalForFineDto;
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
public class ATMPane extends Pane {
    private Text namePane;
    private Text namePayment;
    private Text countPaymentMin;
    private Text countPaymentHour;
    private Text delimeter;
    private Text nameNotPayment;
    private Text countNotPayment;
    private Text nameFine;
    private Text countFine;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    @Autowired
    private LineChartATM lineChartATM;
    @Autowired
    private LineChartAtmFine lineChartAtmFine;
    @Autowired
    private AtmComponent atmComponent;
    @Autowired
    private ListUtilService listUtilService;

    public ATMPane() {
        namePane = new Text();
        namePayment = new Text();
        countPaymentMin = new Text();
        countPaymentHour = new Text();
        delimeter = new Text();
        nameNotPayment = new Text();
        countNotPayment = new Text();
        nameFine = new Text();
        countFine = new Text();
        setLayoutY(50);
        setLayoutX(width);
        setPrefSize(width, height);
    }

    public void init() {
        lineChartATM.setLayoutX(20);
        lineChartATM.setLayoutY(70);

        lineChartAtmFine.setLayoutX(20);
        lineChartAtmFine.setLayoutY(380);

        atmComponent.setLayoutX(0);
        atmComponent.setLayoutY(600);
        atmComponent.init();

        getChildren().addAll(namePane, namePayment, countPaymentMin, countPaymentHour, lineChartATM,
                delimeter, lineChartAtmFine, nameNotPayment, nameFine, countNotPayment, countFine, atmComponent);

        namePane.setLayoutX(width / 2 - 30);
        namePane.setLayoutY(8);
        namePane.setText("АТМ");
        namePane.setFont(Font.font("Helvetica", FontPosture.REGULAR, 25));
        namePayment.setLayoutX(20);
        namePayment.setLayoutY(50);
        namePayment.setText("Число выдач за 5 мин / предыдущий час");
        namePayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));

        countPaymentMin.setLayoutX(width - 200);
        countPaymentMin.setLayoutY(50);
        countPaymentMin.setText("0");
        countPaymentMin.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countPaymentMin.setFill(Color.BLUE);
        countPaymentHour.setLayoutX(width - 140);
        countPaymentHour.setLayoutY(50);
        countPaymentHour.setText("0");
        countPaymentHour.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countPaymentHour.setFill(Color.BLUE);

        delimeter.setLayoutY(50);
        delimeter.setLayoutX(width - 150);
        delimeter.setText("/");
        delimeter.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        delimeter.setFill(Color.BLACK);

        nameNotPayment.setLayoutX(20);
        nameNotPayment.setLayoutY(300);
        nameNotPayment.setText("Количество АТМ:\nбез операций за предыдущий час");
        nameNotPayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        countNotPayment.setLayoutX(width - 140);
        countNotPayment.setLayoutY(320);
        countNotPayment.setText("0");
        countNotPayment.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countNotPayment.setFill(Color.BLUE);

        nameFine.setLayoutX(20);
        nameFine.setLayoutY(340);
        nameFine.setText("со штрафом за предыдущий час");
        nameFine.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        countFine.setLayoutX(width - 140);
        countFine.setLayoutY(340);
        countFine.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        countFine.setFill(Color.BLUE);
    }

    public void initPayment(List<PaymentDto> dtoList) {
        countPaymentHour.setText(listUtilService.utilListCountPayment(dtoList));
        countPaymentHour.setOnMouseClicked(event1 -> Platform.runLater(() -> new AtmTablePaymentDto
                (listUtilService.utilListPayment(dtoList), "АТМ с числом выдач за предыдущий час").start(new Stage())));
        countNotPayment.setText(String.valueOf(listUtilService.utilListNotPayment(dtoList).size()));
        countNotPayment.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableNotPayment
                (listUtilService.utilListNotPayment(dtoList), "АТМ без операций за предыдущий час").start(new Stage())));
    }

    public void initFine(List<TerminalForFineDto> dtoList) {
        countFine.setText(String.valueOf(dtoList.size()));
        countFine.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableTerminalForFineDto
                (listUtilService.utilListFineTerminal(dtoList), "АТМ со штрафом за предыдущий час").start(new Stage())));
    }

    public void updatePaymentMin(String countPaymentMin, List<PaymentDto> dtoList) {
        this.countPaymentMin.setText(countPaymentMin);
        this.countPaymentMin.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTablePaymentDto
                (listUtilService.utilListPayment(dtoList), "АТМ с числом выдач за текущий час").start(new Stage())));
    }


    public void updatePaymentHour(String countPaymentHour, List<PaymentDto> paymentList, List<PaymentDto> notPaymentList) {
        this.countPaymentHour.setText(countPaymentHour);
        this.countPaymentHour.setOnMouseClicked(event1 -> Platform.runLater(() -> new AtmTablePaymentDto
                (listUtilService.utilListPayment(paymentList), "АТМ с числом выдач за предыдущий час").start(new Stage())));
        countNotPayment.setText(String.valueOf(notPaymentList.size()));
        countNotPayment.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableNotPayment
                (listUtilService.utilListNotPayment(notPaymentList), "АТМ без операций за предыдущий час").start(new Stage())));
    }

    public void updateFine(List<TerminalForFineDto> fineList) {
        countFine.setText(String.valueOf(fineList.size()));
        countFine.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableTerminalForFineDto
                (listUtilService.utilListFineTerminal(fineList), "АТМ со штрафом за предыдущий час").start(new Stage())));
    }
}
