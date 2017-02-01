package by.bytechs.service;

import by.bytechs.restService.exception.RestException;
import by.bytechs.restService.interfaces.RestService;
import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.service.exception.ServiceErrorCode;
import by.bytechs.service.exception.ServiceException;
import by.bytechs.service.interfaces.PaymentDtoService;
import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implementation PaymentDtoService.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see PaymentDtoService
 */
@Service
public class PaymentDtoServiceImpl implements PaymentDtoService {

    private final Logger logger = Logger.getLogger(PaymentDtoServiceImpl.class);
    @Autowired
    private RestService restService;
    @Autowired
    private DateTimeUtilService dateTimeUtilService;

    @Override
    public Map<String, Map<Date, List<PaymentDto>>> getAllPaymentPerDay(Date dateTime) throws ServiceException {
        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            logger.info("Parsing date and getting PaymentDto per day");
            return restService.getAllPaymentPerDay(formatDateTime.format(dateTimeUtilService.getDateByZeroMin(dateTime)));
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_005);
        }
    }

    @Override
    public Map<String, Map<Boolean, List<PaymentDto>>> getAllPaymentHour(Date dateTime) throws ServiceException {
        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            logger.info("Parsing date and getting PaymentDto in an hour");
            return utilPayment(restService.getAllPaymentInAnHour(formatDateTime.format
                    (dateTimeUtilService.getDateByZeroMin(dateTime))));
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_006);
        }
    }

    @Override
    public Map<String, List<PaymentDto>> getAllPaymentMin(Date dateTime) throws ServiceException {
        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            logger.info("Parsing date and getting PaymentDto in an min");
            return utilPaymentList(restService.getAllPaymentInAnFiveMin(formatDateTime.format
                    (dateTimeUtilService.getDateByZeroMin(dateTime))));
        } catch (RestException e) {
            throw new ServiceException(e, ServiceErrorCode.SERVICE_ERROR_CODE_007);
        }
    }

    /**
     * This method return map (String - ATM(PST), Boolean - true - payment(false - not payment)).
     *
     * @param map
     * @return map (String - ATM(PST), Boolean - true - payment(false - not payment)).
     */
    public Map<String, Map<Boolean, List<PaymentDto>>> utilPayment(Map<Boolean, List<PaymentDto>> map) {
        Map<String, Map<Boolean, List<PaymentDto>>> paymentMap = new TreeMap<>();

        Map<Boolean, List<PaymentDto>> atmMap = new TreeMap<>();
        Map<Boolean, List<PaymentDto>> pstMap = new TreeMap<>();

        Pattern p = Pattern.compile("(000).+");
        for (Map.Entry<Boolean, List<PaymentDto>> entry : map.entrySet()) {
            List<PaymentDto> atmList = new ArrayList<>();
            List<PaymentDto> pstList = new ArrayList<>();
            for (PaymentDto dto : entry.getValue()) {
                Matcher m = p.matcher(dto.getTerminalId());
                if (m.matches()) {
                    atmList.add(dto);
                } else {
                    pstList.add(dto);
                }
            }
            atmMap.put(entry.getKey(), atmList);
            pstMap.put(entry.getKey(), pstList);
        }

        paymentMap.put("ATM", atmMap);
        paymentMap.put("PST", pstMap);

        return paymentMap;
    }

    /**
     * Thid method return map (String - PST(ATM)).
     *
     * @param dtoList
     * @return map (String - PST(ATM)).
     */
    public Map<String, List<PaymentDto>> utilPaymentList(List<PaymentDto> dtoList) {
        List<PaymentDto> atmList = new ArrayList<>();
        List<PaymentDto> pstList = new ArrayList<>();
        Map<String, List<PaymentDto>> paymentMap = new TreeMap<>();

        Pattern p = Pattern.compile("(000).+");
        for (PaymentDto dto : dtoList) {
            Matcher m = p.matcher(dto.getTerminalId());
            if (m.matches()) {
                atmList.add(dto);
            } else {
                pstList.add(dto);
            }
        }

        paymentMap.put("ATM", atmList);
        paymentMap.put("PST", pstList);

        return paymentMap;
    }
}
