package ro.avengers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import ro.avengers.repo.Repository;
import ro.avengers.repo.RepositoryJsonImpl;
import ro.avengers.service.ServiceImpl;

public class SuperHeroesApplication {
    
    Repository repository = new RepositoryJsonImpl();
    ServiceImpl serviceImpl = new ServiceImpl(repository);

    public void startServer() {

        try {

            ServerSocket ss = new ServerSocket(4050);

            while (true) {
                System.out.println("Astept conexiune de la client...");
                Socket s = ss.accept(); //metoda blocanta
                System.out.println("Clientul s-a conectat!");

                BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);

                OutputStream outputStream = s.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                
                String line = "";
                while (line != null && !line.equals("close connection")) {
                    line = fluxIn.readLine();
                    if (line != null) {
                        if (line.equals("getPlanets")) {
                            objectOutputStream.writeObject(this.serviceImpl.getPlanets());
                        }
                        if (line.equals("getHeroes")) {
                            objectOutputStream.writeObject(this.serviceImpl.getHeros());
                        }
                        if (line.equals("getVillains")) {
                            objectOutputStream.writeObject(this.serviceImpl.getVillains());
                        }
                    }
                }

                s.close();
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {

        SuperHeroesApplication app = new SuperHeroesApplication();
        app.startServer();

    }

}
