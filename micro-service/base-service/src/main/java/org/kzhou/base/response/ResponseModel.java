package org.kzhou.base.response;

public class ResponseModel {
    private Object payload;
    private String code;
    private String message;

    public Object getPayload() {
        return payload;
    }

    public ResponseModel setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseModel setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

}
