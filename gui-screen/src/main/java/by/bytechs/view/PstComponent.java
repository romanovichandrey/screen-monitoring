package by.bytechs.view;

import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.service.utils.interfaces.ListUtilService;
import by.bytechs.view.tables.PstTableStatCriticalMessageDto;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author Romanovich Andrei
 */
@org.springframework.stereotype.Component
public class PstComponent extends Pane {

    private Text outOfService, waitingCollection, soccet, keepAlive, timeClient, prr, idc, cim, epp;
    private Text numberOutOfService, numberWaitingCollection, numberSoccet, numberKeepAlive, numberTimeClient,
            numberPrr, numberIdc, numberCim, numberEpp;
    private Text currentNumberOutOfService, currentNumberWaiting, currentNumberSoccet, currentNumberKeepAlive,
            currentNumberTimeClient, currentNumberPrr, currentNumberIdc, currentNumberCim, currentNumberEpp;
    private Text delimeter1, delimeter2, delimeter3, delimeter4, delimeter5, delimeter6, delimeter7, delimeter8,
            delimeter9, delimeter10;
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    @Autowired
    private ListUtilService listUtilService;

    public PstComponent() {
        outOfService = new Text();
        waitingCollection = new Text();
        soccet = new Text();
        keepAlive = new Text();
        timeClient = new Text();
        currentNumberTimeClient = new Text();
        prr = new Text();
        idc = new Text();
        cim = new Text();
        epp = new Text();
        numberOutOfService = new Text();
        currentNumberOutOfService = new Text();
        numberWaitingCollection = new Text();
        currentNumberWaiting = new Text();
        numberSoccet = new Text();
        currentNumberSoccet = new Text();
        numberKeepAlive = new Text();
        currentNumberKeepAlive = new Text();
        numberTimeClient = new Text();
        numberPrr = new Text();
        currentNumberPrr = new Text();
        numberIdc = new Text();
        currentNumberIdc = new Text();
        numberCim = new Text();
        currentNumberCim = new Text();
        numberEpp = new Text();
        currentNumberEpp = new Text();
        delimeter1 = new Text();
        delimeter2 = new Text();
        delimeter3 = new Text();
        delimeter4 = new Text();
        delimeter5 = new Text();
        delimeter6 = new Text();
        delimeter7 = new Text();
        delimeter8 = new Text();
        delimeter9 = new Text();
        delimeter10 = new Text();
    }

    public void init() {
        setPrefSize(width, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        getChildren().addAll(outOfService, waitingCollection, soccet, keepAlive, timeClient, prr, idc, cim, epp,
                numberOutOfService, numberWaitingCollection, numberSoccet, numberKeepAlive, numberTimeClient, numberPrr,
                numberIdc, numberCim, numberEpp, currentNumberKeepAlive, currentNumberOutOfService,
                currentNumberSoccet, currentNumberWaiting, delimeter1, delimeter2, delimeter3, delimeter4, delimeter5,
                delimeter6, delimeter7, delimeter8, delimeter9, delimeter10, currentNumberTimeClient, currentNumberCim,
                currentNumberEpp, currentNumberIdc, currentNumberPrr);
        initOutOfService();
        initWaitingCollection();
        initSoccet();
        initKeepAlive();
        initTimeClient();
        initPrr();
        initIdc();
        initCdm();
        initEpp();
    }

    public void initOutOfService() {
        outOfService.setLayoutX(20);
        outOfService.setLayoutY(20);
        outOfService.setText("в Out of service текущее / за предыдущий час");
        outOfService.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberOutOfService.setLayoutX(width - 200);
        currentNumberOutOfService.setLayoutY(20);
        currentNumberOutOfService.setText("0");
        currentNumberOutOfService.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberOutOfService.setFill(javafx.scene.paint.Color.BLUE);
        delimeter1.setLayoutX(width - 150);
        delimeter1.setLayoutY(20);
        delimeter1.setText("/");
        delimeter1.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberOutOfService.setLayoutX(width - 140);
        numberOutOfService.setLayoutY(20);
        numberOutOfService.setText("0");
        numberOutOfService.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberOutOfService.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initWaitingCollection() {
        waitingCollection.setLayoutX(20);
        waitingCollection.setLayoutY(60);
        waitingCollection.setText("в ожидании инкасации текущее / за предыдущий час");
        waitingCollection.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberWaiting.setLayoutX(width - 200);
        currentNumberWaiting.setLayoutY(60);
        currentNumberWaiting.setText("0");
        currentNumberWaiting.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberWaiting.setFill(javafx.scene.paint.Color.BLUE);
        delimeter2.setLayoutX(width - 150);
        delimeter2.setLayoutY(60);
        delimeter2.setText("/");
        delimeter2.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberWaitingCollection.setLayoutX(width - 140);
        numberWaitingCollection.setLayoutY(60);
        numberWaitingCollection.setText("0");
        numberWaitingCollection.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberWaitingCollection.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initSoccet() {
        soccet.setLayoutX(20);
        soccet.setLayoutY(100);
        soccet.setText("с ошибкой связи (ping) текущее / за предыдущий час");
        soccet.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberSoccet.setLayoutX(width - 200);
        currentNumberSoccet.setLayoutY(100);
        currentNumberSoccet.setText("0");
        currentNumberSoccet.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberSoccet.setFill(javafx.scene.paint.Color.BLUE);
        delimeter3.setLayoutX(width - 150);
        delimeter3.setLayoutY(100);
        delimeter3.setText("/");
        delimeter3.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberSoccet.setLayoutX(width - 140);
        numberSoccet.setLayoutY(100);
        numberSoccet.setText("0");
        numberSoccet.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberSoccet.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initKeepAlive() {
        keepAlive.setLayoutX(20);
        keepAlive.setLayoutY(140);
        keepAlive.setText("с отсутсвием keep alive текущее / за предыдущий час");
        keepAlive.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberKeepAlive.setLayoutY(140);
        currentNumberKeepAlive.setLayoutX(width - 200);
        currentNumberKeepAlive.setText("0");
        currentNumberKeepAlive.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberKeepAlive.setFill(javafx.scene.paint.Color.BLUE);
        delimeter4.setLayoutX(width - 150);
        delimeter4.setLayoutY(140);
        delimeter4.setText("/");
        delimeter4.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberKeepAlive.setLayoutY(140);
        numberKeepAlive.setLayoutX(width - 140);
        numberKeepAlive.setText("0");
        numberKeepAlive.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberKeepAlive.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initTimeClient() {
        timeClient.setLayoutX(20);
        timeClient.setLayoutY(170);
        timeClient.setText("с временем обслуживания клиента свыше 20 мин текущее / за предыдуший час");
        timeClient.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberTimeClient.setLayoutY(170);
        currentNumberTimeClient.setLayoutX(width - 200);
        currentNumberTimeClient.setText("0");
        currentNumberTimeClient.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberTimeClient.setFill(javafx.scene.paint.Color.BLUE);
        delimeter5.setLayoutY(170);
        delimeter5.setLayoutX(width - 150);
        delimeter5.setText("/");
        delimeter5.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberTimeClient.setLayoutY(170);
        numberTimeClient.setLayoutX(width - 140);
        numberTimeClient.setText("0");
        numberTimeClient.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberTimeClient.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initPrr() {
        prr.setLayoutX(20);
        prr.setLayoutY(240);
        prr.setText("принтер текущее / за предыдущий час");
        prr.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberPrr.setLayoutY(240);
        currentNumberPrr.setLayoutX(width - 200);
        currentNumberPrr.setText("0");
        currentNumberPrr.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberPrr.setFill(javafx.scene.paint.Color.BLUE);
        delimeter7.setLayoutX(width - 150);
        delimeter7.setLayoutY(240);
        delimeter7.setText("/");
        delimeter7.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberPrr.setLayoutX(width - 140);
        numberPrr.setLayoutY(240);
        numberPrr.setText("0");
        numberPrr.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberPrr.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initIdc() {
        idc.setLayoutX(20);
        idc.setLayoutY(270);
        idc.setText("картридер текущее / за предыдущий час");
        idc.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberIdc.setLayoutX(width - 200);
        currentNumberIdc.setLayoutY(270);
        currentNumberIdc.setText("0");
        currentNumberIdc.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberIdc.setFill(javafx.scene.paint.Color.BLUE);
        delimeter8.setLayoutX(width - 150);
        delimeter8.setLayoutY(270);
        delimeter8.setText("/");
        delimeter8.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberIdc.setLayoutX(width - 140);
        numberIdc.setLayoutY(270);
        numberIdc.setText("0");
        numberIdc.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberIdc.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initCdm() {
        cim.setLayoutX(20);
        cim.setLayoutY(300);
        cim.setText("купюроприемник текущее / за предыдущий час");
        cim.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberCim.setLayoutX(width - 200);
        currentNumberCim.setLayoutY(300);
        currentNumberCim.setText("0");
        currentNumberCim.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberCim.setFill(javafx.scene.paint.Color.BLUE);
        delimeter9.setLayoutX(width - 150);
        delimeter9.setLayoutY(300);
        delimeter9.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        delimeter9.setText("/");
        numberCim.setLayoutX(width - 140);
        numberCim.setLayoutY(300);
        numberCim.setText("0");
        numberCim.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberCim.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void initEpp() {
        epp.setLayoutX(20);
        epp.setLayoutY(330);
        epp.setText("клавиатура текущее / за предыдущий час");
        epp.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberEpp.setLayoutX(width - 200);
        currentNumberEpp.setLayoutY(330);
        currentNumberEpp.setText("0");
        currentNumberEpp.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberEpp.setFill(javafx.scene.paint.Color.BLUE);
        delimeter10.setLayoutX(width - 150);
        delimeter10.setLayoutY(330);
        delimeter10.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        delimeter10.setText("/");
        numberEpp.setLayoutX(width - 140);
        numberEpp.setLayoutY(330);
        numberEpp.setText("0");
        numberEpp.setFont(javafx.scene.text.Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberEpp.setFill(javafx.scene.paint.Color.BLUE);
    }

    public void update(Map<Integer, String> mapDevice, List<StatCriticalMessageDto> dtoList) {
        numberOutOfService.setText(mapDevice.get(12));
        numberWaitingCollection.setText(mapDevice.get(22));
        numberSoccet.setText(mapDevice.get(14));
        numberKeepAlive.setText(mapDevice.get(111));
        numberTimeClient.setText(mapDevice.get(4));
        numberPrr.setText(mapDevice.get(1));
        numberIdc.setText(mapDevice.get(2));
        numberCim.setText(mapDevice.get(3));
        numberEpp.setText(mapDevice.get(6));
        numberOutOfService.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 12), "ПСТ в Out of service за предыдущий час").start(new Stage())));
        numberWaitingCollection.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 22), "ПСТ в ожидании инкасации за предыдущий час").start(new Stage())));
        numberSoccet.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 14), "ПСТ с ошибкой связи (ping) за предыдущий час").start(new Stage())));
        numberKeepAlive.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 111), "ПСТ с отсутсвием keep alive за предыдущий час").start(new Stage())));
        numberTimeClient.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 4), "ПСТ с временем обслуживания клиента свыше 20 мин за предыдущий час").start(new Stage())));
        numberPrr.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 1), "ПСТ с ошибкой принтера за предыдущий час").start(new Stage())));
        numberIdc.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 2), "ПСТ с ошибкой картридера за предыдущий час").start(new Stage())));
        numberCim.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 3), "ПСТ с ошибкой купюроприемника за предыдущий час").start(new Stage())));
        numberEpp.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 6), "ПСТ с ошибкой клавиатуры за предыдущий час").start(new Stage())));
    }

    public void updateCurrent(Map<Integer, String> mapDevice, List<StatCriticalMessageDto> dtoList) {
        currentNumberOutOfService.setText(mapDevice.get(12));
        currentNumberWaiting.setText(mapDevice.get(22));
        currentNumberSoccet.setText(mapDevice.get(14));
        currentNumberKeepAlive.setText(mapDevice.get(111));
        currentNumberTimeClient.setText(mapDevice.get(4));
        currentNumberPrr.setText(mapDevice.get(1));
        currentNumberIdc.setText(mapDevice.get(2));
        currentNumberCim.setText(mapDevice.get(3));
        currentNumberEpp.setText(mapDevice.get(6));
        currentNumberOutOfService.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 12), "ПСТ в Out of service текущее").start(new Stage())));
        currentNumberWaiting.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 22), "ПСТ в ожидании инкасации текущее").start(new Stage())));
        currentNumberSoccet.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 14), "ПСТ с ошибкой связи (ping) текущее").start(new Stage())));
        currentNumberKeepAlive.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 111), "ПСТ с отсутсвием keep alive текущее").start(new Stage())));
        currentNumberTimeClient.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 4), "ПСТ с временем обслуживания клиента свыше 20 мин текущее").start(new Stage())));
        currentNumberPrr.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 1), "ПСТ с ошибкой принтера текущее").start(new Stage())));
        currentNumberIdc.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 2), "ПСТ с ошибкой картридера текущее").start(new Stage())));
        currentNumberCim.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 3), "ПСТ с ошибкой купюроприемника текущее").start(new Stage())));
        currentNumberEpp.setOnMouseClicked(event -> Platform.runLater(() -> new PstTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 6), "ПСТ с ошибкой клавиатуры текущее").start(new Stage())));
    }
}
