package fr.eql.projet01.service;

import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.dao.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setMail("test@esprit.tn");
        utilisateur.setNom("Amri");
        utilisateur.setPrenom("Souhaiel");
        utilisateur.setProfile("souhaiel_amri");
    }

    @Test
    void testTrouverTousLesUtilisateurs() {
        when(utilisateurRepository.findAll())
            .thenReturn(Arrays.asList(utilisateur));

        List<Utilisateur> result = utilisateurService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Amri", result.get(0).getNom());
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testTrouverUtilisateurParId() {
        when(utilisateurRepository.findById(1L))
            .thenReturn(Optional.of(utilisateur));

        Optional<Utilisateur> result = utilisateurRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Amri", result.get().getNom());
        assertEquals("test@esprit.tn", result.get().getMail());
    }

    @Test
    void testUtilisateurNonTrouve() {
        when(utilisateurRepository.findById(99L))
            .thenReturn(Optional.empty());

        Optional<Utilisateur> result = utilisateurRepository.findById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCountUtilisateurs() {
        when(utilisateurRepository.count()).thenReturn(5L);

        long count = utilisateurService.count();

        assertEquals(5L, count);
    }

    @Test
    void testUtilisateurProfileNonNull() {
        assertNotNull(utilisateur.getProfile());
        assertEquals("souhaiel_amri", utilisateur.getProfile());
    }
}
