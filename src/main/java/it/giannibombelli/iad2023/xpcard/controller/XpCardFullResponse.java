package it.giannibombelli.iad2023.xpcard.controller;

import it.giannibombelli.iad2023.xpcard.domain.XpCard;
import it.giannibombelli.iad2023.xpcard.domain.XpCardTransaction;

import java.util.List;
import java.util.UUID;

public class XpCardFullResponse {

    private UUID cardId;
    private int currentPoints;
    private List<XpCardTransactionResponse> xpCardTransactions;

    public XpCardFullResponse() {
        super();
    }

    public XpCardFullResponse(XpCard xpCard) {
        cardId = xpCard.getCardId();
        currentPoints = xpCard.getCurrentPoints();
    }

    public XpCardFullResponse(XpCard xpCard, List<XpCardTransaction> xpCardTransactions) {
        cardId = xpCard.getCardId();
        currentPoints = xpCard.getCurrentPoints();
        this.xpCardTransactions = xpCardTransactions.stream().map(XpCardTransactionResponse::new).toList();
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public List<XpCardTransactionResponse> getXpCardTransactions() {
        return xpCardTransactions;
    }

    public class XpCardTransactionResponse {
        private final int points;
        private final String reason;

        public XpCardTransactionResponse(XpCardTransaction xpCardTransaction) {
            points = xpCardTransaction.getPoints();
            reason = xpCardTransaction.getReason();
        }

        public int getPoints() {
            return points;
        }

        public String getReason() {
            return reason;
        }
    }
}
