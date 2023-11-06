package it.giannibombelli.iad2023.xpcard;

import it.giannibombelli.iad2023.xpcard.controller.XpCardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class XpCardApplicationTests {
    @Autowired
    private XpCardController xpCardController;

    @Test
    void contextLoads() {
        assertNotNull(xpCardController);
    }

}
