package by.bytechs.service.utils;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;
import by.bytechs.service.utils.interfaces.ListUtilService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This class implementation ListUtilService.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see ListUtilService
 */
@Service
public class ListUtilServiceImpl implements ListUtilService {

    @Override
    public List<StatCriticalMessageDto> utilStatCriticalMessageDto(List<StatCriticalMessageDto> dtoList) {
        List<StatCriticalMessageDto> resultList = new ArrayList<>();
        Map<Integer, Map<String, StatCriticalMessageDto>> map = new TreeMap<>();
        for (StatCriticalMessageDto dto : dtoList) {
            if (!map.containsKey(dto.getNumberOutOfService())) {
                Map<String, StatCriticalMessageDto> messageDtoMap = new TreeMap<>();
                messageDtoMap.put(dto.getIdTerm(), dto);
                map.put(dto.getNumberOutOfService(), messageDtoMap);
            } else {
                Map<String, StatCriticalMessageDto> messageDtoMap = map.get(dto.getNumberOutOfService());
                if (!messageDtoMap.containsKey(dto.getIdTerm())) {
                    messageDtoMap.put(dto.getIdTerm(), dto);
                    map.put(dto.getNumberOutOfService(), messageDtoMap);
                }
            }
        }
        for (Map.Entry<Integer, Map<String, StatCriticalMessageDto>> entry : map.entrySet()) {
            Map<String, StatCriticalMessageDto> messageDtoMap = entry.getValue();
            for (Map.Entry<String, StatCriticalMessageDto> messageDtoEntry : messageDtoMap.entrySet()) {
                resultList.add(messageDtoEntry.getValue());
            }
        }
        resultList.sort(Comparator.comparing(StatCriticalMessageDto::getLogicalNameTerminal));
        return resultList;
    }

    @Override
    public List<StatCriticalMessageDto> utilListDevice(List<StatCriticalMessageDto> dtoList, int device) {
        List<StatCriticalMessageDto> tempList = new ArrayList<>();
        List<StatCriticalMessageDto> resultList = new ArrayList<>();
        for (StatCriticalMessageDto dto : dtoList) {
            if (device == dto.getNumberOutOfService()) {
                tempList.add(dto);
            }
        }
        tempList.sort(Comparator.comparing(StatCriticalMessageDto::getLogicalNameTerminal));
        for (int i = 0; i < tempList.size(); i++) {
            StatCriticalMessageDto dto = tempList.get(i);
            dto.setNumberMessage(i + 1);
            resultList.add(dto);
        }
        resultList.sort(Comparator.comparing(StatCriticalMessageDto::getNumberMessage));
        return resultList;
    }

    @Override
    public List<PaymentDto> utilListPayment(List<PaymentDto> dtoList) {
        if (dtoList.isEmpty()) {
            return dtoList;
        }
        List<PaymentDto> resultList = new ArrayList<>();
        dtoList.sort(Comparator.comparing(PaymentDto::getTerminalLogicalName));
        int n = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            PaymentDto dto = dtoList.get(i);
            if (dto.getCountPayment() != null) {
                n += 1;
                dto.setConsecutiveNumber(n);
                resultList.add(dto);
            }
        }
        if (!resultList.isEmpty()) {
            resultList.sort(Comparator.comparing(PaymentDto::getConsecutiveNumber));
        }
        return resultList;
    }

    @Override
    public List<PaymentDto> utilListNotPayment(List<PaymentDto> dtoList) {
        List<PaymentDto> resultList = new ArrayList<>();
        dtoList.sort(Comparator.comparing(PaymentDto::getTerminalLogicalName));
        int n = 0;
        for (int i = 0; i < dtoList.size(); i++) {
            PaymentDto dto = dtoList.get(i);
            if (dto.getCountPayment() == null) {
                n += 1;
                dto.setConsecutiveNumber(n);
                resultList.add(dto);
            }
        }
        if (!resultList.isEmpty()) {
            resultList.sort(Comparator.comparing(PaymentDto::getConsecutiveNumber));
        }
        return resultList;
    }

    @Override
    public List<TerminalForFineDto> utilListFineTerminal(List<TerminalForFineDto> dtoList) {
        List<TerminalForFineDto> resultList = new ArrayList<>();
        dtoList.sort(Comparator.comparing(TerminalForFineDto::getTerminalLogicalName));
        for (int i = 0; i < dtoList.size(); i++) {
            TerminalForFineDto dto = dtoList.get(i);
            dto.setConsecutiveNumber(i + 1);
            resultList.add(dto);
        }
        resultList.sort(Comparator.comparing(TerminalForFineDto::getConsecutiveNumber));
        return resultList;
    }

    @Override
    public String utilListCountPayment(List<PaymentDto> dtoList) {
        long countPayment = 0;
        for(PaymentDto dto : dtoList) {
            if(dto.getCountPayment() != null) {
                countPayment += dto.getCountPayment();
            }
        }
        return String.valueOf(countPayment);
    }
}
