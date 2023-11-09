package it.giannibombelli.iad2023.xpcard.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.giannibombelli.iad2023.xpcard.XpCardApplication;
import it.giannibombelli.iad2023.xpcard.controller.XpCardFullResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = XpCardApplication.class)
@AutoConfigureMockMvc
class XpCardAcceptanceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void emit() throws Exception {
        mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"initialPoints\": 500}"))
                .andExpect(status().isOk());
    }

    @Test
    void listCard() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void getCard() throws Exception {
        final UUID cardId = createCardAndGetCardId();

        mvc.perform(get("/" + cardId)).andExpect(status().isOk());
    }

    @Test
    void addPoints() throws Exception {
        final UUID cardId = createCardAndGetCardId();

        mvc.perform(post("/" + cardId + "/addPoints")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"points\": 500, \"reason\": \"Gains 500 XP points\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void redeemPoints() throws Exception {
        final UUID cardId = createCardAndGetCardId();

        mvc.perform(post("/" + cardId + "/redeemPoints")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"points\": 400, \"reason\": \"Spent 400 XP points to redeem eXtreme Programming explained\"}"))
                .andExpect(status().isOk());
    }

    private UUID createCardAndGetCardId() throws Exception {
        final MvcResult mvcResult = mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"initialPoints\": 500}"))
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        final XpCardFullResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(), XpCardFullResponse.class);
        return response.getCardId();
    }
}
