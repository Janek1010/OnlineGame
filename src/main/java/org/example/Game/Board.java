package org.example.Game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.Enums.TypeOfField;
import org.example.Enums.TypeOfPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Board implements Serializable {
    private List<List<Field>> actualMap;
    private int rows;
    private int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.actualMap =  new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Field> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new Field(i,j));
            }
            actualMap.add(row);
        }
    }
    public void setFieldType(int x, int y, TypeOfField typeOfField, TypeOfPlayer typeOfPlayer){
        actualMap.get(x).get(y).setTypeOfPlayerAndFieldType(typeOfPlayer,typeOfField);
    }
}
