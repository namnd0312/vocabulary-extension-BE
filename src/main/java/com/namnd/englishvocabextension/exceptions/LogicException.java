package com.namnd.englishvocabextension.exceptions;

import com.namnd.englishvocabextension.enums.MessageEnum;

public class LogicException extends RuntimeException{
    MessageEnum messageEnum;
    private String code;
    private String message;

    public LogicException(String code, String message){
        this.message = message;
        this.code = code;
    }

   public LogicException(MessageEnum messageEnum){
        this.messageEnum = messageEnum;
    }

    public LogicException(){
        super();
    }

    public MessageEnum getMessageEnum() {
        return messageEnum;
    }

    public void setMessageEnum(MessageEnum messageEnum) {
        this.messageEnum = messageEnum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
