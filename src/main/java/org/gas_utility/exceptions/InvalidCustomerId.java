package org.gas_utility.exceptions;


public class InvalidCustomerId extends RuntimeException{

    public InvalidCustomerId(){
        super();
    }
    public InvalidCustomerId(String msg){
        super(msg);
    }
}
