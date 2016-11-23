package org.ndshop.dbs.jpa.exception;


import org.ndshop.dbs.jpa.enums.RepExceptionType;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [dao层异自定义常]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class RepException extends RuntimeException {
    private RepExceptionType type = RepExceptionType.UNDEFINE;

    private RepException repException;

    public RepException(RepExceptionType repExceptionType, String msg) {
        super(msg);
        this.type = repExceptionType;
    }

    public RepException getRepException() {
        return repException;
    }

    public void setRepException(RepException repException) {
        this.repException = repException;
    }

    public RepExceptionType getType() {
        return type;
    }

    public void setType(RepExceptionType type) {
        this.type = type;
    }

}
