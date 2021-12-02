package Dungeon;

import GamePlay.pac.GameLogic;
import cretures.pac.Enemy;
import items.pac.Weaponry;

import java.util.ArrayList;
import java.util.Random;

import static Dungeon.Maps.*;
import static GamePlay.pac.GameLogic.Names;
import static GamePlay.pac.GameLogic.Tags;

public class Dungeon {
    private static Random rnd = new Random();
    private static int lvlVals = 10;//для изменения количества уровней

    public static String[][] generateLvl(int lvlNumber){
        String [][] map = new String[hexLvlY.get(lvlNumber)][hexLvlX.get(lvlNumber)];
        defaultMap(lvlNumber, map);
        return map;
    }
    private static void defaultMap(int lvlNumber, String [][] map){
        for (int count=0; count<hexLvlY.get(lvlNumber);count++){
            for(int count2=0; count2<hexLvlX.get(lvlNumber);count2++){
                map[count][count2] = "[ ]";
            }
        }
    }

    public static ArrayList <Enemy> generateEnemiesArray (int lvlNumber){
        loadInfo();
        ArrayList <Enemy> enemies = new ArrayList<>();
        ArrayList <Weaponry> enemyWeapons = new ArrayList<>();
        int enemiesMass = enemyLvl.get(lvlNumber);
        int xLenght = hexLvlX.get(lvlNumber);
        int yLenght = hexLvlY.get(lvlNumber);
        Random rnd = new Random();
        for (int count = 0; count < enemiesMass; count++) {
            int firstPt = rnd.nextInt(4);
            int secondPt = rnd.nextInt(4);
            String tag = Tags[0][firstPt] + " " + Tags[1][secondPt];
            //Добавление нового в список
            enemyWeapons.add(new Weaponry(tag, rnd.nextInt(3-1)+1, rnd.nextInt(55 - 1) + 1));
        }
        for (int count = 0; count < enemiesMass; count++) {
            //Для имён
            int firstPt = rnd.nextInt(10);
            int secondPt = rnd.nextInt(10);
            int nicknameId = rnd.nextInt(10);
            //Создание имени
            String name = Names[0][firstPt] + Names[1][secondPt] + Names[2][nicknameId];
            //Добавление нового в список
            enemies.add(new Enemy(name, 100, rnd.nextInt(100 - 1)+1, 1, 1));
            enemies.get(count).Equipment.add(enemyWeapons.get(count));
            enemies.get(count).setDamage(GameLogic.calcDPS(enemies.get(count).Equipment, enemies.get(count).getDamage()));
        }
        for (int count = 0; count<enemiesMass; count++){
            enemies.get(count).xPos= rnd.nextInt(xLenght-1)+1;
            enemies.get(count).yPos= rnd.nextInt(yLenght-1)+1;
        }
        return enemies;
    }

}
