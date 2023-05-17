package org.example.Network.Players;

import org.example.Game.Board;
import org.example.Network.Players.MapFrame;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MapShowingHandler extends Thread {
    private ObjectInputStream in;
    private MapFrame frame;

    public MapShowingHandler(ObjectInputStream in, MapFrame frame) throws IOException {
        this.in = in;
        this.frame = frame;
    }

    @Override
    public void run() {
        Object inBoard;
        try {
            while ((inBoard = in.readObject()) != null) {

                if (inBoard instanceof Board board) {
                    frame.updateBoard(board);
                }
            }
        } catch (Exception e){
            System.out.println("MapShowingHandler error");
        }
    }
}
