package by.bytechs.view;

import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.service.utils.interfaces.ListUtilService;
import by.bytechs.view.tables.AtmTableStatCriticalMessageDto;
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
import java.util.Map;

/**
 * @author Romanovich Andrei
 */
@Component
public class AtmComponent extends Pane {

    private Text outOfService, waitingCollection, soccet, keepAlive, timeClient, prr, idc, cdm, epp, host;
    private Text numberOutOfService, numberWaitingCollection, numberSoccet, numberKeepAlive, numberTimeClient,
            numberPrr, numberIdc, numberCdm, numberEpp, numberHost;
    private Text currentNumberOutOfService, currentNumberWaiting, currentNumberSoccet, currentNumberKeepAlive,
            currentNumberTimeClient, currentNumberHost, currentNumberPrr, currentNumberIdc, currentNumberCdm, currentNumberEpp;
    private Text delimeter1, delimeter2, delimeter3, delimeter4, delimeter5, delimeter6, delimeter7, delimeter8,
            delimeter9, delimeter10;
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    @Autowired
    private ListUtilService listUtilService;

    public AtmComponent() {
        outOfService = new Text();
        waitingCollection = new Text();
        soccet = new Text();
        keepAlive = new Text();
        timeClient = new Text();
        currentNumberTimeClient = new Text();
        prr = new Text();
        idc = new Text();
        cdm = new Text();
        epp = new Text();
        host = new Text();
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
        numberCdm = new Text();
        currentNumberCdm = new Text();
        numberEpp = new Text();
        currentNumberEpp = new Text();
        numberHost = new Text();
        currentNumberHost = new Text();
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
        getChildren().addAll(outOfService, waitingCollection, soccet, keepAlive, timeClient, prr, idc, cdm, epp,
                numberOutOfService, numberWaitingCollection, numberSoccet, numberKeepAlive, numberTimeClient, numberPrr,
                numberIdc, numberCdm, numberEpp, host, numberHost, currentNumberKeepAlive, currentNumberOutOfService,
                currentNumberSoccet, currentNumberWaiting, delimeter1, delimeter2, delimeter3, delimeter4, delimeter5,
                delimeter6, delimeter7, delimeter8, delimeter9, delimeter10, currentNumberTimeClient, currentNumberHost,
                currentNumberCdm, currentNumberEpp, currentNumberIdc, currentNumberPrr);
        initOutOfService();
        initWaitingCollection();
        initSoccet();
        initKeepAlive();
        initTimeClient();
        initHost();
        initPrr();
        initIdc();
        initCdm();
        initEpp();
    }

    public void initOutOfService() {
        outOfService.setLayoutX(20);
        outOfService.setLayoutY(20);
        outOfService.setText("в Out of service текущее / за предыдущий час");
        outOfService.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberOutOfService.setLayoutX(width - 200);
        currentNumberOutOfService.setLayoutY(20);
        currentNumberOutOfService.setText("0");
        currentNumberOutOfService.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberOutOfService.setFill(Color.BLUE);
        delimeter1.setLayoutX(width - 150);
        delimeter1.setLayoutY(20);
        delimeter1.setText("/");
        delimeter1.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberOutOfService.setLayoutX(width - 140);
        numberOutOfService.setLayoutY(20);
        numberOutOfService.setText("0");
        numberOutOfService.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberOutOfService.setFill(Color.BLUE);
    }

    public void initWaitingCollection() {
        waitingCollection.setLayoutX(20);
        waitingCollection.setLayoutY(60);
        waitingCollection.setText("в ожидании инкасации текущее / за предыдущий час");
        waitingCollection.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberWaiting.setLayoutX(width - 200);
        currentNumberWaiting.setLayoutY(60);
        currentNumberWaiting.setText("0");
        currentNumberWaiting.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberWaiting.setFill(Color.BLUE);
        delimeter2.setLayoutX(width - 150);
        delimeter2.setLayoutY(60);
        delimeter2.setText("/");
        delimeter2.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberWaitingCollection.setLayoutX(width - 140);
        numberWaitingCollection.setLayoutY(60);
        numberWaitingCollection.setText("0");
        numberWaitingCollection.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberWaitingCollection.setFill(Color.BLUE);
    }

    public void initSoccet() {
        soccet.setLayoutX(20);
        soccet.setLayoutY(100);
        soccet.setText("с ошибкой связи (ping) текущее / за предыдущий час");
        soccet.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberSoccet.setLayoutX(width - 200);
        currentNumberSoccet.setLayoutY(100);
        currentNumberSoccet.setText("0");
        currentNumberSoccet.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberSoccet.setFill(Color.BLUE);
        delimeter3.setLayoutX(width - 150);
        delimeter3.setLayoutY(100);
        delimeter3.setText("/");
        delimeter3.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberSoccet.setLayoutX(width - 140);
        numberSoccet.setLayoutY(100);
        numberSoccet.setText("0");
        numberSoccet.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberSoccet.setFill(Color.BLUE);
    }

    public void initKeepAlive() {
        keepAlive.setLayoutX(20);
        keepAlive.setLayoutY(140);
        keepAlive.setText("с отсутствием keep alive текущее / за предыдущий час");
        keepAlive.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberKeepAlive.setLayoutY(140);
        currentNumberKeepAlive.setLayoutX(width - 200);
        currentNumberKeepAlive.setText("0");
        currentNumberKeepAlive.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberKeepAlive.setFill(Color.BLUE);
        delimeter4.setLayoutX(width - 150);
        delimeter4.setLayoutY(140);
        delimeter4.setText("/");
        delimeter4.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberKeepAlive.setLayoutY(140);
        numberKeepAlive.setLayoutX(width - 140);
        numberKeepAlive.setText("0");
        numberKeepAlive.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberKeepAlive.setFill(Color.BLUE);
    }

    public void initTimeClient() {
        timeClient.setLayoutX(20);
        timeClient.setLayoutY(170);
        timeClient.setText("с временем обслуживания клиента свыше 20 мин текущее / за предыдуший час");
        timeClient.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberTimeClient.setLayoutY(170);
        currentNumberTimeClient.setLayoutX(width - 200);
        currentNumberTimeClient.setText("0");
        currentNumberTimeClient.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberTimeClient.setFill(Color.BLUE);
        delimeter5.setLayoutY(170);
        delimeter5.setLayoutX(width - 150);
        delimeter5.setText("/");
        delimeter5.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberTimeClient.setLayoutY(170);
        numberTimeClient.setLayoutX(width - 140);
        numberTimeClient.setText("0");
        numberTimeClient.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberTimeClient.setFill(Color.BLUE);
    }

    public void initHost() {
        host.setLayoutX(20);
        host.setLayoutY(200);
        host.setText("cвязь с HOST текущее / за предыдущий час");
        host.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberHost.setLayoutX(width - 200);
        currentNumberHost.setLayoutY(200);
        currentNumberHost.setText("0");
        currentNumberHost.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberHost.setFill(Color.BLUE);
        delimeter6.setLayoutY(200);
        delimeter6.setLayoutX(width - 150);
        delimeter6.setText("/");
        delimeter6.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberHost.setLayoutX(width - 140);
        numberHost.setLayoutY(200);
        numberHost.setText("0");
        numberHost.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberHost.setFill(Color.BLUE);
    }

    public void initPrr() {
        prr.setLayoutX(20);
        prr.setLayoutY(240);
        prr.setText("принтер текущее / за предыдущий час");
        prr.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberPrr.setLayoutY(240);
        currentNumberPrr.setLayoutX(width - 200);
        currentNumberPrr.setText("0");
        currentNumberPrr.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberPrr.setFill(Color.BLUE);
        delimeter7.setLayoutX(width - 150);
        delimeter7.setLayoutY(240);
        delimeter7.setText("/");
        delimeter7.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberPrr.setLayoutX(width - 140);
        numberPrr.setLayoutY(240);
        numberPrr.setText("0");
        numberPrr.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberPrr.setFill(Color.BLUE);
    }

    public void initIdc() {
        idc.setLayoutX(20);
        idc.setLayoutY(270);
        idc.setText("картридер текущее / за предыдущий час");
        idc.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberIdc.setLayoutX(width - 200);
        currentNumberIdc.setLayoutY(270);
        currentNumberIdc.setText("0");
        currentNumberIdc.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberIdc.setFill(Color.BLUE);
        delimeter8.setLayoutX(width - 150);
        delimeter8.setLayoutY(270);
        delimeter8.setText("/");
        delimeter8.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberIdc.setLayoutX(width - 140);
        numberIdc.setLayoutY(270);
        numberIdc.setText("0");
        numberIdc.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberIdc.setFill(Color.BLUE);
    }

    public void initCdm() {
        cdm.setLayoutX(20);
        cdm.setLayoutY(300);
        cdm.setText("диспенсер текущее / за предыдущий час");
        cdm.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberCdm.setLayoutX(width - 200);
        currentNumberCdm.setLayoutY(300);
        currentNumberCdm.setText("0");
        currentNumberCdm.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberCdm.setFill(Color.BLUE);
        delimeter9.setLayoutX(width - 150);
        delimeter9.setLayoutY(300);
        delimeter9.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        delimeter9.setText("/");
        numberCdm.setLayoutX(width - 140);
        numberCdm.setLayoutY(300);
        numberCdm.setText("0");
        numberCdm.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberCdm.setFill(Color.BLUE);
    }

    public void initEpp() {
        epp.setLayoutX(20);
        epp.setLayoutY(330);
        epp.setText("клавиатура текущее / за предыдущий час");
        epp.setFont(Font.font("Helvetica", FontPosture.REGULAR, 16));
        currentNumberEpp.setLayoutX(width - 200);
        currentNumberEpp.setLayoutY(330);
        currentNumberEpp.setText("0");
        currentNumberEpp.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        currentNumberEpp.setFill(Color.BLUE);
        delimeter10.setLayoutX(width - 150);
        delimeter10.setLayoutY(330);
        delimeter10.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        delimeter10.setText("/");
        numberEpp.setLayoutX(width - 140);
        numberEpp.setLayoutY(330);
        numberEpp.setText("0");
        numberEpp.setFont(Font.font("Helvetica", FontPosture.REGULAR, 20));
        numberEpp.setFill(Color.BLUE);
    }

    public void update(Map<Integer, String> mapDevice, List<StatCriticalMessageDto> dtoList) {
        numberOutOfService.setText(mapDevice.get(12));
        numberWaitingCollection.setText(mapDevice.get(22));
        numberSoccet.setText(mapDevice.get(14));
        numberKeepAlive.setText(mapDevice.get(111));
        numberTimeClient.setText(mapDevice.get(4));
        numberPrr.setText(mapDevice.get(1));
        numberIdc.setText(mapDevice.get(2));
        numberCdm.setText(mapDevice.get(7));
        numberEpp.setText(mapDevice.get(6));
        numberHost.setText(mapDevice.get(16));
        numberOutOfService.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 12), "АТМ в Out of service за предыдущий час").start(new Stage())));
        numberWaitingCollection.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 22), "АТМ в ожидании инкасации за предыдущий час").start(new Stage())));
        numberSoccet.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 14), "АТМ с ошибкой связи (ping) за предыдущий час").start(new Stage())));
        numberKeepAlive.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 111), "АТМ с отсутствием keep alive за предыдущий час").start(new Stage())));
        numberTimeClient.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 4), "АТМ с временем обслуживания клиента свыше 20 мин за предыдущий час").start(new Stage())));
        numberHost.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 16), "АТМ ошибка по связи с Host за предыдущий час").start(new Stage())));
        numberPrr.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 1), "АТМ ошибка по принтеру за предыдущий час").start(new Stage())));
        numberIdc.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 2), "АТМ ошибка по картридеру за предыдущий час").start(new Stage())));
        numberCdm.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 7), "АТМ ошибка по диспенсеру за предыдущий час").start(new Stage())));
        numberEpp.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 6), "АТМ ошибка по клавиатуре за предыдущий час").start(new Stage())));
    }

    public void updateCurrent(Map<Integer, String> mapDevice, List<StatCriticalMessageDto> dtoList) {
        currentNumberOutOfService.setText(mapDevice.get(12));
        currentNumberWaiting.setText(mapDevice.get(22));
        currentNumberSoccet.setText(mapDevice.get(14));
        currentNumberKeepAlive.setText(mapDevice.get(111));
        currentNumberTimeClient.setText(mapDevice.get(4));
        currentNumberHost.setText(mapDevice.get(16));
        currentNumberPrr.setText(mapDevice.get(1));
        currentNumberIdc.setText(mapDevice.get(2));
        currentNumberCdm.setText(mapDevice.get(7));
        currentNumberEpp.setText(mapDevice.get(6));
        currentNumberOutOfService.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 12), "АТМ в Out of service текущее").start(new Stage())));
        currentNumberWaiting.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 22), "АТМ в ожидании инкасации текущее").start(new Stage())));
        currentNumberSoccet.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 14), "АТМ с ошибкой связи (ping) текущее").start(new Stage())));
        currentNumberKeepAlive.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 111), "АТМ с отсутствием keep alive текущее").start(new Stage())));
        currentNumberTimeClient.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 4), "АТМ с временем обслуживания клиента свыше 20 мин текущее").start(new Stage())));
        currentNumberHost.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 16), "АТМ ошибка по связи с Host текущее").start(new Stage())));
        currentNumberPrr.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 1), "АТМ ошибка по принтеру текущее").start(new Stage())));
        currentNumberIdc.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 2), "АТМ ошибка по картридеру текущее").start(new Stage())));
        currentNumberCdm.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 7), "АТМ ошибка по диспенсеру текущее").start(new Stage())));
        currentNumberEpp.setOnMouseClicked(event -> Platform.runLater(() -> new AtmTableStatCriticalMessageDto
                (listUtilService.utilListDevice(dtoList, 6), "АТМ ошибка по клавиатуре текущее").start(new Stage())));
    }
}
