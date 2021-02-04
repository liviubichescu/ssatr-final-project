package ro.avengers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import ro.avengers.models.CommunicationModel;
import ro.avengers.models.MarvelCharacter;
import ro.avengers.models.Planet;
import ro.avengers.repo.Repository;
import ro.avengers.repo.RepositoryJsonImpl;
import ro.avengers.service.ServiceImpl;

/**
 *
 * @author liviu.bichescu
 */
public class AvengersNetConnector {

    Repository repository = new RepositoryJsonImpl();
    ServiceImpl serviceImpl = new ServiceImpl(repository);

    public AvengersNetConnector() {
        this.startServer();
    }

    public void startServer() {

        try {

            ServerSocket ss = new ServerSocket(4050);

            while (true) {
                System.out.println("Astept conexiune de la client...");
                Socket s = ss.accept(); //metoda blocanta
                System.out.println("Clientul s-a conectat!");

                BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

                // get the output stream from the socket.
                OutputStream outputStream = s.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                
                String line = "";
                while (line != null && !line.equals("close connection")) {
                    line = fluxIn.readLine();
                    if (line != null) {
                        if (line.equals("getPlanets")) {
                            objectOutputStream.writeObject(this.planets());
                        }
                        if (line.equals("getHeroes")) {
                            objectOutputStream.writeObject(this.heroes());
                        }
                        if (line.equals("getVillains")) {
                            objectOutputStream.writeObject(this.villains());
                        }
                    }
                }

                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------ Display data -----------------------------------------------*/
    // display all planet's
    private List<Planet> planets() {
        return this.serviceImpl.getPlanets();
    }

    // display all heroes
    private List<MarvelCharacter> heroes() {
        return this.serviceImpl.getHeros();
    }

    // display all villain's
    private List<MarvelCharacter> villains() {
        return this.serviceImpl.getVillains();
    }
}
