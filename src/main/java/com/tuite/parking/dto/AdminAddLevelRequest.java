package com.tuite.parking.dto;

import jakarta.validation.constraints.Min;

public class AdminAddLevelRequest {
    @Min(0)
    private int levelId;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
