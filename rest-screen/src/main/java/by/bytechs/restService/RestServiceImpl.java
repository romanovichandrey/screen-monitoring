package by.bytechs.restService;

import by.bytechs.restService.exception.RestErrorCode;
import by.bytechs.restService.exception.RestException;
import by.bytechs.restService.interfaces.RestService;
import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementation RestService interface.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see RestService
 */
@Service
public class RestServiceImpl implements RestService {
    private final Logger logger = Logger.getLogger(RestServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    @Qualifier("urlGetAllStatCriticalMessageActual")
    private String urlGetAllStatCriticalMessageActual;
    @Autowired
    @Qualifier("urlGetAllErrorDeviceInterval")
    private String urlGetAllErrorDeviceInterval;
    @Autowired
    @Qualifier(value = "urlGetAllTerminalForFineDtoByTime")
    private String urlGetAllTerminalForFineDtoByTime;
    @Autowired
    @Qualifier(value = "urlGetAllTerminalForFineDtoByListTime")
    private String urlGetAllTerminalForFineDtoByListTime;
    @Autowired
    @Qualifier(value = "urlGetAllPaymentPerDay")
    private String urlGetAllPaymentPerDay;
    @Autowired
    @Qualifier(value = "urlGetAllPaymentInAnHour")
    private String urlGetAllPaymentInAnHour;
    @Autowired
    @Qualifier(value = "urlGetAllPaymentInAnFiveMin")
    private String urlGetAllPaymentInAnFiveMin;


    @Override
    public List<StatCriticalMessageDto> getAllStatCriticalMessageActual(Integer[] devices) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Integer[]> entity = new HttpEntity<>(devices, httpHeaders);
            ResponseEntity<StatCriticalMessageDto[]> response = restTemplate.exchange(urlGetAllStatCriticalMessageActual,
                    HttpMethod.POST, entity, StatCriticalMessageDto[].class);
            List<StatCriticalMessageDto> list = Arrays.asList(response.getBody());
            logger.info("Getting all StatCriticalMessageActual by devices: true");
            return list;
        } catch (Exception e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_002);
        }
    }

    @Override
    public List<StatCriticalMessageDto> getAllErrorDeviceInterval(String time, Integer[] devices) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Integer[]> entity = new HttpEntity<>(devices, httpHeaders);
            ResponseEntity<StatCriticalMessageDto[]> response = restTemplate.exchange(urlGetAllErrorDeviceInterval,
                    HttpMethod.POST, entity, StatCriticalMessageDto[].class, time);
            List<StatCriticalMessageDto> list = Arrays.asList(response.getBody());
            logger.info("Getting all ErrorDeviceInterval by devices and time: true");
            return list;
        } catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_003);
        }
    }

    @Override
    public List<TerminalForFineDto> getAllTerminalFineByTime(String time) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<TerminalForFineDto[]> response = restTemplate.exchange(urlGetAllTerminalForFineDtoByTime,
                    HttpMethod.GET, entity, TerminalForFineDto[].class, time);
            List<TerminalForFineDto> list = Arrays.asList(response.getBody());
            logger.info("Getting all TerminalForFineDto by time: true");
            return list;
        } catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_004);
        }
    }

    @Override
    public Map<String, Map<String, Map<String, TerminalForFineDto>>> getAllTerminalFineByListTime(String time) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ParameterizedTypeReference<Map<String, Map<String, Map<String, TerminalForFineDto>>>> responseType = new
                    ParameterizedTypeReference<Map<String, Map<String, Map<String, TerminalForFineDto>>>>() {};
            ResponseEntity<Map<String, Map<String, Map<String, TerminalForFineDto>>>> response = restTemplate.exchange
                    (urlGetAllTerminalForFineDtoByListTime, HttpMethod.GET, entity, responseType, time);
            Map<String, Map<String, Map<String, TerminalForFineDto>>> map = response.getBody();
            logger.info("Getting all TerminalForFineDto by list time: true");
            return map;
        } catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_005);
        }
    }

    @Override
    public Map<String, Map<Date, List<PaymentDto>>> getAllPaymentPerDay(String dateTime) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ParameterizedTypeReference<Map<String, Map<Date, List<PaymentDto>>>> responseType = new
                    ParameterizedTypeReference<Map<String, Map<Date, List<PaymentDto>>>>() {};
            ResponseEntity<Map<String, Map<Date, List<PaymentDto>>>> responseEntity = restTemplate.exchange
                    (urlGetAllPaymentPerDay, HttpMethod.GET, entity, responseType, dateTime);
            Map<String, Map<Date, List<PaymentDto>>> paymentMap = responseEntity.getBody();
            logger.info("Getting all PaymentDto per day with termWeb: true");
            return paymentMap;
        }catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_006);
        }
    }

    @Override
    public Map<Boolean, List<PaymentDto>> getAllPaymentInAnHour(String dateTime) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ParameterizedTypeReference<Map<Boolean, List<PaymentDto>>> responseType = new
                    ParameterizedTypeReference<Map<Boolean, List<PaymentDto>>>() {};
            ResponseEntity<Map<Boolean, List<PaymentDto>>> responseEntity = restTemplate.exchange
                    (urlGetAllPaymentInAnHour, HttpMethod.GET, entity, responseType, dateTime);
            Map<Boolean, List<PaymentDto>> paymentMap = responseEntity.getBody();
            logger.info("Getting all PaymentDto in an hour with termWeb: true");
            return paymentMap;
        }catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_007);
        }
    }

    @Override
    public List<PaymentDto> getAllPaymentInAnFiveMin(String dateTime) throws RestException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ParameterizedTypeReference<List<PaymentDto>> responseType = new
                    ParameterizedTypeReference<List<PaymentDto>>() {};
            ResponseEntity<List<PaymentDto>> responseEntity = restTemplate.exchange
                    (urlGetAllPaymentInAnFiveMin, HttpMethod.GET, entity, responseType, dateTime);
            List<PaymentDto> paymentList = responseEntity.getBody();
            logger.info("Getting all PaymentDto in an five minute with termWeb: true");
            return paymentList;
        }catch (RestClientException e) {
            throw new RestException(e, RestErrorCode.REST_ERROR_CODE_008);
        }
    }
}
