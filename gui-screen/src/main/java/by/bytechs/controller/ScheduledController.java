package by.bytechs.controller;

import by.bytechs.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Romanovich Andrei
 */
@Service
public class ScheduledController {
    @Autowired
    private ComponentController componentController;
    @Autowired
    private TerminalForFineController terminalForFineController;
    @Autowired
    private PaymentController paymentController;

    @Scheduled(initialDelay = (10 * 1000), fixedRate = (5 * (60 * 1000)) + (15 * 1000))
    public void poolScreenDataMin() throws ServiceException {
        paymentController.updateMin();
        componentController.updateMin();
    }

    @Scheduled(cron = "0 5 * * * *")
    public void poolScreenDataHour() throws ServiceException {
        paymentController.updateHour();
        terminalForFineController.update();
        componentController.updateHour();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void poolNullMin() {
        paymentController.nullPaymentMin();
    }
}
