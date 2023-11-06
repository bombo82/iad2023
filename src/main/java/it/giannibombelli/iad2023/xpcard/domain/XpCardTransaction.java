package it.giannibombelli.iad2023.xpcard.domain;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class XpCardTransaction {
    @Id
    private String internalId;

    private final UUID cardId;
    private final int points;
    private final String reason;

    public XpCardTransaction(UUID cardId, int points, String reason) {
        this.cardId = cardId;
        this.points = points;
        this.reason = reason;
    }

    public int getPoints() {
        return points;
    }

    public String getReason() {
        return reason;
    }
}
