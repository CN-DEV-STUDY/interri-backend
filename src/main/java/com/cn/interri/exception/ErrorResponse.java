package com.cn.interri.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private final HttpStatusCode httpStatusCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("errors")
    private List<CustomFieldError> customFieldErrors = new ArrayList<>();

    public ErrorResponse(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    @Builder
    public ErrorResponse(HttpStatusCode httpStatusCode, String message, List<FieldError> fieldErrors) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;

        //BindingResult.getFieldErrors() 메소드를 통해 전달받은 fieldErrors
        for (FieldError fieldError : fieldErrors) {
            this.customFieldErrors.add(
                    CustomFieldError.builder()
                            .field(fieldError.getField())
                            .value(fieldError.getRejectedValue())
                            .reason(fieldError.getDefaultMessage())
                            .build()
            );
        }
    }

    /**
     * parameter 검증에 통과하지 못한 필드가 담긴 클래스
     */
    @Builder
    @Getter
    public static class CustomFieldError {
        private String field;
        private Object value;
        private String reason;

    }
}
