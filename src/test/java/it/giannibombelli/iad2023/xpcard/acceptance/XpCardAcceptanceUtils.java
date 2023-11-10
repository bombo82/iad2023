package it.giannibombelli.iad2023.xpcard.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.giannibombelli.iad2023.xpcard.controller.XpCardFullResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class XpCardAcceptanceUtils {
    private final MockMvc mvc;

    public XpCardAcceptanceUtils(MockMvc mvc) {
        this.mvc = mvc;
    }

    public XpCardFullResponse createXPCard(Integer points, String reason) throws Exception {
        String requestBody = String.format("{\"initialPoints\": %d, \"note\": \"%s\"}", points, reason);
        final MvcResult mvcResult = mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(mvcResult.getResponse().getContentAsString(), XpCardFullResponse.class);
    }

    public void earnPoints(UUID cardId, Integer points, String reason) throws Exception {
        String requestBody = String.format("{\"points\": %d, \"reason\": \"%s\"}", points, reason);
        mvc.perform(post("/" + cardId + "/redeemPoints")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    public void redeem(UUID cardId, Integer points, String description) throws Exception {
        String requestBody = String.format(
            "{\"points\": %d, \"reason\": \"Spent %d XP points to redeem %s\"}",
            points, points, description
        );
        mvc.perform(post("/" + cardId + "/redeemPoints")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }
}
