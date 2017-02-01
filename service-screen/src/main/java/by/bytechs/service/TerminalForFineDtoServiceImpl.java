package by.bytechs.service;

import by.bytechs.restService.exception.RestException;
import by.bytechs.restService.interfaces.RestService;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.exception.ServiceErrorCode;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.TerminalForFineDtoService;
import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementing TerminalForFineDtoService interface.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see TerminalForFineDtoService
 */
@Service
public class TerminalForFineDtoServiceImpl implements TerminalForFineDtoService {

    private static final Logger LOGGER = Logger.getLogger(TerminalForFineDtoServiceImpl.class);
    @Autowired
    private RestService restService;
    @Autowired
    private DateTimeUtilService dateTimeUtilService;

    @Override
    public Map<String, List<TerminalForFineDto>> getTerminalForFineDto() throws ServiceException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            List<TerminalForFineDto> list = restService.getAllTerminalFineByTime(simpleDateFormat.format
                    (dateTimeUtilService.getDateByHourAgo(new Date())));
            LOGGER.info("Getting TerminalForFineDto by time: true");
            return parsingListByPSTAndATM(list);
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_003);
        }
    }

    @Override
    public Map<String, Map<String, Map<String, TerminalForFineDto>>> getTerminalForFineDtoByDates() throws ServiceException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        try {
            Map<String, Map<String, Map<String, TerminalForFineDto>>> map = restService.
                    getAllTerminalFineByListTime(simpleDateFormat.format(dateTimeUtilService.getDateByZeroMin(new Date())));
            LOGGER.info("Getting TerminalForFineDto by list time: true");
            return map;
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_004);
        }
    }

    @Override
    public Map<String, List<TerminalForFineDto>> parsingListByPSTAndATM(List<TerminalForFineDto> dtoList) {
        Pattern pattern = Pattern.compile("(000).+");
        List<TerminalForFineDto> listATM = new ArrayList<>();
        List<TerminalForFineDto> listPST = new ArrayList<>();
        Map<String, List<TerminalForFineDto>> map = new TreeMap<>();
        for (TerminalForFineDto dto : dtoList) {
            Matcher matcher = pattern.matcher(dto.getTerminalId());
            if (matcher.matches()) {
                listATM.add(dto);
            } else {
                listPST.add(dto);
            }
        }

        Map<String, TerminalForFineDto> mapAtm = new TreeMap<>();
        for (TerminalForFineDto dto : listATM) {
            if (!mapAtm.containsKey(dto.getTerminalId())) {
                mapAtm.put(dto.getTerminalId(), dto);
            }
        }

        List<TerminalForFineDto> fineAtmList = new ArrayList<>();
        for (Map.Entry<String, TerminalForFineDto> entry : mapAtm.entrySet()) {
            fineAtmList.add(entry.getValue());
        }

        Map<String, TerminalForFineDto> mapPst = new TreeMap<>();
        for (TerminalForFineDto dto : listPST) {
            if (!mapPst.containsKey(dto.getTerminalId())) {
                mapPst.put(dto.getTerminalId(), dto);
            }
        }

        List<TerminalForFineDto> finePstList = new ArrayList<>();
        for (Map.Entry<String, TerminalForFineDto> entry : mapPst.entrySet()) {
            finePstList.add(entry.getValue());
        }

        map.put("ATM", fineAtmList);
        map.put("PST", finePstList);
        return map;
    }
}
