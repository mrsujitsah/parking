package com.tuite.parking.dto;

import com.tuite.parking.common.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AdminAddSlotRequest {

    @Min(0)
    private int levelId;
    @NotNull
    private VehicleType type;
    @Min(1)
    private int count;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
