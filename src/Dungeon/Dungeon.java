package Dungeon;

import java.util.Random;

import static Dungeon.Maps.hexLvlX;
import static Dungeon.Maps.hexLvlY;

public class Dungeon {
    private static Random rnd = new Random();
    private static int lvlVals = 10;//для изменения количества уровней

    public static String[][] generateLvl(int lvlNumber){
        String [][] map = new String[hexLvlY.get(lvlNumber)][hexLvlX.get(lvlNumber)];
        return map;
    }
}
