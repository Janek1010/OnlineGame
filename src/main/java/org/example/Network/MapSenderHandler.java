package org.example.Network;

import org.example.Game.Board;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class MapSenderHandler extends Thread{
    private List<EchoClientHandler> clients;
    public MapSenderHandler(List<EchoClientHandler> clients) throws IOException {
        this.clients = clients;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
                Board acutalBoard = MyServer.getBoard();
                for (var client: clients) {
                    ObjectOutputStream out = client.getOut();
                    out.reset();
                    out.writeObject(acutalBoard);
                    out.flush();
                }
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
