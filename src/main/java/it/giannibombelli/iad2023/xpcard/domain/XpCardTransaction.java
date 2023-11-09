package it.giannibombelli.iad2023.xpcard.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

public class XpCardTransaction {
    @Id
    private String internalId;

    private final UUID cardId;
    private final int points;
    private final String reason;
    private final Date date;

    public XpCardTransaction(UUID cardId, int points, String reason, Date date) {
        this.cardId = cardId;
        this.points = points;
        this.reason = reason;
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public String getReason() {
        return reason;
    }

    public Date getDate() {
        return date;
    }
}
