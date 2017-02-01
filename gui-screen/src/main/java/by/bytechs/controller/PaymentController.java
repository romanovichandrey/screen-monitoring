package by.bytechs.controller;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.PaymentDtoService;
import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import by.bytechs.view.ATMPane;
import by.bytechs.view.PSTPane;
import by.bytechs.view.charts.LineChartATM;
import by.bytechs.view.charts.LineChartPST;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller for work payment ATM and PST.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
@Controller
public class PaymentController {
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    @Autowired
    private PSTPane pstPane;
    @Autowired
    private ATMPane atmPane;
    @Autowired
    private PaymentDtoService paymentDtoService;
    @Autowired
    private DateTimeUtilService dateTimeUtilService;
    private static Long countATM;
    private static Long countPST;
    @Autowired
    private LineChartATM lineChartATM;
    @Autowired
    private LineChartPST lineChartPST;
    @Autowired
    private DateTimeUtilService timeUtilService;

    public void init() {
        try {
            Map<String, Map<Date, List<PaymentDto>>> map = paymentDtoService.getAllPaymentPerDay(new Date());

            Map<Date, List<PaymentDto>> mapAtm = map.get("ATM");
            Map<Date, List<PaymentDto>> mapPST = map.get("PST");

            ObservableList<XYChart.Data<String, Number>> dataListPst = FXCollections.observableArrayList();
            ObservableList<String> listAxisPst = FXCollections.observableArrayList();
            ObservableList<XYChart.Data<String, Number>> dataListAtm = FXCollections.observableArrayList();
            ObservableList<String> listAxisAtm = FXCollections.observableArrayList();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            for (Map.Entry<Date, List<PaymentDto>> entry : mapAtm.entrySet()) {
                listAxisAtm.add(simpleDateFormat.format(timeUtilService.getDateByHourForward(entry.getKey())));
                long countPayment = 0;
                for (PaymentDto dto : entry.getValue()) {
                    countPayment += (dto.getCountPayment() != null ? dto.getCountPayment() : 0);
                }
                dataListAtm.add(new XYChart.Data<>(simpleDateFormat.format(timeUtilService.getDateByHourForward(entry.getKey())),
                        countPayment));
            }

            for (Map.Entry<Date, List<PaymentDto>> entry : mapPST.entrySet()) {
                listAxisPst.add(simpleDateFormat.format(timeUtilService.getDateByHourForward(entry.getKey())));
                long countPayment = 0;
                for (PaymentDto dto : entry.getValue()) {
                    countPayment += (dto.getCountPayment() != null ? dto.getCountPayment() : 0);
                }
                dataListPst.add(new XYChart.Data<>(simpleDateFormat.format(timeUtilService.getDateByHourForward(entry.getKey())),
                        countPayment));
            }

            Platform.runLater(() -> lineChartPST.init(dataListPst, listAxisPst));
            Platform.runLater(() -> lineChartATM.init(dataListAtm, listAxisAtm));

            List<PaymentDto> currentPaymentAtm = mapAtm.get(dateTimeUtilService.getDateByHourAgo(new Date()));
            List<PaymentDto> currentPaymentPst = mapPST.get(dateTimeUtilService.getDateByHourAgo(new Date()));

            Platform.runLater(() -> pstPane.initPayment(currentPaymentPst));
            Platform.runLater(() -> atmPane.initPayment(currentPaymentAtm));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all PaymentDto at start application: " + e);
        }
    }

    public void updateMin() {
        try {
            Map<String, List<PaymentDto>> map = paymentDtoService.getAllPaymentMin(new Date());
            List<PaymentDto> listATM = map.get("ATM");
            List<PaymentDto> listPST = map.get("PST");

            long countOverallATM = 0;
            for (PaymentDto dto : listATM) {
                countOverallATM += dto.getCountPayment();
            }

            long countOverallPST = 0;
            for (PaymentDto dto : listPST) {
                countOverallPST += dto.getCountPayment();
            }

            long paymentAtm;
            if (countATM == null) {
                countATM = countOverallATM;
                paymentAtm = countATM;
            } else {
                paymentAtm = countOverallATM - countATM;
                countATM = countOverallATM;
            }

            long paymentPst;
            if (countPST == null) {
                countPST = countOverallPST;
                paymentPst = countPST;
            } else {
                paymentPst = countOverallPST - countPST;
                countPST = countOverallPST;
            }

            Platform.runLater(() -> atmPane.updatePaymentMin(String.valueOf(paymentAtm), listATM));
            Platform.runLater(() -> pstPane.updatePaymentMin(String.valueOf(paymentPst), listPST));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all Payment in an five min: " + e);
        }
    }

    public void updateHour() {
        try {
            Map<String, Map<Boolean, List<PaymentDto>>> paymentMap = paymentDtoService.getAllPaymentHour(new Date());

            Map<Boolean, List<PaymentDto>> atmMap = paymentMap.get("ATM");
            Map<Boolean, List<PaymentDto>> pstMap = paymentMap.get("PST");

            List<PaymentDto> paymentAtmList = atmMap.get(true);
            List<PaymentDto> notPaymentAtmList = atmMap.get(false);

            List<PaymentDto> paymentPstList = pstMap.get(true);
            List<PaymentDto> notPaymentPstList = pstMap.get(false);

            long countOverallATM = 0;
            for (PaymentDto dto : paymentAtmList) {
                countOverallATM += dto.getCountPayment() != null ? dto.getCountPayment() : 0;
            }
            final long finalCountOverallATM = countOverallATM;

            long countOverallPST = 0;
            for (PaymentDto dto : paymentPstList) {
                countOverallPST += dto.getCountPayment() != null ? dto.getCountPayment() : 0;
            }
            final long finalCountOverallPST = countOverallPST;

            Platform.runLater(() -> atmPane.updatePaymentHour(String.valueOf(finalCountOverallATM), paymentAtmList, notPaymentAtmList));
            Platform.runLater(() -> pstPane.updatePaymentHour(String.valueOf(finalCountOverallPST), paymentPstList, notPaymentPstList));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

            Platform.runLater(() -> lineChartATM.update(simpleDateFormat.format(dateTimeUtilService.
                    getDateByZeroMin(new Date())), finalCountOverallATM));

            Platform.runLater(() -> lineChartPST.update(simpleDateFormat.format(dateTimeUtilService.
                    getDateByZeroMin(new Date())), finalCountOverallPST));



        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all Payment in an hour: " + e);
        }
    }

    public void nullPaymentMin() {

        countATM = 0l;
        countPST = 0l;

        Platform.runLater(() -> atmPane.updatePaymentMin(String.valueOf(countATM), new ArrayList<>()));
        Platform.runLater(() -> pstPane.updatePaymentMin(String.valueOf(countPST), new ArrayList<>()));
    }
}
