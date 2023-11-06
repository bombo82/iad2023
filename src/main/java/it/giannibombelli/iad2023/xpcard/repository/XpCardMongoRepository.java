package it.giannibombelli.iad2023.xpcard.repository;

import it.giannibombelli.iad2023.xpcard.domain.XpCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface XpCardMongoRepository extends MongoRepository<XpCard, String> {
    XpCard getByCardId(UUID cardId);
}
