package org.example.Network.Players;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Message implements Serializable {
    private int x;
    private int y;
    private TypeOfPlayer typeOfPlayer;
    private TypeOfField typeOfField;
    private int BUILDING_GOLD_COST;
    private int BUILDING_WOOD_COST;
    private int BUILDING_STONE_COST;
}
