package it.giannibombelli.iad2023.xpcard.controller;

import it.giannibombelli.iad2023.xpcard.domain.XpCard;
import it.giannibombelli.iad2023.xpcard.domain.XpCardService;
import it.giannibombelli.iad2023.xpcard.domain.XpCardTransaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class XpCardController {

    XpCardService service;

    public XpCardController(XpCardService service) {
        this.service = service;
    }

    @PostMapping
    public XpCardFullResponse emit(@RequestBody XpCardRequest emitXpCardRequest) {
        final XpCard xpCard = service.emit(UUID.randomUUID(), emitXpCardRequest.getInitialPoints(), emitXpCardRequest.getNote());
        return new XpCardFullResponse(xpCard);
    }

    @GetMapping
    public List<XpCardSummaryResponse> listCard() {
        return this.service.list().stream().map(XpCardSummaryResponse::new).toList();
    }

    @GetMapping("/{cardId}")
    public XpCardFullResponse getCard(@PathVariable UUID cardId) {
        final XpCard xpCard = this.service.get(cardId);
        List<XpCardTransaction> xpCardTransactions = this.service.getTransactions(cardId);
        return new XpCardFullResponse(xpCard, xpCardTransactions);
    }

    @PostMapping("/{cardId}/addPoints")
    public void gainPoints(@PathVariable UUID cardId, @RequestBody PointsRequest gainPointsRequest) {
        service.gainPoints(cardId, gainPointsRequest.getPoints(), gainPointsRequest.getReason());
    }

    @PostMapping("/{cardId}/redeemPoints")
    public void redeemPoints(@PathVariable UUID cardId, @RequestBody PointsRequest redeemPointsRequest) {
        service.redeemPoints(cardId, redeemPointsRequest.getPoints(), redeemPointsRequest.getReason());
    }
}
