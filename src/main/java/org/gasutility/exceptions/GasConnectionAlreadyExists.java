package org.gasutility.exceptions;

public class GasConnectionAlreadyExists extends RuntimeException{

    public GasConnectionAlreadyExists(){super();}

    public GasConnectionAlreadyExists(String msg){super(msg);}
}
