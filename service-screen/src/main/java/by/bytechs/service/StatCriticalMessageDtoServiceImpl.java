package by.bytechs.service;

import by.bytechs.restService.exception.RestException;
import by.bytechs.restService.interfaces.RestService;
import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.service.exception.ServiceErrorCode;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.StatCriticalMessageDtoService;
import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementing StatCriticalMessageDtoService interface.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see StatCriticalMessageDtoService
 */
@Service
public class StatCriticalMessageDtoServiceImpl implements StatCriticalMessageDtoService {

    @Autowired
    private RestService restService;
    @Autowired
    private DateTimeUtilService dateTimeUtilService;

    @Override
    public Map<String, List<StatCriticalMessageDto>> getStatCriticalMessageDto(Integer[] devices) throws ServiceException {
        try {
            List<StatCriticalMessageDto> list = restService.getAllStatCriticalMessageActual(devices);
            return parsingListByPSTAndATM(list);
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_002);
        }
    }

    @Override
    public Map<String, List<StatCriticalMessageDto>> getErrorStatCriticalMessageDto(Integer[] devices) throws ServiceException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = simpleDateFormat.format(dateTimeUtilService.getDateByZeroMin(new Date()));
        try {
            List<StatCriticalMessageDto> list = restService.getAllErrorDeviceInterval(time, devices);
            return parsingListByPSTAndATM(list);
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_002);
        }
    }

    @Override
    public Map<String, List<StatCriticalMessageDto>> parsingListByPSTAndATM(List<StatCriticalMessageDto> dtoList) {
        Pattern pattern = Pattern.compile("(000).+");
        List<StatCriticalMessageDto> listATM = new ArrayList<>();
        List<StatCriticalMessageDto> listPST = new ArrayList<>();
        Map<String, List<StatCriticalMessageDto>> map = new TreeMap<>();
        for (StatCriticalMessageDto dto : dtoList) {
            Matcher matcher = pattern.matcher(dto.getIdTerm());
            if (matcher.matches()) {
                listATM.add(dto);
            } else {
                listPST.add(dto);
            }
        }
        map.put("ATM", listATM);
        map.put("PST", listPST);
        return map;
    }
}
