package demo.liaopeixiong.cdccommon.exception;

public class CdcRuntimeException extends RuntimeException {

    public CdcRuntimeException(){
        super();
    }

    public CdcRuntimeException(Throwable cause) {
        super(cause);
    }

    public CdcRuntimeException(String message) {
        super(message);
    }
}
