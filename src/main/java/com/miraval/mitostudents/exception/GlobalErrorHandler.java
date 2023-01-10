package com.miraval.mitostudents.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    //Para todas las excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerAllException(Exception ex, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //PARA UNA EXCEPCION EN ESPECIFICO CREADO ANTERIORMENTE EN EL PROYECTO ejemplo
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    //Application.Properties manejo del 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    //Excepcion para validadores
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {

        //Concatenar varios errores con validators
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error->error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" "));

        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));

        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }
}
