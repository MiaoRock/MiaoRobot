package com.miao.robot.Exception;

public class IllegalMiaoException extends MiaoException {

    public IllegalMiaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalMiaoException(String message) {
        super(message);
    }

    public IllegalMiaoException(Throwable cause) {
        super(cause);
        this.errCode = cause.toString();
        this.errMsg = cause.getMessage();
    }

    public IllegalMiaoException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
