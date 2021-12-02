package Dungeon;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static Map<Integer, Integer> enemyLvl= new HashMap<>();
    public static Map <Integer, Integer> hexLvlY = new HashMap<>();
    public static Map <Integer, Integer> hexLvlX = new HashMap<>();
    public static void loadInfo(){
        hexLvlY.put(0,2);//6
        hexLvlY.put(1,2);//6
        hexLvlY.put(2,5);//10
        hexLvlY.put(3,3);//12
        hexLvlY.put(4,3);//12
        hexLvlY.put(5,7);//14
        hexLvlY.put(6,4);//16
        hexLvlY.put(7,6);//24
        hexLvlY.put(8,6);//30
        hexLvlY.put(9,4);//16 extra

        hexLvlX.put(0,3);//6
        hexLvlX.put(1,3);//6
        hexLvlX.put(2,2);//10
        hexLvlX.put(3,4);//12
        hexLvlX.put(4,3);//12
        hexLvlX.put(5,2);//14
        hexLvlX.put(6,4);//16
        hexLvlX.put(7,4);//24
        hexLvlX.put(8,5);//30
        hexLvlX.put(9,4);//16 extra

        enemyLvl.put(0,1);
        enemyLvl.put(1,2);
        enemyLvl.put(2,4);
        enemyLvl.put(3,6);
        enemyLvl.put(4,7);
        enemyLvl.put(5,8);
        enemyLvl.put(6,9);
        enemyLvl.put(7,12);
        enemyLvl.put(8,16);
        enemyLvl.put(9,4);//extra
    }
}
