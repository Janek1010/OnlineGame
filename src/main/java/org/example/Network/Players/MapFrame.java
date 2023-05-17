package org.example.Network.Players;

import lombok.Getter;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Game.Field;
import org.example.Game.Statistics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapFrame extends JFrame {
    private final JPanel mapPanel;
    @Getter
    private TypeOfPlayer typeOfPlayer;
    private Statistics statistics;
    private JLabel woodLabel;
    private JLabel goldLabel;
    private JLabel stoneLabel;
    private JLabel pointsLabel;

    public MapFrame(Board board, TypeOfPlayer typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
        this.statistics = new Statistics(typeOfPlayer);
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
        // Tworzenie panelu statystyk
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        getContentPane().add(statsPanel, BorderLayout.EAST);

        // Tworzenie etykiet statystyk
        JLabel woodTextLabel = new JLabel("Drewno:");
        woodLabel = new JLabel();
        JLabel goldTextLabel = new JLabel("Złoto:");
        goldLabel = new JLabel();
        JLabel stoneTextLabel = new JLabel("Kamień:");
        stoneLabel = new JLabel();
        JLabel pointsTextLabel = new JLabel("Punkty:");
        pointsLabel = new JLabel();

        // Dodawanie etykiet statystyk do panelu
        statsPanel.add(woodTextLabel);
        statsPanel.add(woodLabel);
        statsPanel.add(goldTextLabel);
        statsPanel.add(goldLabel);
        statsPanel.add(stoneTextLabel);
        statsPanel.add(stoneLabel);
        statsPanel.add(pointsTextLabel);
        statsPanel.add(pointsLabel);
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
                    if (field.getTypeOfPlayer() == TypeOfPlayer.PLAYER_1) {
                        label.setBackground(Color.BLUE);
                    }
                    if (field.getTypeOfPlayer() == TypeOfPlayer.PLAYER_2) {
                        label.setBackground(Color.RED);
                    }
                } else {
                    label.setBackground(Color.WHITE);
                }
            }
        }
        repaint();
    }

    public void updateStatistics(ArrayList<Statistics> statistics) {
        for (Statistics s: statistics) {
            if (s.getTypeOfPlayer() == typeOfPlayer){
                woodLabel.setText(String.valueOf(s.getWood()));
                goldLabel.setText(String.valueOf(s.getGold()));
                stoneLabel.setText(String.valueOf(s.getStone()));
                pointsLabel.setText(String.valueOf(s.getPoints()));
            }
        }

    }
}
