package org.gas_utility.exceptions;

public class InvalidGasConnectionRequestId extends RuntimeException {
    public InvalidGasConnectionRequestId(String msg) {
        super(msg);
    }
}
