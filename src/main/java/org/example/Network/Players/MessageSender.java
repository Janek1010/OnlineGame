package org.example.Network.Players;

import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;
import org.example.Network.MyServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageSender extends Thread {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(MessageSender.class.getName());
    private ObjectOutputStream out;
    private MapFrame frame;
    private TypeOfPlayer typeOfPlayer;

    public MessageSender(ObjectOutputStream out, MapFrame frame, TypeOfPlayer typeOfPlayer) throws IOException {
        this.out = out;
        this.frame = frame;
        this.typeOfPlayer = typeOfPlayer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                out.writeObject(new Message(x, y, typeOfPlayer, TypeOfField.BUILDING));
                out.flush();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error when sending Message", e);
            }
        }
    }
}
