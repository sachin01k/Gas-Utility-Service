package org.gasutility.exceptions;

import javax.swing.*;

public class InvalidRequestType extends RuntimeException{

    public InvalidRequestType(String msg){
        super(msg);
    }
}
