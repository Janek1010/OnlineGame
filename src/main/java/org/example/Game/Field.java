package org.example.Game;

import lombok.*;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class Field implements Serializable {
    private int x;
    private int y;
    private boolean isTaken;
    private TypeOfPlayer typeOfPlayer;
    private TypeOfField typeOfField;
    private Building building;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.typeOfPlayer = TypeOfPlayer.NO_ONE;
        this.typeOfField = TypeOfField.NOTHING;
        this.isTaken =false;
    }

    public void setTypeOfPlayerAndFieldType(TypeOfPlayer typeOfPlayer, TypeOfField typeOfField){
        if(this.isTaken == false){
            this.typeOfPlayer = typeOfPlayer;
            this.typeOfField = typeOfField;
            if (typeOfField == TypeOfField.BUILDING){
                this.building = new Building(typeOfPlayer);
                this.isTaken = true;
            }
        }
    }

}
