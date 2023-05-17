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
    private TypeOfPlayer typeOfPlayer;
    private TypeOfField typeOfField;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.typeOfPlayer = TypeOfPlayer.NO_ONE;
        this.typeOfField = TypeOfField.NOTHING;
    }
    public boolean hasBuilding(){
        if (typeOfField == TypeOfField.BUILDING){
            return true;
        }
        return false;
    }
    public void setTypeOfPlayerAndFieldType(TypeOfPlayer typeOfPlayer, TypeOfField typeOfField){
        this.typeOfPlayer = typeOfPlayer;
        this.typeOfField = typeOfField;
    }

    @Override
    public String toString() {
        return "(" + x +
                " " + y +
                ")";
    }
}
