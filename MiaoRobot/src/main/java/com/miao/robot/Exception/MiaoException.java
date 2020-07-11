package com.miao.robot.Exception;

public class MiaoException extends Exception {
    private static final long serialVersionUID = 1L;
    protected String errCode;
    protected String errMsg;

    public MiaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MiaoException(String message) {
        super(message);
    }

    public MiaoException(Throwable cause) {
        super(cause);
        this.errCode = cause.toString();
        this.errMsg = cause.getMessage();
    }

    public MiaoException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
