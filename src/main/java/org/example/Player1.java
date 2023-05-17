package org.example;

import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Network.MyServer;
import org.example.Network.Players.MapFrame;
import org.example.Network.Players.MapShowingHandler;
import org.example.Network.Players.Player;

import java.io.IOException;

public class Player1 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Player 1
        Player client1 = new Player(new MapFrame(new Board(MyServer.ROWS_COUNT, MyServer.COLUMNS_COUNT),TypeOfPlayer.PLAYER_1), TypeOfPlayer.PLAYER_1);
        client1.startConnection("127.0.0.1", 5555);
        Thread.sleep(1000);

        Thread showingMap = new MapShowingHandler(client1.getIn(),client1.getFrame());

        showingMap.start();
        showingMap.join();

        client1.stopConnection();
    }
}
