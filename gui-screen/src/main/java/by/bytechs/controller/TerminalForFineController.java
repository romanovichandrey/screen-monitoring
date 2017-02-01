package by.bytechs.controller;

import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.TerminalForFineDtoService;
import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import by.bytechs.view.ATMPane;
import by.bytechs.view.PSTPane;
import by.bytechs.view.charts.LineChartAtmFine;
import by.bytechs.view.charts.LineChartPstFine;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller for work with fine ATM and PST.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
@Controller
public class TerminalForFineController {

    @Autowired
    private TerminalForFineDtoService terminalForFineDtoService;
    @Autowired
    private ATMPane atmPane;
    @Autowired
    private PSTPane pstPane;
    private static final Logger LOGGER = Logger.getLogger(TerminalForFineController.class);
    @Autowired
    private DateTimeUtilService dateTimeUtilService;
    @Autowired
    private LineChartAtmFine lineChartAtmFine;
    @Autowired
    private LineChartPstFine lineChartPstFine;
    @Autowired
    private DateTimeUtilService timeUtilService;

    /**
     * Initialization data from fine ATM and PST at start application (lineChartFine and numberFine).
     */
    public void init() {
        try {
            Map<String, Map<String, Map<String, TerminalForFineDto>>> map = terminalForFineDtoService.getTerminalForFineDtoByDates();
            Map<String, Map<String, TerminalForFineDto>> mapAtm = map.get("ATM");
            Map<String, Map<String, TerminalForFineDto>> mapPst = map.get("PST");

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

            ObservableList<String> listAxisPst = FXCollections.observableArrayList();
            ObservableList<String> listAxisAtm = FXCollections.observableArrayList();
            ObservableList<XYChart.Data<String, Number>> dataListPst = FXCollections.observableArrayList();
            ObservableList<XYChart.Data<String, Number>> dataListAtm = FXCollections.observableArrayList();

            for (Map.Entry<String, Map<String, TerminalForFineDto>> entry : mapAtm.entrySet()) {
                Map<String, TerminalForFineDto> atmMap = entry.getValue();
                int count = atmMap.entrySet().size();
                dataListAtm.add(new XYChart.Data<>(timeFormat.format(timeUtilService.
                        getDateByHourForward(dateFormat.parse(entry.getKey()))), count));
                listAxisAtm.add(timeFormat.format(timeUtilService.getDateByHourForward(dateFormat.parse(entry.getKey()))));
            }

            for (Map.Entry<String, Map<String, TerminalForFineDto>> entry : mapPst.entrySet()) {
                Map<String, TerminalForFineDto> pstMap = entry.getValue();
                int count = pstMap.entrySet().size();
                dataListPst.add(new XYChart.Data<>(timeFormat.format(timeUtilService.
                        getDateByHourForward(dateFormat.parse(entry.getKey()))), count));
                listAxisPst.add(timeFormat.format(timeUtilService.getDateByHourForward(dateFormat.parse(entry.getKey()))));
            }

            Map<String, TerminalForFineDto> fineMapAtm = mapAtm.get(dateFormat.format
                    (dateTimeUtilService.getDateByHourAgo(new Date())));
            Map<String, TerminalForFineDto> fineMapPst = mapPst.get(dateFormat.format
                    (dateTimeUtilService.getDateByHourAgo(new Date())));

            List<TerminalForFineDto> fineAtm = new ArrayList<>();
            List<TerminalForFineDto> finePst = new ArrayList<>();

            if (fineMapAtm != null) {
                for (Map.Entry<String, TerminalForFineDto> entry : fineMapAtm.entrySet()) {
                    fineAtm.add(entry.getValue());
                }
            }

            if (fineMapPst != null) {
                for (Map.Entry<String, TerminalForFineDto> entry : fineMapPst.entrySet()) {
                    finePst.add(entry.getValue());
                }
            }

            Platform.runLater(() -> lineChartAtmFine.init(dataListAtm, listAxisAtm));
            Platform.runLater(() -> lineChartPstFine.init(dataListPst, listAxisPst));

            Platform.runLater(() -> atmPane.initFine(fineAtm));
            Platform.runLater(() -> pstPane.initFine(finePst));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all TerminalForFineDto at start application: " + e);
        } catch (ParseException e) {
            LOGGER.error("ParseException: Cannot parse date: " + e);
        }
    }

    public void update() {
        try {
            Map<String, List<TerminalForFineDto>> fineMap = terminalForFineDtoService.getTerminalForFineDto();

            List<TerminalForFineDto> listAtm = fineMap.get("ATM");
            List<TerminalForFineDto> listPst = fineMap.get("PST");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            String time = simpleDateFormat.format(dateTimeUtilService.getDateByZeroMin(new Date()));

            Platform.runLater(() -> lineChartAtmFine.update(time, listAtm.size()));
            Platform.runLater(() -> lineChartPstFine.update(time, listPst.size()));

            Platform.runLater(() -> atmPane.updateFine(listAtm));
            Platform.runLater(() -> pstPane.updateFine(listPst));
        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all TerminalForFineDto at update application: " + e);
        }

    }
}
