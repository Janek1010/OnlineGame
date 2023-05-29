package org.example.Network;

import lombok.Getter;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Game.Statistics;
import org.example.Network.Players.MapFrame;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class MyServer {
    private static final Logger LOGGER = Logger.getLogger(MyServer.class.getName());
    private static ServerSocket serverSocket;
    private int connectedPlayers = 0;
    private final static int MAX_PLAYERS = 2;
    public final static int ROWS_COUNT = 30;
    public final static int COLUMNS_COUNT = 30;
    private static Board board = null;
    private static List<EchoClientHandler> echoClientHandlers = new ArrayList<>();
    @Getter
    private static List<Statistics> statistics = new ArrayList<>();
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.start(5555);
    }
    static Board getBoard(){
        return board;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (connectedPlayers != MAX_PLAYERS) {
                Socket acceptedSocket = serverSocket.accept();
                EchoClientHandler acceptedPlayer = new EchoClientHandler(acceptedSocket);
                echoClientHandlers.add(acceptedPlayer);
                acceptedPlayer.start();
                connectedPlayers++;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error when starting Server", e);
            e.printStackTrace();
        } finally {
            stop();
        }
        LOGGER.log(Level.INFO, "Maksymalna ilosc graczy, startujemy rozgrykwe ");
        board = new Board(ROWS_COUNT,COLUMNS_COUNT);
        board.setFieldType(3,3, TypeOfField.BUILDING, TypeOfPlayer.PLAYER_1); //robie graczowi startowa pozycje
        board.setFieldType(ROWS_COUNT -3,COLUMNS_COUNT-3, TypeOfField.BUILDING, TypeOfPlayer.PLAYER_2);

        statistics.add(new Statistics(TypeOfPlayer.PLAYER_1));
        statistics.add(new Statistics(TypeOfPlayer.PLAYER_2));

        new Thread(new ServerSenderHandler(echoClientHandlers, statistics)).start();

    }

    public static void stop() {
        try {
            serverSocket.close();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error when stoping server", e);
            e.printStackTrace();
        }
    }


}