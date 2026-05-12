package fr.eql.projet01.service;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.dao.PublicationRepository;
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
public class PublicationServiceTest {

    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private PublicationServiceImpl publicationService;

    private Publication publication;
    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Amri");

        publication = new Publication();
        publication.setId(1L);
        publication.setTitre("Test DevSecOps Pipeline");
        publication.setTexte("Publication de test pour le pipeline CI/CD Azure DevOps");
        publication.setUtilisateur(utilisateur);
    }

    @Test
    void testSauvegarderPublication() {
        when(publicationRepository.save(publication))
            .thenReturn(publication);

        Publication saved = publicationService.saveOrUpdate(publication);

        assertNotNull(saved);
        assertEquals("Test DevSecOps Pipeline", saved.getTitre());
        verify(publicationRepository, times(1)).save(publication);
    }

    @Test
    void testTrouverPublicationParId() {
        when(publicationRepository.findById(1L))
            .thenReturn(Optional.of(publication));

        Publication result = publicationService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test DevSecOps Pipeline", result.getTitre());
    }

    @Test
    void testTrouverPublicationsParUtilisateur() {
        when(publicationRepository.findByUtilisateur(utilisateur))
            .thenReturn(Arrays.asList(publication));

        List<Publication> result = publicationService.findPublicationByUser(utilisateur);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Amri", result.get(0).getUtilisateur().getNom());
        verify(publicationRepository, times(1)).findByUtilisateur(utilisateur);
    }

    @Test
    void testPublicationTitreNonNull() {
        assertNotNull(publication.getTitre());
        assertFalse(publication.getTitre().isEmpty());
    }

    @Test
    void testPublicationTexteNonNull() {
        assertNotNull(publication.getTexte());
        assertTrue(publication.getTexte().length() > 0);
    }
}
