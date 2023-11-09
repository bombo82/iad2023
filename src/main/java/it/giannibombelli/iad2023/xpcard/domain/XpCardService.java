package it.giannibombelli.iad2023.xpcard.domain;

import it.giannibombelli.iad2023.xpcard.repository.XpCardMongoRepository;
import it.giannibombelli.iad2023.xpcard.repository.XpCardTransactionMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class XpCardService {
    private final XpCardMongoRepository xpCardRepository;
    private final XpCardTransactionMongoRepository xpCardTransactionRepository;

    public XpCardService(XpCardMongoRepository xpCardRepository, XpCardTransactionMongoRepository xpCardTransactionRepository) {
        this.xpCardRepository = xpCardRepository;
        this.xpCardTransactionRepository = xpCardTransactionRepository;
    }

    static int sumPointsToCurrent(int points, int currentPoints) {
        return currentPoints + points;
    }

    static int subtractPointsFromCurrent(int points, int currentPoints) {
        return currentPoints - points;
    }

    public XpCard emit(UUID cardId, int initialPoints, String note) {
        final XpCard xpCard = new XpCard(cardId, initialPoints, note);
        xpCardRepository.save(xpCard);

        final String reason = "Emit XP Card with " + initialPoints + " points.";
        final XpCardTransaction transaction = new XpCardTransaction(cardId, initialPoints, reason, new Date());
        xpCardTransactionRepository.save(transaction);

        return xpCard;
    }

    public XpCard get(UUID cardId) {
        return xpCardRepository.getByCardId(cardId);
    }

    public List<XpCard> list() {
        return xpCardRepository.findAll();
    }

    public void gainPoints(UUID cardId, int points, String reason) {
        final XpCard xpCard = xpCardRepository.getByCardId(cardId);
        int currentPoints = xpCard.getCurrentPoints();
        xpCard.setCurrentPoints(sumPointsToCurrent(points, currentPoints));
        xpCardRepository.save(xpCard);

        final XpCardTransaction transaction = new XpCardTransaction(cardId, points, reason, new Date());
        xpCardTransactionRepository.save(transaction);
    }

    public void redeemPoints(UUID cardId, int points, String reason) {
        final XpCard xpCard = xpCardRepository.getByCardId(cardId);
        int currentPoints = xpCard.getCurrentPoints();
        if (currentPoints < points) {
            return;
        }
        xpCard.setCurrentPoints(subtractPointsFromCurrent(points, currentPoints));

        final XpCardTransaction transaction = new XpCardTransaction(cardId, -points, reason, new Date());
        xpCardTransactionRepository.save(transaction);
    }

    public List<XpCardTransaction> getTransactions(UUID cardId) {
        return xpCardTransactionRepository.getAllByCardId(cardId);
    }
}
