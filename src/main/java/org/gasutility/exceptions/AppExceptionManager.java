package org.gasutility.exceptions;

import com.sun.mail.util.MailConnectException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.gasutility.model.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestControllerAdvice
public class AppExceptionManager {

    private Logger logger = (Logger) LoggerFactory.getLogger(AppExceptionManager.class);

    public ResponseEntity<String> sendResponse(String msg){
        logger.error(msg);
        return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value=InvalidCustomerId.class)
    public ResponseEntity<String> invalidCustomerId(InvalidCustomerId ice){
        return this.sendResponse(ice.getMessage());
    }

    @ExceptionHandler(value=InvalidRequestId.class)
    public ResponseEntity<String> invalidRequestId(InvalidRequestId ire){
        return this.sendResponse(ire.getMessage());
    }

    @ExceptionHandler(value=GasConnectionAlreadyExists.class)
    public ResponseEntity<String> gasConnectionAlreadyExists(GasConnectionAlreadyExists gcae){
        return this.sendResponse(gcae.getMessage());
    }

    @ExceptionHandler(value=InvalidGasConnectionRequestId.class)
    public ResponseEntity<String> invalidGasConnectionRequestId(InvalidGasConnectionRequestId igcri){
        return this.sendResponse(igcri.getMessage());
    }

    @ExceptionHandler(value=InvalidRequestType.class)
    public ResponseEntity<String> invalidRequestType(InvalidRequestType irt){
        return this.sendResponse(irt.getMessage());
    }

    @ExceptionHandler(value=DuplicatePhoneNumber.class)
    public ResponseEntity<String> duplicatePhoneNumber(DuplicatePhoneNumber dpn){
        return this.sendResponse(dpn.getMessage());
    }

    @ExceptionHandler(value=DuplicateEmail.class)
    public ResponseEntity<String> duplicateEmail(DuplicateEmail de){
        return this.sendResponse(de.getMessage());
    }

    @ExceptionHandler(value=DuplicateAadharNumber.class)
    public ResponseEntity<String> duplicateAadharNumber(DuplicateAadharNumber dan){
        return this.sendResponse(dan.getMessage());
    }

    @ExceptionHandler(value=MailConnectException.class)
    public ResponseEntity<String> mailNotSent(MailConnectException mce){
        return this.sendResponse(mce.getMessage());
    }

    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity<List<String>> constraintViolations(ConstraintViolationException cve){

        List<String> errors = cve.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());

        /*List<String> errors = manve.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors",errors);*/

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> exception(Exception e){
        return this.sendResponse(e.getMessage());
    }

}
