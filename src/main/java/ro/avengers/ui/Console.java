package ro.avengers.ui;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;
import ro.avengers.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Console {

    private ServiceImpl serviceImpl;

    public Console(ServiceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    /*---------------------------------- Menu -------------------------------------------------*/

    private void menu() {
        System.out.println();
        System.out.println("*********** MENU ***************");
        System.out.println();
        System.out.println("1. Display planet's");
        System.out.println("2. Display Heroes");
        System.out.println("3. Display Villain's");
        System.out.println("--------------------------------");
        System.out.println("4. Start FIGHT ");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Enter option: ");
    }

    private void battlefieldMenu() {
        System.out.println();
        System.out.println("------------------------- WELCOME TO THE FIGHT PLAY GROUND! ------------------------ \n\n" +
                "-------------------------------- CHOSE YOUR FIGHT ----------------------------------");
        System.out.println();
        System.out.println("1. HERO vs VILLAIN");
        System.out.println("2. AVENGERS vs VILLAIN");
        System.out.println("-------------------------------------");
        System.out.println("0. Exit");
        System.out.println();
        System.out.println("Enter option: ");

    }

    /**
     * Reads user input from battlefield menu
     */
    private void battlefield() {
        battlefieldMenu();
        int choice = validateUserChoice();
        System.out.println();
        switch (choice) {
            case 1:
                heroVSvillain();
                break;
            case 2:
                avengersVSvillain();
                break;
            case 0:
                return;
            default:
                System.out.println("Option not available! Please enter a number from the menu!");
        }
    }

    // start the game
    public void startGame() {
        boolean flag = true;
        while (flag) {
            menu();
            int choice = validateUserChoice();
            switch (choice) {
                case 1:
                    planets();
                    break;
                case 2:
                    heros();
                    break;
                case 3:
                    villains();
                    break;
                case 4:
                    battlefield();
                    flag = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option not available! Please enter a number from the menu!");
            }
        }

    }


    /*------------------------------------ Display data -----------------------------------------------*/
    // display all planet's
    private void planets() {
        System.out.println("Chose a PLANET from the list: ");
        this.serviceImpl.getPlanets().forEach(System.out::println);
        System.out.println();
    }

    // display all heroes
    private void heros() {
        System.out.println("Chose a HERO from the list: ");
        this.serviceImpl.getHeros().forEach(System.out::println);
        System.out.println();
    }

    // display all villain's
    private void villains() {
        System.out.println();
        System.out.println("Chose a VILLAIN from the list: ");
        this.serviceImpl.getVillains().forEach(System.out::println);
        System.out.println();
    }


    /*------------------------------------- Read user choices----------------------------------------------*/

    /**
     * Chose a planet from the Planet list
     *
     * @return a planet
     */
    private Planet chosePlanet() {
        // display planet's for user to chose
        planets();
        System.out.print("Enter PLANET Id: ");
        int choice = validateUserChoice();
        System.out.println();

        Planet planet = this.serviceImpl.selectPlanet(choice);

        // check to see if user input is correct
        if (!this.serviceImpl.getPlanets()
                .stream()
                .map(Planet::getId)
                .collect(Collectors.toList()).contains(choice)) {
            System.out.println("Character that you have chosen is not a Planet, please enter again a planet ID!");
            System.out.println();
            planet = chosePlanet();
        }
        return planet;
    }

    /**
     * Chose a hero from the Marvel Character list
     *
     * @return a Hero character
     */
    private MarvelCharacter choseHero() {
        // display heroes for user to chose
        heros();
        // read user input
        System.out.print("Enter HERO Id: ");
        int choice = validateUserChoice();
        System.out.println();

        MarvelCharacter hero = serviceImpl.selectHero(choice);
        // retrive all heroes ID
        List<Integer> listId = serviceImpl.getCharactersID(false);

        // check to see if user input is correct
        if (!listId.contains(choice)) {
            System.out.println("Character that you have chosen is not a Hero, please enter again a hero ID!");
            System.out.println();
            hero = choseHero();
        }

        return hero;
    }

    /**
     * Chose a villain from the Marvel Character list
     *
     * @return a Villain character
     */
    private MarvelCharacter choseVillain() {
        // display villain's for user to chose
        villains();
        // read user input
        System.out.print("Enter VILLAIN Id: ");
        int choice = validateUserChoice();
        System.out.println();

        MarvelCharacter villain = serviceImpl.selectVillain(choice);

        // retrive all villain's ID
        List<Integer> listId = serviceImpl.getCharactersID(true);

        // check to see if user input is correct
        if (!listId.contains(choice)) {
            System.out.println("Character that you have chosen is not a Villain, please enter again a villain ID!");
            System.out.println();
            villain = choseVillain();
        }

        return villain;
    }

    /**
     * Chose a list of Heroes to create the Avengers Team
     *
     * @return a list of Heroes
     */
    private List<MarvelCharacter> choseAvengers() {
        // display heroes for user to chose
        heros();
        // read user input for the size of avengers team
        System.out.println("Enter size of Avengers team: ");
        int nrOfAvengers = this.serviceImpl.getHeros().size();
        int teamsize = validateUserChoice();
        if (teamsize > nrOfAvengers) {
            while (teamsize > nrOfAvengers) {
                System.out.println("You have chosen to many heroes! Please try again with a smaller number!!!");
                teamsize = validateUserChoice();
            }
        }
        List<MarvelCharacter> avengersTeam = new ArrayList<>();
        for (int i = 0; i < teamsize; i++) {
            System.out.print("Enter ID for the " + (i + 1) + " Hero: ");
            int choice = validateUserChoice();
            MarvelCharacter hero = serviceImpl.selectHero(choice);
            // retrive all heroes ID
            List<Integer> listId = serviceImpl.getCharactersID(false);

            // check to see if user input is correct
            if (!listId.contains(choice)) {
                System.out.println("Character that you have chosen is not a Hero, please enter again a hero ID!");
                System.out.println();
            }
            avengersTeam.add(hero);
        }
        return avengersTeam;
    }


    /*------------------------------------- Fights processing, winner and loser message display --------------------*/

    /**
     * Processing the fight
     * Display's the fight result messages to the user
     */
    private void heroVSvillain() {
        System.out.println("***** Chose a PLANET, a HERO and a VILLAIN! *****");
        System.out.println();
        Planet planet = chosePlanet();
        MarvelCharacter hero = choseHero();
        MarvelCharacter villain = choseVillain();
        MarvelCharacter winner = serviceImpl.heroVsVillain(planet, hero, villain);
        MarvelCharacter looser = null;

        if (winner.equals(hero))
            looser = villain;
        else
            looser = hero;

        System.out.println();
        System.out.println("**************** GAME OVER ****************");
        System.out.println();
        System.out.println("The winner is: " + winner.getName().toUpperCase() + "!!!");
        System.out.println();
        System.out.println("After " + ServiceImpl.rounds + " difficult rounds " +
                winner.getName() + " won the battle against " + looser.getName() + " with " + winner.getHealth() + " Health Points remaining!");

    }

    /**
     * Reads user choice for planet, villain and Avengers team and send's them for processing the fight
     * Display's the fight result messages to the user
     */
    private void avengersVSvillain() {
        System.out.println("***** Chose a PLANET, size of Avengers team, select your Heroes and a VILLAIN! *****");
        System.out.println();
        Planet planet = chosePlanet();
        List<MarvelCharacter> avengers = choseAvengers();
        MarvelCharacter villain = choseVillain();

        List<MarvelCharacter> deadAvengers = serviceImpl.avengersVsVillain(planet, avengers, villain);

        System.out.println();
        System.out.println("**************** GAME OVER ****************");
        System.out.println();

        // if villain won display messages
        if (deadAvengers.size() == avengers.size()) {
            System.out.println("After " + ServiceImpl.rounds + " difficult attack's over the avengers, " + villain.getName().toUpperCase() +
                    " won the battle against the Avengers Team with " + villain.getHealth() + " Health Points remaining!");
            for (MarvelCharacter carac : deadAvengers) {
                System.out.println(carac.getName().toUpperCase() + " was defeated!!!");
            }
        }
        // if avengers won display messages
        else {
            System.out.println("--------The Avengers Team WON!!!---------");
            System.out.println();
            if (deadAvengers.size() > 0) {
                System.out.println("During battle " + villain.getName().toUpperCase() + " succeeded to defeat hero: ");
                for (MarvelCharacter carac : deadAvengers) {
                    System.out.println(carac.getName().toUpperCase() + "!!!");
                }
            } else {
                System.out.println(villain.getName().toUpperCase() + " could not defeat any of the avengers!");
            }
            System.out.println();
            for (MarvelCharacter carac : avengers) {
                if (carac.getHealth() < 0)
                    carac.setHealth(0);
                System.out.println(carac.getName().toUpperCase() + " finished the fight with " + carac.getHealth() + " Health Points remaining!!!");
            }
        }
    }



    /*-----------------------------------------------------------------------------------*/

    /**
     * Validate's the user input
     *
     * @return an int, (user choice)
     */
    private int validateUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid choice! Your input should be a number! Please try again! ");
            scanner.next(); // this is important!
        }
        choice = scanner.nextInt();
        return choice;
    }
}
