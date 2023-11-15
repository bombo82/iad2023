package it.giannibombelli.iad2023.xpcard.controller;

import it.giannibombelli.iad2023.xpcard.domain.XpCard;
import it.giannibombelli.iad2023.xpcard.domain.XpCardTransaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class XpCardFullResponse {

    private UUID cardId;
    private int currentPoints;
    private String note;
    private List<XpCardTransactionResponse> xpCardTransactions;

    public XpCardFullResponse() {
        super();
    }

    public XpCardFullResponse(XpCard xpCard) {
        cardId = xpCard.getCardId();
        currentPoints = xpCard.getCurrentPoints();
        note = xpCard.getNote();
    }

    public XpCardFullResponse(XpCard xpCard, List<XpCardTransaction> xpCardTransactions) {
        cardId = xpCard.getCardId();
        currentPoints = xpCard.getCurrentPoints();
        note = xpCard.getNote();
        this.xpCardTransactions = xpCardTransactions.stream().map(xpCardTransaction -> {
            XpCardTransactionResponse responseItem = new XpCardTransactionResponse();
            responseItem.setPoints(xpCardTransaction.getPoints());
            responseItem.setReason(xpCardTransaction.getReason());
            responseItem.setDate(xpCardTransaction.getDate());

            return responseItem;
        }).toList();
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

    public List<XpCardTransactionResponse> getXpCardTransactions() {
        return xpCardTransactions;
    }

    public static class XpCardTransactionResponse {
        private int points;
        private String reason;
        private Date date;

        public XpCardTransactionResponse() {
            super();
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

    }
}
