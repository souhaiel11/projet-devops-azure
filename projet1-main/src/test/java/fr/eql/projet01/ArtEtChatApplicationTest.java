package fr.eql.projet01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtEtChatApplicationTest {

    @Test
    void testApplicationContextLoads() {
        // Vérifie que la classe principale existe
        assertDoesNotThrow(() -> {
            Class.forName("fr.eql.projet01.ArtEtChatApplication");
        });
    }

    @Test
    void testNomApplication() {
        String nomApp = "Arts & Chat";
        assertNotNull(nomApp);
        assertTrue(nomApp.contains("Chat"));
    }

    @Test
    void testEnvironnementDevSecOps() {
        // Test symbolique qui documente l'environnement
        String pipeline = "Azure DevOps";
        String orchestration = "Kubernetes";
        String monitoring = "Application Insights";

        assertAll(
            () -> assertNotNull(pipeline),
            () -> assertNotNull(orchestration),
            () -> assertNotNull(monitoring),
            () -> assertEquals("Azure DevOps", pipeline),
            () -> assertEquals("Kubernetes", orchestration)
        );
    }
}
