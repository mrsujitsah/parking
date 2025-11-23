package com.tuite.parking.dto;

public class ExitResponse {
    private boolean exited;
    private String message;
    private double fee;

    public ExitResponse(boolean exited, String message, double fee) {
        this.exited = exited;
        this.message = message;
        this.fee = fee;
    }

    public boolean isExited() {
        return exited;
    }

    public String getMessage() {
        return message;
    }

    public double getFee() {
        return fee;
    }
}
