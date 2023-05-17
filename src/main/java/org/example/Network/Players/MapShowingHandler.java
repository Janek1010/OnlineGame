package org.example.Network.Players;

import org.example.Game.Board;
import org.example.Game.Statistics;
import org.example.Network.Players.MapFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MapShowingHandler extends Thread {
    private ObjectInputStream in;
    private MapFrame frame;

    public MapShowingHandler(ObjectInputStream in, MapFrame frame) {
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
                if (inBoard instanceof ArrayList<?> receivedList) {
                    if (!receivedList.isEmpty() && receivedList.get(0) instanceof Statistics) {
                        ArrayList<Statistics> statistics = (ArrayList<Statistics>) receivedList;
                        frame.updateStatistics(statistics);
                    } else {
                        // Obsłuż przypadki, gdy lista jest pusta lub zawiera obiekty innego typu niż Statistics.
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
