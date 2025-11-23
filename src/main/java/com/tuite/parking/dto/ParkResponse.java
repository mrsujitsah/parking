package com.tuite.parking.dto;

public class ParkResponse {
    private boolean parked;
    private String message;
    private Integer levelId;
    private Integer slotId;

    public ParkResponse(boolean parked, String message, Integer levelId, Integer slotId) {
        this.parked = parked;
        this.message = message;
        this.levelId = levelId;
        this.slotId = slotId;
    }

    public boolean isParked() {
        return parked;
    }

    public String getMessage() {
        return message;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public Integer getSlotId() {
        return slotId;
    }
}
