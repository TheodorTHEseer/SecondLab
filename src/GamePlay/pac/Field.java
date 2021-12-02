package GamePlay.pac;

import cretures.pac.Creature;
import cretures.pac.Enemy;

import java.util.ArrayList;

import static Dungeon.Maps.hexLvlX;
import static Dungeon.Maps.hexLvlY;

public class Field {
    int fieldXlenght;
    int fieldYlenght;
    int minLenght = 0;
    String defaultSymbol="[ ]";
    public Field(int y, int x){
        this.fieldYlenght=y;
        this.fieldXlenght=x;
    }
    public void defaultMap (String [][] map){
        for (int count=minLenght; count<fieldYlenght;count++){
            for(int count2=minLenght; count2<fieldXlenght;count2++){
                map[count][count2] = defaultSymbol;
            }
        }
    }
    public void customMap (String[][]map, Creature player){
        map[player.yPos][player.xPos]=player.shortName;
    }
    public static void customMap (String[][]map, Creature player, ArrayList <Enemy> enemies){
        map[player.yPos][player.xPos]=player.shortName;
        for(int count = 0; count<enemies.size(); count ++){
            map[enemies.get(count).yPos][enemies.get(count).xPos]=enemies.get(count).shortName;
        }
    }

    public static boolean fightStatusCheck(Creature player, ArrayList <Enemy> enemies){
        for (int count =0; count<enemies.size();count++) {
            if (player.xPos == enemies.get(count).xPos && player.yPos == enemies.get(count).yPos) {
                return true;
            }
        }
        return false;
    }
    public static int fightStatusEnemyIndex(Creature player, ArrayList <Enemy> enemies){

        for (int count=0; count<enemies.size();count++){
            if (player.xPos == enemies.get(count).xPos && player.yPos == enemies.get(count).yPos){return count;}
        }
        return 1;
    }
    public static void checkAliveStatus(ArrayList <Enemy> enemies) {
        for(int count =0; count<enemies.size(); count++){
            if (enemies.get(count).getHp()<=0){
                enemies.remove(count);
            }
        }
    }
    public static void displayMap(String[][] MyMap, int yLenght, int xLenght){
        for (int columnsN = 0; columnsN < yLenght; columnsN++) {
            for (int stringsN = 0; stringsN < xLenght; stringsN++) {
                System.out.print(MyMap[columnsN][stringsN]);
            }
            System.out.println(" ");
        }
    }
    public static void displayMap(String[][] MyMap, int lvlNumber){
        for (int columnsN = 0; columnsN < hexLvlY.get(lvlNumber); columnsN++) {
            for (int stringsN = 0; stringsN < hexLvlX.get(lvlNumber); stringsN++) {
                System.out.print(MyMap[columnsN][stringsN]);
            }
            System.out.println(" ");
        }
    }

}
