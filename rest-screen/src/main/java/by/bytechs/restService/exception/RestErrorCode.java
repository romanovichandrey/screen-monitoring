package by.bytechs.restService.exception;

/**
 * This enum is intended to store messages for RestException.
 * @author Romanovich Andrei
 * @version 1.0
 */
public enum  RestErrorCode {
    REST_ERROR_CODE_000("RestClientException: Cannot get all ScreenDataDto"),
    REST_ERROR_CODE_001("RestClientException: Cannot get all DataLineChartDto during the time interval"),
    REST_ERROR_CODE_002("RestClientException: Cannot get all StatCriticalMessageActual by devices"),
    REST_ERROR_CODE_003("RestClientException: Cannot get all ErrorDeviceInterval by devices and time"),
    REST_ERROR_CODE_004("RestClientException: Cannot get all TerminalForFineDto by time"),
    REST_ERROR_CODE_005("RestClientException: Cannot get all TerminalForFineDto by list time"),
    REST_ERROR_CODE_006("RestClientException: Cannot get all Payment per day"),
    REST_ERROR_CODE_007("RestClientException: Cannot get all Payment in an hour"),
    REST_ERROR_CODE_008("RestClientException: Cannot get all Payment in an five minute");

    private final String value;

    RestErrorCode(String value) {
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
