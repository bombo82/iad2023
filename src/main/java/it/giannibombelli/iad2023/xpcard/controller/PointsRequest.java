package it.giannibombelli.iad2023.xpcard.controller;

public class PointsRequest {
    private int points;
    private String reason;

    public PointsRequest() {
        super();
    }

    public int getPoints() {
        return points;
    }

    public String getReason() {
        return reason;
    }
}
