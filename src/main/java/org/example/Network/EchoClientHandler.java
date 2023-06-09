package org.example.Network;

import lombok.Getter;
import lombok.Setter;
import org.example.Network.Players.MapFrame;
import org.example.Game.Statistics;
import org.example.Network.Players.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EchoClientHandler extends Thread {
    private static final Logger LOGGER = Logger.getLogger(EchoClientHandler.class.getName());
    private final Socket clientSocket;
    @Getter
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    @Getter
    @Setter
    private MapFrame frame;

    public EchoClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            Object inMessage;
            while ((inMessage = in.readObject()) != null ) {
                try {
                    if (inMessage instanceof Message mess){
                        for(Statistics s: MyServer.getStatistics()){
                            if (s.getTypeOfPlayer() == mess.getTypeOfPlayer()){
                                s.payForBoulding(mess.getBUILDING_GOLD_COST(), mess.getBUILDING_WOOD_COST(),mess.getBUILDING_STONE_COST());
                            }
                        }

                        MyServer.getBoard().setFieldType(mess.getX(),mess.getY(),mess.getTypeOfField(),mess.getTypeOfPlayer());
                    }
                } catch (Exception e){
                    LOGGER.log(Level.SEVERE, "blad w petli run!"+ e, e);
                    break;
                }
            }
            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error when serving client", e);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Zamykam polaczenie", e);
        }
    }
    public void closeConnection() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
