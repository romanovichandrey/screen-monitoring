package by.bytechs.service.exception;

/**
 * This enum is intended to store messages for ServiceException.
 * @author Romanovich Andrei
 * @version 1.0
 */
public enum  ServiceErrorCode {
    SERVICE_ERROR_CODE_000("RestException: Cannot get all ScreenDataDto"),
    SERVICE_ERROR_CODE_001("RestException: Cannot get all DataLineChartDto by during the time interval"),
    SERVICE_ERROR_CODE_002("RestException: Cannot get all StatCriticalMessageActual by devices"),
    SERVICE_ERROR_CODE_003("RestException: Cannot get all TerminalForFineFto by time"),
    SERVICE_ERROR_CODE_004("RestException: Cannot get all TerminalForFineFto by list time"),
    SERVICE_ERROR_CODE_005("RestException: Cannot parse date and get all PaymentDto per day"),
    SERVICE_ERROR_CODE_006("RestException: Cannot parse date and get all PaymentDto in an hour"),
    SERVICE_ERROR_CODE_007("RestException: Cannot parse date and get all PaymentDto in an five minute");

    private final String value;

    ServiceErrorCode(String value) {
        this.value = value;
    }

    public boolean equalsValue(String message) {
        return message != null && value.equals(message);
    }

    @Override
    public String toString() {
        return value;
    }
}
