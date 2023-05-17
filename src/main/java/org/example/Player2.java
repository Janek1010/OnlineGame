package org.example;

import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Network.MyServer;
import org.example.Network.Players.MapFrame;
import org.example.Network.Players.MapShowingHandler;
import org.example.Network.Players.Player;

import java.io.IOException;

public class Player2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player client2 = new Player(new MapFrame(new Board(MyServer.ROWS_COUNT, MyServer.COLUMNS_COUNT),TypeOfPlayer.PLAYER_2), TypeOfPlayer.PLAYER_2);
        client2.startConnection("127.0.0.1", 5555);
        Thread.sleep(1000);

        Thread showingMap2 = new MapShowingHandler(client2.getIn(),client2.getFrame());
        showingMap2.start();

        showingMap2.join();

        client2.stopConnection();
    }
}
