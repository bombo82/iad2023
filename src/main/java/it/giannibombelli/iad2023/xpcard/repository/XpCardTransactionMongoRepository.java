package it.giannibombelli.iad2023.xpcard.repository;

import it.giannibombelli.iad2023.xpcard.domain.XpCardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface XpCardTransactionMongoRepository extends MongoRepository<XpCardTransaction, String> {
    List<XpCardTransaction> getAllByCardId(UUID cardId);
}
