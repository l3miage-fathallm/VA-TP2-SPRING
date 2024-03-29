package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;
    @Test
    void testCountByGenreMusicalEquals(){
        //given
        ArtistEntity artist1 = new ArtistEntity();
        artist1.setName("Mark");
        artist1.setGenreMusical(GenreMusical.CLASSIC);
        artist1.setBiography("biography de l'artist 1");
        artistRepository.save(artist1);

        ArtistEntity artist2 = new ArtistEntity();
        artist2.setName("Omar");
        artist2.setGenreMusical(GenreMusical.RAP);
        artist2.setBiography("biography de l'artist 2");
        artistRepository.save(artist2);

        ArtistEntity artist3 = new ArtistEntity();
        artist3.setName("Paul");
        artist3.setGenreMusical(GenreMusical.CLASSIC);
        artist3.setBiography("biography de l'artist 3");
        artistRepository.save(artist3);

        //when
        int artistEntitiesClassic = artistRepository.countByGenreMusicalEquals(GenreMusical.CLASSIC);
        int artistEntitiesRap = artistRepository.countByGenreMusicalEquals(GenreMusical.RAP);
        int artistEntitiesSlam = artistRepository.countByGenreMusicalEquals(GenreMusical.SLAM);



        //then
        assertEquals(2, artistEntitiesClassic);
        assertEquals(1, artistEntitiesRap);
        assertEquals(0, artistEntitiesSlam);



    }
}
