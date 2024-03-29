package fr.uga.l3miage.spring.tp2.exo1.components;

import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundSongEntityException;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import fr.uga.l3miage.spring.tp2.exo1.models.SongEntity;
import fr.uga.l3miage.spring.tp2.exo1.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SongComponentTest {
    @Autowired
    private SongComponent songComponent;
    @MockBean
    private SongRepository songRepository;

    @Test
    void getSongEntityByIdNotFound(){
        //given
        SongEntity song = new SongEntity();
        song.setTitle("test");
        song.setArtistEntity(new ArtistEntity());
        song.setDuration(Duration.ofMinutes(3));
        songRepository.save(song);
        when(songRepository.findById(anyString())).thenReturn(Optional.empty());

        //when - then
        assertThrows(NotFoundSongEntityException.class, ()->songComponent.getSongEntityById("test"));
    }
}
