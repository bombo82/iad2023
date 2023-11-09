package it.giannibombelli.iad2023.xpcard.controller;

import it.giannibombelli.iad2023.xpcard.XpCardApplication;
import it.giannibombelli.iad2023.xpcard.domain.XpCard;
import it.giannibombelli.iad2023.xpcard.domain.XpCardService;
import it.giannibombelli.iad2023.xpcard.domain.XpCardTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = XpCardApplication.class)
@AutoConfigureMockMvc
class XpCardControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private XpCardService service;

    @Test
    void emit() throws Exception {
        when(service.emit(any(), anyInt(), anyString())).thenReturn(new XpCard(UUID.randomUUID(), 500, "emit controller test"));

        mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"initialPoints\": 500, \"note\": \"emit controller test\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cardId").isNotEmpty())
            .andExpect(jsonPath("$.currentPoints").value(500))
            .andExpect(jsonPath("$.note").value("emit controller test"))
            .andExpect(jsonPath("$.xpCardTransactions").isEmpty());
    }

    @Test
    void listCard() throws Exception {
        when(service.list()).thenReturn(List.of(new XpCard(UUID.randomUUID(), 500, "listCard controller test")));

        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").isNotEmpty())
            .andExpect(jsonPath("$[0].cardId").isNotEmpty())
            .andExpect(jsonPath("$[0].currentPoints").value(500))
            .andExpect(jsonPath("$[0].note").value("listCard controller test"))
            .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void getCard() throws Exception {
        final UUID cardId = UUID.randomUUID();
        when(service.get(any())).thenReturn(new XpCard(cardId, 500, "getCard controller test"));
        when(service.getTransactions(any())).thenReturn(List.of(new XpCardTransaction(cardId, 500, "Gains 500 XP Points", new Date())));

        mvc.perform(get("/" + cardId))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cardId").isNotEmpty())
            .andExpect(jsonPath("$.currentPoints").value(500))
            .andExpect(jsonPath("$.note").value("getCard controller test"))
            .andExpect(jsonPath("$.xpCardTransactions").isArray())
            .andExpect(jsonPath("$.xpCardTransactions[0]").isNotEmpty())
            .andExpect(jsonPath("$.xpCardTransactions[0].points").value(500))
            .andExpect(jsonPath("$.xpCardTransactions[0].reason").value("Gains 500 XP Points"))
            .andExpect(jsonPath("$.xpCardTransactions[0].date").isNotEmpty())
            .andExpect(jsonPath("$.xpCardTransactions[1]").doesNotExist());
    }

    @Test
    void gainPoints() throws Exception {
        mvc.perform(post("/" + UUID.randomUUID() + "/addPoints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"points\": 500, \"reason\": \"Gains 500 XP points\"}"))
            .andExpect(status().isOk());
        verify(service).gainPoints(any(), eq(500), eq("Gains 500 XP points"));
    }

    @Test
    void redeemPoints() throws Exception {
        mvc.perform(post("/" + UUID.randomUUID() + "/redeemPoints")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"points\": 400, \"reason\": \"Spent 400 XP points to redeem eXtreme Programming explained\"}"))
            .andExpect(status().isOk());
        verify(service).redeemPoints(any(), eq(400), eq("Spent 400 XP points to redeem eXtreme Programming explained"));
    }
}
