package org.example.Network.Players;

import lombok.Getter;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Game.Field;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JFrame {
    private final JPanel mapPanel;
    @Getter
    private TypeOfPlayer typeOfPlayer;

    public MapFrame(Board board, TypeOfPlayer typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(typeOfPlayer.name());
        setSize(800, 800);
        setLocationRelativeTo(null);

        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(board.getRows(), board.getColumns()));
        getContentPane().add(mapPanel);

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                mapPanel.add(label);
            }
        }

        setVisible(true);
    }


    public void updateBoard(Board board) {
        if (mapPanel.getComponentCount() == 0) {
            return;
        }
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Field field = board.getActualMap().get(i).get(j);
                JLabel label = (JLabel) mapPanel.getComponent(i * board.getColumns() + j);

                if (field.getTypeOfField() == TypeOfField.BUILDING) {
                    //System.out.println("mam budynek na"+i+" "+ j+ " dla gracza "+ field.getTypeOfPlayer());
                    if (field.getTypeOfPlayer() == TypeOfPlayer.PLAYER_1){
                        label.setBackground(Color.BLUE);
                    }
                    if (field.getTypeOfPlayer() == TypeOfPlayer.PLAYER_2){
                        label.setBackground(Color.RED);
                    }
                } else {
                    label.setBackground(Color.WHITE);
                }
            }
        }
        repaint();
    }
}
