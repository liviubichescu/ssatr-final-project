package ro.avengers.service;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;
import ro.avengers.repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ServiceImpl implements Service, Attack {

    public static int rounds = 0;
    private Repository repository;

    public ServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Planet> getPlanets() {
        return repository.getPlanets();
    }

    @Override
    public List<MarvelCharacter> getHeros() {
        return this.repository.getCharacters()
                .stream()
                .filter(marvelCharacter -> !marvelCharacter.isVillain())
                .collect(Collectors.toList());
    }

    @Override
    public List<MarvelCharacter> getVillains() {
        return this.repository.getCharacters()
                .stream()
                .filter(MarvelCharacter::isVillain)
                .collect(Collectors.toList());
    }


    @Override
    public MarvelCharacter heroVsVillain(Planet planet, MarvelCharacter hero, MarvelCharacter villain) {
        // apply modifiers on villain and hero based on the planet
        applyPlanetModifiers(planet, hero);
        applyPlanetModifiers(planet, villain);

        fight(villain, hero);

        if (hero.getHealth() > 0)
            return hero;

        return villain;
    }

    @Override
    public List<MarvelCharacter> avengersVsVillain(Planet planet, List<MarvelCharacter> avengers, MarvelCharacter villain) {
        List<MarvelCharacter> deadAvengers = new ArrayList<>();
        // apply modifiers on villain and hero based on the planet
        for (MarvelCharacter hero: avengers){
            applyPlanetModifiers(planet, hero);
        }
        applyPlanetModifiers(planet, villain);

        // villain attack's first
        // each attack will be over one hero at a time from the avengers team
        // if the hero dies the villain moves to the next hero turn by turn until villains's health or all heroes health reaches 0
        for (MarvelCharacter hero: avengers){
            fight(villain, hero);
            if (hero.getHealth() <= 0 )
                deadAvengers.add(hero);
        }
        return deadAvengers;
    }

    public List<Integer> getCharactersID(boolean isVillain) {
        return this.repository.getCharacters()
                .stream()
                .filter(res -> res.isVillain() == isVillain)
                .map(MarvelCharacter::getId)
                .collect(Collectors.toList());
    }

    // select hero
    public MarvelCharacter selectHero(int id) {
        for (MarvelCharacter character : repository.getCharacters()) {
            if (character.getId() == id && !character.isVillain())
                return character;
        }
        return null;
    }

    // select villain
    public MarvelCharacter selectVillain(int id) {
        for (MarvelCharacter character : repository.getCharacters()) {
            if (character.getId() == id && character.isVillain())
                return character;

        }
        return null;
    }

    // select planet
    public Planet selectPlanet(int id) {
        for (Planet aplanet : repository.getPlanets()) {
            if (aplanet.getId() == id)
                return aplanet;
        }
        return null;
    }


    /**
     * helper method for calculating a random attack number
     * between 60% and 100% of the attack power.
     * used for creating damage when character attack's
     */
    private int calculateAttack(int attack) {
        int randomNum = ThreadLocalRandom.current().nextInt(60, 100 + 1);
        return (attack * randomNum) / 100;
    }

    // helper method for calculating the fight between 2 Marvel Characters
    private void fight(MarvelCharacter villain, MarvelCharacter hero) {
        // attack turn by turn until someone's health reaches 0
        while (hero.getHealth() > 0 && villain.getHealth() > 0) {
            rounds++;
            // hero attack
            villain.setHealth(villain.getHealth() - calculateAttack(hero.getAttack()));
            // villain attack if he survived hero's attack
            if (villain.getHealth() > 0)
                hero.setHealth(hero.getHealth() - calculateAttack(villain.getAttack()));
        }
    }

    // helper method for applying planet modifiers over villain and hero
    private void applyPlanetModifiers(Planet planet, MarvelCharacter character) {
        if (character.isVillain()){
            character.setHealth(character.getHealth() + planet.getModifiers().getVillainHealthModifier());
            character.setAttack(character.getAttack() + planet.getModifiers().getVillainAttackModifier());
        }
        else {
            character.setHealth(character.getHealth() + planet.getModifiers().getHeroHealthModifier());
            character.setAttack(character.getAttack() + planet.getModifiers().getHeroAttackModifier());
        }
    }

}
