package ro.avengers;

import ro.avengers.repo.Repository;
import ro.avengers.repo.RepositoryJsonImpl;
import ro.avengers.service.ServiceImpl;
import ro.avengers.ui.Console;

public class SuperHeroesApplication {

    public static void main(String[] args) {

//        Repository repository = new RepositoryJsonImpl();
//        ServiceImpl serviceImpl = new ServiceImpl(repository);
//        Console console = new Console(serviceImpl);
//        console.startGame();
        AvengersNetConnector connector = new AvengersNetConnector();

    }

}
