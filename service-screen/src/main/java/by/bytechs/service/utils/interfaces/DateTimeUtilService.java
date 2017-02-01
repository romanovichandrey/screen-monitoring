package by.bytechs.service.utils.interfaces;

import java.util.Date;

/**
 * This interface for work date.
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface DateTimeUtilService {

    /**
     * Return date an hour ago and zero min, sec, milisec.
     * @param date current date
     * @return date
     */
    Date getDateByHourAgo(Date date);

    /**
     * Return date an zero min, sec, milisec.
     * @param date current date.
     * @return date
     */
    Date getDateByZeroMin(Date date);

    /**
     * Return date an hour forward and zero min, sec, milisec.
     * @param date current date
     * @return date
     */
    Date getDateByHourForward(Date date);
}
