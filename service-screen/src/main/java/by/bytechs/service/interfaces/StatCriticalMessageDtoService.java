package by.bytechs.service.interfaces;

import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * This interface is designed to handle the business logic of the application for getting StatCriticalMessageDto.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface StatCriticalMessageDtoService {

    /**
     * Getting all StatCriticalMessageDto by devices and create.
     *
     * @return map PST or ATM -> List<StatCriticalMessageDto>
     * @throws ServiceException
     */
    Map<String, List<StatCriticalMessageDto>> getStatCriticalMessageDto(Integer[] devices) throws ServiceException;

    /**
     * Getting all StatCriticalMessageDto by time and devices.
     *
     * @return map PST or ATM -> List<StatCriticalMessageDto>
     * @throws ServiceException
     */
    Map<String, List<StatCriticalMessageDto>> getErrorStatCriticalMessageDto(Integer[] devices) throws ServiceException;

    /**
     * This method parsing ScreenDataDtoList by PST and ATM.
     *
     * @param dtoList
     * @return map
     */
    Map<String, List<StatCriticalMessageDto>> parsingListByPSTAndATM(List<StatCriticalMessageDto> dtoList);
}
