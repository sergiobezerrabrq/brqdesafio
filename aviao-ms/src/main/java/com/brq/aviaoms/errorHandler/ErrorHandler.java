package com.brq.aviaoms.errorHandler;

import com.brq.aviaoms.exception.NotFoundException;
import com.brq.aviaoms.json.response.AviaoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * ErrorHandler for NotFoundException
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<AviaoResponse> notFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
