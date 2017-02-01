package by.bytechs.service.exception;

/**
 * This class handles all of an exception is encountered.
 * @author Romanovich Andrei
 * @version 1.0
 * @see Exception
 */
public class ServiceException extends Exception {
    private ServiceErrorCode serviceErrorCode;
    private Object[] params;
    private String message;

    public ServiceException(Exception e, ServiceErrorCode serviceErrorCode, Object... params) {
        super(e);
        this.serviceErrorCode = serviceErrorCode;
        this.params = params;
        this.message = String.format(serviceErrorCode.toString(), params);
    }

    public ServiceErrorCode getServiceErrorCode() {
        return serviceErrorCode;
    }

    public void setServiceErrorCode(ServiceErrorCode serviceErrorCode) {
        this.serviceErrorCode = serviceErrorCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
