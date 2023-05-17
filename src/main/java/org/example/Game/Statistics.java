package org.example.Game;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Statistics implements Serializable {
    float gold =0;
    float stone = 0;
    float wood = 0;
    long points = 0;
    TypeOfPlayer typeOfPlayer;

    public Statistics(TypeOfPlayer typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
    }

    void addGold(float addedGold){
        this.gold +=addedGold;
    }
    void addStone(float addedStone){
        this.stone +=addedStone;
    }
    void addWood(float addedWood){
        this.wood +=addedWood;
    }
    void addPoints(long addedPoints){
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
}
