package br.com.moraesit.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Integer status;

    private OffsetDateTime timestamp;

    private String detail;

    private String userMessage;

    private List<ErrorObject> objects;

    @Getter
    @Builder
    public static class ErrorObject {
        private String name;
        private String userMessage;
    }
}