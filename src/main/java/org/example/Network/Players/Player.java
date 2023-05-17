package org.example.Network.Players;

import lombok.Getter;
import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Network.MyServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class Player {
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());


    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private MapFrame frame = null;
    private final TypeOfPlayer typeOfPlayer;

    public Player(MapFrame frame, TypeOfPlayer typeOfPlayer) {
        this.frame = frame;
        this.typeOfPlayer = typeOfPlayer;
    }


    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            frame.setOut(out);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error when starting connection", e);
        }
    }


    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error when closing connection", e);
        }
    }
}