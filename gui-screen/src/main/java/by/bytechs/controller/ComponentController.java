package by.bytechs.controller;

import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.StatCriticalMessageDtoService;
import by.bytechs.service.utils.interfaces.ListUtilService;
import by.bytechs.view.AtmComponent;
import by.bytechs.view.PstComponent;
import javafx.application.Platform;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Controller for work statCriticalMessageDto ATM and PST.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
@Component
public class ComponentController {
    @Autowired
    private StatCriticalMessageDtoService statCriticalMessageDtoService;
    @Autowired
    private ListUtilService listUtilService;
    private static final Logger LOGGER = Logger.getLogger(ComponentController.class);
    @Autowired
    private AtmComponent atmComponent;
    @Autowired
    private PstComponent pstComponent;

    public void init() {
        try {
            Integer[] devices = {1, 2, 3, 6, 7, 12, 14, 16};
            Map<String, List<StatCriticalMessageDto>> mapMessage = statCriticalMessageDtoService.
                    getErrorStatCriticalMessageDto(devices);

            List<StatCriticalMessageDto> listAtm = listUtilService.utilStatCriticalMessageDto(mapMessage.get("ATM"));
            List<StatCriticalMessageDto> listPst = listUtilService.utilStatCriticalMessageDto(mapMessage.get("PST"));

            Integer[] resultDevices = {1, 2, 3, 4, 6, 7, 12, 14, 16, 22, 111};

            Map<Integer, String> mapDevicesAtm = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listAtm) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesAtm.put(i, String.valueOf(count));
            }

            Map<Integer, String> mapDevicesPst = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listPst) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesPst.put(i, String.valueOf(count));
            }

            Platform.runLater(() -> atmComponent.update(mapDevicesAtm, listAtm));
            Platform.runLater(() -> pstComponent.update(mapDevicesPst, listPst));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all StatCriticalMessageDto at start application: " + e);
        }

    }

    public void updateMin() {
        try {
            Integer[] devices = {1, 2, 3, 6, 7, 12, 14, 16};
            Map<String, List<StatCriticalMessageDto>> mapMessage = statCriticalMessageDtoService.
                    getStatCriticalMessageDto(devices);

            List<StatCriticalMessageDto> listAtm = listUtilService.utilStatCriticalMessageDto(mapMessage.get("ATM"));
            List<StatCriticalMessageDto> listPst = listUtilService.utilStatCriticalMessageDto(mapMessage.get("PST"));

            Integer[] resultDevices = {1, 2, 3, 4, 6, 7, 12, 14, 16, 22, 111};

            Map<Integer, String> mapDevicesAtm = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listAtm) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesAtm.put(i, String.valueOf(count));
            }

            Map<Integer, String> mapDevicesPst = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listPst) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesPst.put(i, String.valueOf(count));
            }

            Platform.runLater(() -> atmComponent.updateCurrent(mapDevicesAtm, listAtm));
            Platform.runLater(() -> pstComponent.updateCurrent(mapDevicesPst, listPst));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all StatCriticalMessageDto at update 5 min: " + e);
        }

    }

    public void updateHour() {
        try {
            Integer[] devices = {1, 2, 3, 6, 7, 12, 14, 16};
            Map<String, List<StatCriticalMessageDto>> mapMessage = statCriticalMessageDtoService.
                    getErrorStatCriticalMessageDto(devices);

            List<StatCriticalMessageDto> listAtm = listUtilService.utilStatCriticalMessageDto(mapMessage.get("ATM"));
            List<StatCriticalMessageDto> listPst = listUtilService.utilStatCriticalMessageDto(mapMessage.get("PST"));

            Integer[] resultDevices = {1, 2, 3, 4, 6, 7, 12, 14, 16, 22, 111};

            Map<Integer, String> mapDevicesAtm = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listAtm) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesAtm.put(i, String.valueOf(count));
            }

            Map<Integer, String> mapDevicesPst = new TreeMap<>();
            for (Integer i : resultDevices) {
                int count = 0;
                for (StatCriticalMessageDto dto : listPst) {
                    if (i == dto.getNumberOutOfService()) {
                        count += 1;
                    }
                }
                mapDevicesPst.put(i, String.valueOf(count));
            }

            Platform.runLater(() -> atmComponent.update(mapDevicesAtm, listAtm));
            Platform.runLater(() -> pstComponent.update(mapDevicesPst, listPst));

        } catch (ServiceException e) {
            LOGGER.error("ServiceException: Cannot get all StatCriticalMessageDto at update hour: " + e);
        }
    }
}
