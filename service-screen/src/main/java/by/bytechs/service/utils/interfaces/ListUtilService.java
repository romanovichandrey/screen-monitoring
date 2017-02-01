package by.bytechs.service.utils.interfaces;

import by.bytechs.screenMonitoringDto.PaymentDto;
import by.bytechs.screenMonitoringDto.StatCriticalMessageDto;
import by.bytechs.screenMonitoringDto.TerminalForFineDto;

import java.util.List;

/**
 * This interface for work with List.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface ListUtilService {

    /**
     * This method creates a list of unique device and terminalId.
     *
     * @param dtoList
     * @return list of unique device and terminalId.
     */
    List<StatCriticalMessageDto> utilStatCriticalMessageDto(List<StatCriticalMessageDto> dtoList);

    /**
     * This method creates a list of unique terminalId of device.
     *
     * @param dtoList
     * @param device  number device.
     * @return list of unique terminalId of device.
     */
    List<StatCriticalMessageDto> utilListDevice(List<StatCriticalMessageDto> dtoList, int device);

    /**
     * This method creates a numeric list with current payment.
     *
     * @param dtoList
     * @return list with current payment.
     */
    List<PaymentDto> utilListPayment(List<PaymentDto> dtoList);

    /**
     * This method creates a numeric list not payment.
     *
     * @param dtoList
     * @return list not payment
     */
    List<PaymentDto> utilListNotPayment(List<PaymentDto> dtoList);

    /**
     * This method creates a numeric list for fine.
     *
     * @param dtoList
     * @return numeric list for fine.
     */
    List<TerminalForFineDto> utilListFineTerminal(List<TerminalForFineDto> dtoList);

    /**
     * This method create count payment and convert to string.
     * @param dtoList
     * @return string count payment.
     */
    String utilListCountPayment(List<PaymentDto> dtoList);
}
