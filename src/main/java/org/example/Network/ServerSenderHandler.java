package org.example.Network;

import org.example.Game.Board;
import org.example.Game.Statistics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ServerSenderHandler extends Thread{
    private List<EchoClientHandler> clients;
    private List<Statistics> statistics;
    public ServerSenderHandler(List<EchoClientHandler> clients, List<Statistics> statistics) {
        this.clients = clients;
        this.statistics = statistics;
    }
    @Override
    public void run() {
        while (true){
            try {
                Board acutalBoard = MyServer.getBoard();
                for (Statistics s: statistics) {
                    s.update(acutalBoard);
                }
                for (var client: clients) {
                    ObjectOutputStream out = client.getOut();
                    out.reset();
                    out.writeObject(acutalBoard);
                    out.writeObject(statistics);
                    out.flush();
                }
            } catch ( IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
