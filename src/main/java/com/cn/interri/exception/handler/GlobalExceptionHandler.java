package com.cn.interri.exception.handler;

import com.cn.interri.exception.enums.CommonErrorCode;
import com.cn.interri.exception.CustomException;
import com.cn.interri.exception.ErrorCode;
import com.cn.interri.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * https://bamdule.tistory.com/92
 * https://mangkyu.tistory.com/205
 *
 *  예외의 순서 중요!!!
 *  새로운 예외는 제일 위에 추가
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * @Valid 검증 실패 시 Catch
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleIllegalArgument", e);

        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, errorCode);
    }

    /**
     * ResponseEntityExceptionHandler에 정의된 모든 예외를 ErrorResponse로 바꾸기 위한 메소드
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleExceptionInternal", e);

        ErrorResponse response = ErrorResponse.builder()
                .httpStatus(status)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException", e);

        ErrorResponse response = ErrorResponse.builder()
                .httpStatus(e.getErrorCode().getHttpStatus())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    /**
     * 모든 예외를 핸들링하여 ErrorResponse 형식으로 반환
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);

        ErrorResponse response = new ErrorResponse(
                CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
                CommonErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
        return ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .message(errorCode.getMessage())
                .fieldErrors(e.getBindingResult().getFieldErrors())
                .build();
    }
}
