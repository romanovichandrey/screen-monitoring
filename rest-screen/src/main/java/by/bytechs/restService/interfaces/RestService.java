package by.bytechs.restService.interfaces;

import by.bytechs.restService.exception.RestException;
import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This interface is used to request data from termWeb.
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface RestService {

    /**
     * Getting all StatCriticalMessageActual with termWeb by devices
     * @param devices
     * @return TransferMapDto
     * @throws RestException
     */
    List<StatCriticalMessageDto> getAllStatCriticalMessageActual(Integer[] devices) throws RestException;

    /**
     * Getting all ErrorDeviceInterval with termWeb by devices and time interval
     * @param time
     * @param devices
     * @return TransferMapDto
     * @throws RestException
     */
    List<StatCriticalMessageDto> getAllErrorDeviceInterval(String time, Integer[] devices) throws RestException;

    /**
     * Getting all TerminalForFineDto by time.
     * @param time
     * @return list TerminalForFineDto
     * @throws RestException
     */
    List<TerminalForFineDto> getAllTerminalFineByTime(String time) throws RestException;

    /**
     * Getting all TerminalForFineDto by list time.
     * @param time
     * @return map (String - ATM or PST, String - date format String, String - Id) TerminalForFineDto
     * @throws RestException
     */
    Map<String, Map<String, Map<String, TerminalForFineDto>>> getAllTerminalFineByListTime(String time) throws RestException;

    /**
     * Getting all PaymentDto by dateTime with termWeb.
     * @param dateTime
     * @return map (String - ATM or PST, Date - date payments) ScreenDataDto
     * @throws RestException
     */
    Map<String, Map<Date, List<PaymentDto>>> getAllPaymentPerDay(String dateTime) throws RestException;

    /**
     * Getting all PaymentDto by dateTime with termWeb in an hour.
     * @param dateTime
     * @return map (Boolean - true (payment), false(not payment))
     * @throws RestException
     */
    Map<Boolean, List<PaymentDto>> getAllPaymentInAnHour(String dateTime) throws RestException;

    /**
     * Getting all PaymentDto by dateTime with termWeb in an five minute.
     * @param dateTime
     * @return list
     * @throws RestException
     */
    List<PaymentDto> getAllPaymentInAnFiveMin(String dateTime) throws RestException;
}
