package com.tuite.parking.dto;

import java.util.Map;

public class StatusResponse {
    private Map<Integer, LevelStatus> levels;

    public StatusResponse(Map<Integer, LevelStatus> levels) {
        this.levels = levels;
    }

    public Map<Integer, LevelStatus> getLevels() {
        return levels;
    }

    public static class LevelStatus{
        private int totalSlots;
        private int occupiedSlots;
        private int freeSlots;

        public LevelStatus(int totalSlots, int occupiedSlots, int freeSlots) {
            this.totalSlots = totalSlots;
            this.occupiedSlots = occupiedSlots;
            this.freeSlots = freeSlots;
        }

        public int getTotalSlots() {
            return totalSlots;
        }

        public int getOccupiedSlots() {
            return occupiedSlots;
        }

        public int getFreeSlots() {
            return freeSlots;
        }
    }
}
