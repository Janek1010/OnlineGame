package org.example.Game;

import lombok.Getter;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;

@Getter
public class Building implements Serializable {
    private TypeOfPlayer typeOfPlayer;
    private int level;
    private float generatedGold = 2;
    private float generatedWood = 1;
    private float generatedStone = 1;
    private long generatedPoints = 3;

    public Building(TypeOfPlayer typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
        this.level = 1;
    }
    public void upgradeBuilding(){
        generatedGold *= 1.5;
        generatedWood *= 1.2;
        generatedStone *= 1.4;
        generatedPoints *= 2;
        level++;
    }

}
