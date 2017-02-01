package by.bytechs.service.utils;

import by.bytechs.service.utils.interfaces.DateTimeUtilService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * This class implementation DateTimeUtilService.
 * @author Romanovich Andrei
 * @version 1.0
 * @see DateTimeUtilService
 */
@Service
public class DateTimeUtilServiceImpl implements DateTimeUtilService {

    @Override
    public Date getDateByHourAgo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    @Override
    public Date getDateByZeroMin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDateByHourForward(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

}
