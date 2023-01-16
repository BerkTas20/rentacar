package com.berktas.rentacar.core.util.message;

public class ResponseMessage { //fotoğraflar için yapıldı
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
