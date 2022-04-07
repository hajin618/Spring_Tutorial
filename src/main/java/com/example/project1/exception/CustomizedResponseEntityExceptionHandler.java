package com.example.project1.exception;

import com.example.project1.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice // 모든 controller 실행 될 때 사전에 실행
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)      // 이 메서드가 Exception Handler로 사용될 수 있음을 지칭
                                            // : 어떤 controller 클래스가 실행되더라도 이 adviceExceptionHandler 클래스가 실행
                                            // : 이 클래스 안에서 exception이 발생하게 되면 handleAllExceptions 메서드 실행됨
    // ResponseEntity: 사용자 객체 추가했을 때 반환시켰던 값                    어디서 발생했는지
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        // 500번 error
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // handleAllExceptions 는 일반화 되어있는 오류 처리
    // handleUserNotFoundException : 사용자가 존재하지 않을 때 발생하는 오류 처리
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        // not found (404 error)
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // 부모 클래스(ResponseEntityExceptionHandler)에 있는 메서드 재정의하여 사용
    // 유효성 검사에 문제가 생겼을 때 Bad Request 와 함께 오류메세지 출력을 위한 메서드
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,   // 발생한 exception의 객체
                                                                  HttpHeaders headers,                  // request 헤더값
                                                                  HttpStatus status,                    // status
                                                                  WebRequest request) {                 // 요청된 request
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
