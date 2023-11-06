package it.giannibombelli.iad2023.xpcard.domain;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class XpCard {
    @Id
    private String internalId;

    private final UUID cardId;

    private int currentPoints;

    public XpCard(UUID cardId, int currentPoints) {
        this.cardId = cardId;
        this.currentPoints = currentPoints;
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
}
