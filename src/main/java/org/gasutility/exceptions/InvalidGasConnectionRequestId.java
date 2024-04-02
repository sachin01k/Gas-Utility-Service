package org.gasutility.exceptions;

public class InvalidGasConnectionRequestId extends RuntimeException {
    public InvalidGasConnectionRequestId(String msg) {
        super(msg);
    }
}
