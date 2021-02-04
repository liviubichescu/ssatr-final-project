package ro.avengers.repo;

import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryJsonImpl implements Repository{

    private DataAccess db;
    private List<MarvelCharacter> characters;
    private List<Planet> planets;


    public RepositoryJsonImpl(){
        loadData();
    }

    // load all data from file
    @Override
    public void loadData() {

        try {
            this.db = new DataAccess();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepositoryJsonImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryJsonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.characters = this.db.getCharacters();
            this.planets = this.db.getPlanets();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryJsonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Planet> getPlanets() {
        return planets;
    }

    @Override
    public List<MarvelCharacter> getCharacters() {
        return characters;
    }

}
