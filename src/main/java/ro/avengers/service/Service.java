package ro.avengers.service;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;

import java.util.List;

public interface Service {

    // returns the planet list
    List<Planet> getPlanets();

    // returns the heroes list
    List<MarvelCharacter> getHeros();

    // returns the villains list
    List<MarvelCharacter> getVillains();

    /**
     * Store all the characters ID on a list based on a isVillain parameter
     *
     * @param isVillain if character si villain or not
     * @return a list of characters
     */
    List<Integer> getCharactersID(boolean isVillain);

    /**
     * Searches a hero in repository based on a give ID
     * @param id the hero ID
     * @return a hero if present or null if not
     */
    MarvelCharacter selectHero(int id);

    /**
     * Searches a villain in repository based on a give ID
     * @param id the villain ID
     * @return a villain if present or null if not
     */
    MarvelCharacter selectVillain(int id);

    /**
     * Searches a planet in repository based on a give ID
     * @param id the planet ID
     * @return a planet if present or null if not
     */
    Planet selectPlanet(int id);

}
