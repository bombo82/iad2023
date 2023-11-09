package it.giannibombelli.iad2023.xpcard.domain;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class XpCard {
    @Id
    private String internalId;

    private final UUID cardId;
    private int currentPoints;
    private String note;

    public XpCard(UUID cardId, int currentPoints, String note) {
        this.cardId = cardId;
        this.currentPoints = currentPoints;
        this.note = note;
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getNote() {
        return note;
    }
}
