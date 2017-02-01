package by.bytechs.restService.exception;

/**
 * This class handles all of an exception is encountered.
 * @author Romanovich Andrei
 * @version 1.0
 * @see Exception
 */
public class RestException extends Exception {
    private RestErrorCode restErrorCode;
    private Object[] params;
    private String message;

    public RestException(Exception e, RestErrorCode restErrorCode, Object... params) {
        super(e);
        this.restErrorCode = restErrorCode;
        this.params = params;
        message = String.format(restErrorCode.toString(), params);
    }

    public RestErrorCode getRestErrorCode() {
        return restErrorCode;
    }

    public void setRestErrorCode(RestErrorCode restErrorCode) {
        this.restErrorCode = restErrorCode;
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
