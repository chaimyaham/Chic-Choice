package org.chicchoice.vetementservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VetementAlreadyExistsException extends RuntimeException{
    public VetementAlreadyExistsException(String message){
        super(message);
    }
}
