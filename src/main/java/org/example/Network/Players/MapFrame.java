package org.example.Network.Players;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;
import org.example.Game.Board;
import org.example.Game.Field;
import org.example.Game.Statistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MapFrame extends JFrame {
    private static final int BUILDING_GOLD_COST = 50;
    private static final int BUILDING_WOOD_COST = 50;
    private static final int BUILDING_STONE_COST = 50;
    private final JPanel mapPanel;
    @Setter
    private ObjectOutputStream out = null;
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
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();

                // Przeliczenie współrzędnych pikselowych na indeksy w siatce GridLayout
                int width = mapPanel.getWidth();
                int height = mapPanel.getHeight();
                int rows = board.getRows();
                int columns = board.getColumns();
                int cellWidth = width / columns;
                int cellHeight = height / rows;

                int rowIndex = y / cellHeight;
                int columnIndex = x / cellWidth;

                try {
                    out.writeObject(new Message(rowIndex, columnIndex, typeOfPlayer, TypeOfField.BUILDING));
                    out.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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
        for (Statistics s : statistics) {
            if (s.getTypeOfPlayer() == typeOfPlayer) {

                woodLabel.setText(new BigDecimal(s.getWood()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                goldLabel.setText(new BigDecimal(s.getGold()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                stoneLabel.setText(new BigDecimal(s.getStone()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                pointsLabel.setText(new BigDecimal(s.getPoints()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
        }

    }
}
