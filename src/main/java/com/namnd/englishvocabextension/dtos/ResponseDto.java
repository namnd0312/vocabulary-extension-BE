package com.namnd.englishvocabextension.dtos;

import com.namnd.englishvocabextension.enums.MessageEnum;

import java.time.Instant;

/**
 * @author nam.nd
 * @created 17/08/2021 - 11:19 PM
 */

public class ResponseDto {
    private String code;

    private String message;

    private Instant time;

    MessageEnum messageEnum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public ResponseDto() {
    }

    public ResponseDto(String code, String message, Instant time) {
        this.code = code;
        this.message = message;
        this.time = time;
    }

    public ResponseDto(MessageEnum messageEnum) {
        this.code = messageEnum.getCode();
        this.message = messageEnum.getMessage();
        this.time = Instant.now();
    }
}
