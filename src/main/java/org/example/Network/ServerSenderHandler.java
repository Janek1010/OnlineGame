package org.example.Network;

import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Game.Statistics;
import org.example.Network.Players.MapFrame;
import org.example.Network.Players.Player;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ServerSenderHandler extends Thread{
    private List<EchoClientHandler> clients;
    private List<Statistics> statistics;
    private boolean gameEnded = false;
    private boolean dialogDisplayed;

    public ServerSenderHandler(List<EchoClientHandler> clients, List<Statistics> statistics) {
        this.clients = clients;
        this.statistics = statistics;
        this.gameEnded = false;
        this.dialogDisplayed = false;
    }
    @Override
    public void run() {
        while (true){
            try {
                Board acutalBoard = MyServer.getBoard();
                for (Statistics s: statistics) {
                    s.update(acutalBoard);
                    if (s.getPoints() >= 2) {
                        gameEnded = true;
                        if (!dialogDisplayed) {
                            TypeOfPlayer winner = s.getTypeOfPlayer();
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(null,  winner.name() + " has won!");
                                closePlayerWindows();
                            });
                            dialogDisplayed = true;
                        }
                        break;
                    }

                }
                if(!gameEnded){
                    for (var client: clients) {
                        ObjectOutputStream out = client.getOut();
                        out.reset();
                        out.writeObject(acutalBoard);
                        out.writeObject(statistics);
                        out.flush();
                    }
                }
            } catch ( IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void closePlayerWindows() { // nie działa gowno
        for (EchoClientHandler client : clients) {
            MapFrame frame = client.getFrame();
            if (frame != null) {
                SwingUtilities.invokeLater(() -> {
                    frame.dispose();
                    client.setFrame(null);
                });
            }
            client.closeConnection();
        }
        MyServer.stop();
    }
}
