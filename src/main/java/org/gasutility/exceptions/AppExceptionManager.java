package org.gasutility.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AppExceptionManager {

    private Logger logger = (Logger) LoggerFactory.getLogger(AppExceptionManager.class);

    @ExceptionHandler(value=InvalidCustomerId.class)
    public ResponseEntity<String> invalidCustomerId(InvalidCustomerId ice){

        String msg = ice.getMessage();
        logger.error(msg);
        return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value=InvalidRequestId.class)
    public ResponseEntity<String> invalidRequestId(InvalidRequestId ire){

        String msg = ire.getMessage();
        logger.error(msg);
        return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value=GasConnectionAlreadyExists.class)
    public ResponseEntity<String> gasConnectionAlreadyExists(GasConnectionAlreadyExists gcae){
        String msg = gcae.getMessage();
        logger.error(msg);
        return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> exception(Exception e){

        String msg = e.getMessage();
        logger.error(msg);
        return new ResponseEntity<>(msg,HttpStatus.FORBIDDEN);
    }

}
