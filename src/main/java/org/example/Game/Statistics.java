package org.example.Game;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Statistics implements Serializable {
    double gold =0;
    double stone = 0;
    double wood = 0;
    double points = 0;
    TypeOfPlayer typeOfPlayer;

    public Statistics(TypeOfPlayer typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
    }

    void addGold(double addedGold){
        this.gold +=addedGold;
    }
    void addStone(double addedStone){
        this.stone +=addedStone;
    }
    void addWood(double addedWood){
        this.wood +=addedWood;
    }
    void addPoints(double addedPoints){
        this.points += addedPoints;
    }

    public void update(Board acutalBoard) {
        for (int i = 0; i < acutalBoard.getRows(); i++) {
            for (int j = 0; j < acutalBoard.getColumns(); j++) {
                Field field = acutalBoard.getActualMap().get(i).get(j);
                if (field.getTypeOfPlayer() == typeOfPlayer){
                    if (field.getTypeOfField() == TypeOfField.BUILDING){
                        Building building = field.getBuilding();
                        addGold(building.getGeneratedGold());
                        addStone(building.getGeneratedStone());
                        addWood(building.getGeneratedWood());
                        addPoints(building.getGeneratedPoints());
                    }
                }
            }
        }
    }

    public void payForBoulding(int BUILDING_GOLD_COST, int BUILDING_WOOD_COST, int BUILDING_STONE_COST) {
        setGold(getGold()-BUILDING_GOLD_COST);
        setWood(getWood()-BUILDING_WOOD_COST);
        setStone(getStone()-BUILDING_STONE_COST);
    }

}
