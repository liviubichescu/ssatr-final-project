package ro.avengers.service;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;

import java.util.List;

public interface Attack {

    /**
     * Process a fight between a hero and a villain on a planet
     * @param planet
     * @param hero
     * @param enemy
     * @return the character who won
     */
    MarvelCharacter heroVsVillain(Planet planet, MarvelCharacter hero, MarvelCharacter enemy);

    /**
     * Process a fight between an Avenger Team(List of heroes) and a villain on a planet
     */
    List<MarvelCharacter> avengersVsVillain(Planet planet, List<MarvelCharacter> avengers, MarvelCharacter villain);

}
