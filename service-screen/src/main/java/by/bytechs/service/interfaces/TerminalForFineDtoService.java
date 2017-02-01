package by.bytechs.service.interfaces;

import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * This interface is designed to handle the business logic of the application for getting TerminalForFineDto.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface TerminalForFineDtoService {

    /**
     * Getting all TerminalForFineDto by time.
     *
     * @return map PST or ATM -> List<TerminalForFineDto>
     * @throws ServiceException
     */
    Map<String, List<TerminalForFineDto>> getTerminalForFineDto() throws ServiceException;

    /**
     * Getting all TerminalForFineDto by list time.
     *
     * @return map PST or ATM -> List<TerminalForFineDto>
     * @throws ServiceException
     */
    Map<String, Map<String, Map<String, TerminalForFineDto>>> getTerminalForFineDtoByDates() throws ServiceException;

    /**
     * This method parsing TerminalForFineDto by PST and ATM.
     *
     * @param dtoList
     * @return map
     */
    Map<String, List<TerminalForFineDto>> parsingListByPSTAndATM(List<TerminalForFineDto> dtoList);
}
