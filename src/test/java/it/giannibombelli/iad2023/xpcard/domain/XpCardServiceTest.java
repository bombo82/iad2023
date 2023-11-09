package it.giannibombelli.iad2023.xpcard.domain;

import it.giannibombelli.iad2023.xpcard.repository.XpCardMongoRepository;
import it.giannibombelli.iad2023.xpcard.repository.XpCardTransactionMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class XpCardServiceTest {
    @Mock
    private XpCardMongoRepository xpCardRepository;
    @Mock
    private XpCardTransactionMongoRepository xpCardTransactionRepository;

    private XpCardService service;

    @BeforeEach
    void setUp() {
        service = new XpCardService(xpCardRepository, xpCardTransactionRepository);
    }

    @Test
    void emit() {
        final XpCard xpCard = service.emit(UUID.randomUUID(), 500, "emit service test");

        assertEquals(500, xpCard.getCurrentPoints());
        assertEquals("emit service test", xpCard.getNote());
    }

    @Test
    void get() {
        final UUID cardId = UUID.randomUUID();
        final XpCard expectedXpCard = new XpCard(cardId, 500, "get service test");
        when(xpCardRepository.getByCardId(cardId)).thenReturn(expectedXpCard);

        final XpCard xpCard = service.get(cardId);

        assertEquals(expectedXpCard, xpCard);
        assertEquals("get service test", xpCard.getNote());
    }

    @Test
    void list() {
        final UUID cardId1 = UUID.randomUUID();
        final UUID cardId2 = UUID.randomUUID();
        final List<XpCard> expectedXpCardList = List.of(
                new XpCard(cardId1, 500, "list service test [1]"),
                new XpCard(cardId2, 500, "list service test [2]")
        );
        when(xpCardRepository.findAll()).thenReturn(expectedXpCardList);

        final List<XpCard> xpCardList = service.list();

        assertEquals(expectedXpCardList, xpCardList);
    }

    @Test
    void gainPoints() {
        final UUID cardId = UUID.randomUUID();
        when(xpCardRepository.getByCardId(cardId)).thenReturn(new XpCard(cardId, 0, "gain service test"));

        service.gainPoints(cardId, 500, "Gains 500 XP points");

        verify(xpCardTransactionRepository, times(1)).save(any(XpCardTransaction.class));
    }

    @Test
    void redeemPoints_when_gained_points_is_enough() {
        final UUID cardId = UUID.randomUUID();
        when(xpCardRepository.getByCardId(cardId)).thenReturn(new XpCard(cardId, 500, "redeem service test"));

        service.redeemPoints(cardId, 400, "Spent 400 XP points to redeem eXtreme Programming explained");

        verify(xpCardTransactionRepository, times(1)).save(any(XpCardTransaction.class));
    }

    @Test
    void redeemPoints_when_gained_points_is_NOT_enough() {
        final UUID cardId = UUID.randomUUID();
        when(xpCardRepository.getByCardId(cardId)).thenReturn(new XpCard(cardId, 100, "redeem service test"));

        service.redeemPoints(cardId, 400, "Spent 400 XP points to redeem eXtreme Programming explained");

        verifyNoInteractions(xpCardTransactionRepository);
    }

    @Test
    void getTransactions() {
        final UUID cardId = UUID.randomUUID();
        final List<XpCardTransaction> expectedXpCardTransactionList = List.of(
                new XpCardTransaction(cardId, 500, "Gains 500 XP points", new Date())
        );
        when(xpCardTransactionRepository.getAllByCardId(cardId)).thenReturn(expectedXpCardTransactionList);

        final List<XpCardTransaction> xpCardTransactionList = service.getTransactions(cardId);

        assertEquals(expectedXpCardTransactionList, xpCardTransactionList);
    }

    @Test
    void sumPointsToCurrent() {
        assertEquals(1500, XpCardService.sumPointsToCurrent(500, 1000));
    }

    @Test
    void subtractPointsFromCurrent() {
        assertEquals(100, XpCardService.subtractPointsFromCurrent(400, 500));
    }
}
