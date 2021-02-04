package ro.avengers.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;
import ro.avengers.models.PlanetModifier;

/**
 *
 * @author liviu.bichescu
 */
public class DataAccess {
    
    private Connection conn;
    
    public DataAccess() throws ClassNotFoundException, SQLException {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         conn = DriverManager.getConnection("jdbc:derby://localhost/test33;create=true","app","app");
    }
    
    public ArrayList<MarvelCharacter> getCharacters() throws SQLException{
        ArrayList<MarvelCharacter> rsp = new ArrayList<MarvelCharacter>();
        
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM CHARACTERS");

        while (rs.next()) {
            MarvelCharacter c = new MarvelCharacter();
            
            c.setId(rs.getInt("ID"));
            c.setName(rs.getString("NAME"));
            c.setDescription(rs.getString("DESCRIPTION"));
            c.setAttack(rs.getInt("ATTACK"));
            c.setHealth(rs.getInt("HEALTH"));
            c.setIsVillain(rs.getBoolean("ISVILLAIN"));
            
            rsp.add(c);
      }

        return rsp;
    }
    
        public ArrayList<Planet> getPlanets() throws SQLException{
        ArrayList<Planet> rsp = new ArrayList<>();
        
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM PLANETS INNER JOIN MODIFIERS\n" +
                                        "ON PLANETS.MODIFIER_ID = MODIFIERS.ID");

        while (rs.next()) {          
            
            PlanetModifier pm = new PlanetModifier();
            
            pm.setHeroAttackModifier(rs.getInt("HEROATTACKMODIFIER"));
            pm.setHeroHealthModifier(rs.getInt("HEROHEALTHMODIFIER"));
            pm.setVillainAttackModifier(rs.getInt("VILLAINATTACKMODIFIER"));
            pm.setVillainHealthModifier(rs.getInt("VILLAINHEALTHMODIFIER"));
            
            Planet p = new Planet();
           
            p.setId(rs.getInt("ID"));
            p.setName(rs.getString("NAME"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setModifiers(pm);
            
            rsp.add(p);
      }

        return rsp;
    }
}
