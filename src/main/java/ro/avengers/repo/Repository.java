package ro.avengers.repo;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;

import java.util.List;

public interface Repository {

    void loadData();

    /**
     * Returns a list of Planets from db
     * @return
     */
    List<Planet> getPlanets();

    /**
     * Returns a list of characters from db
     * @return
     */
    List<MarvelCharacter> getCharacters();

}
