package org.example.Game;

import lombok.Getter;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;

@Getter
public class Building implements Serializable {
    private TypeOfPlayer typeOfPlayer;
    private int level;
    private double generatedGold = 0.02;
    private double generatedWood = 0.01;
    private double generatedStone = 0.01;
    private double generatedPoints = 0.01;

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
