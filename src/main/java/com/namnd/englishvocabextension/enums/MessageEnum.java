package com.namnd.englishvocabextension.enums;

public enum MessageEnum {

    INTERNAL_API_ERROR("SEATELLER-500", "INTERNAL SERVER ERROR"),
    BAD_REQUEST("400", "BAD REQUEST!"),
    OK("00", "OK"),
    RECORD_NOT_FOUND("005", "Không tìm thấy bản ghi"),
    RECORD_NOT_EXIST("006", "Bản ghi đã tồn tại"),

    FIELD_NOT_MATCH("SEATELLER-007", "{0} FIELD IS NOT MATCH");

    private final String code;
    private final String message;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
