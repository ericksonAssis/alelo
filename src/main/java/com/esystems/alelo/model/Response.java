package com.esystems.alelo.model;

public class Response {

    private int status;
    private String message;

    public Response(int status, String message){
        this.status = status;
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
