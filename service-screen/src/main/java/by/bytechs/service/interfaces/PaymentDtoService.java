package by.bytechs.service.interfaces;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.service.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This interface is designed for the work with PaymentDto
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface PaymentDtoService {

    /**
     * This method is designed for the getting PaymentDto per day.
     * @param dateTime (Start time pooling devices).
     * @return all PaymentDto per day.
     * @throws SerialException
     */
    Map<String, Map<Date, List<PaymentDto>>> getAllPaymentPerDay(Date dateTime) throws ServiceException;

    /**
     * Getting all PaymentDto in an hour.
     * @param dateTime
     * @return map
     * @throws ServiceException
     */
    Map<String, Map<Boolean, List<PaymentDto>>> getAllPaymentHour(Date dateTime) throws ServiceException;

    /**
     * Getting all PaymentDto in an five minut.
     * @param dateTime
     * @return map
     * @throws ServiceException
     */
    Map<String, List<PaymentDto>> getAllPaymentMin(Date dateTime) throws ServiceException;
}
