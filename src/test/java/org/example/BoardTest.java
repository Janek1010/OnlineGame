package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.example.Game.Board;
import org.example.Game.Field;
import org.junit.jupiter.api.Test;

class BoardTest {
    /**
     * Method under test: {@link Board#Board(int, int)}
     */
    @Test
    void testConstructor() {
        List<List<Field>> actualMap = (new Board(1, 1)).getActualMap();
        assertEquals(1, actualMap.size());

        List<Field> getResult = actualMap.get(0);
        assertEquals(1, getResult.size());

        Field getResult2 = getResult.get(0);
        assertEquals(0, getResult2.getX());
        assertEquals(0, getResult2.getY());
    }
    @Test
    void printTest(){
        List<List<Field>> actualMap = (new Board(20, 20)).getActualMap();
        for (var e : actualMap) {
            System.out.println(e);
        }
    }
}

