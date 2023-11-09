package it.giannibombelli.iad2023.xpcard.controller;

import it.giannibombelli.iad2023.xpcard.domain.XpCard;

import java.util.UUID;

public class XpCardSummaryResponse {
    private final UUID cardId;
    private final int currentPoints;
    private final String note;

    public XpCardSummaryResponse(XpCard xpCard) {
        cardId = xpCard.getCardId();
        currentPoints = xpCard.getCurrentPoints();
        note = xpCard.getNote();
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public String getNote() {
        return note;
    }
}
