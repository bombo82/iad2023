package it.giannibombelli.iad2023.xpcard.controller;

public class XpCardRequest {
    private int initialPoints;
    private String note;

    public XpCardRequest() {
        super();
    }

    public int getInitialPoints() {
        return initialPoints;
    }

    public void setInitialPoints(int initialPoints) {
        this.initialPoints = initialPoints;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
