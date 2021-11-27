package Dungeon;

import cretures.pac.Enemy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static FileMgmt.MgmtCfg.home;
import static FileMgmt.MgmtGeneral.logs;

public class Dungeon {
    private static Random rnd = new Random();
    private static int lvlVals = 10;//для изменения количества уровней

    public Map<Integer, String[][]> getDungeonLvls() {
        return dungeonLvls;
    }
    public static Map <Integer, Enemy> ActualLvlEnemies = new HashMap<>();

    private static Map <Integer, String[][]> dungeonLvls = new HashMap<>();
    public String[][] actualRoom(int roomKey){
        return dungeonLvls.get(roomKey);
    }
    public String[][] nextRoom (int roomKey){
        return dungeonLvls.get(roomKey+1);
    }
    private static void saveActualDungeon(Map <Integer, String[][]> dungeonLvls){
        try {
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder" + File.separator + "actualDungeon.txt", false);
            String room = null;
            try{
                for(Integer count=0; count<dungeonLvls.size();count++){
                    room= dungeonLvls.get(count).toString();
                    fileWriter.write( count.toString()+"!!!"+room);
                    logs.put(logs.size()+1, "Генерация подъземелья|SAVE|Room " + count+"|Done");
                }
            }
            catch (NullPointerException e) {
                System.out.println(e.getMessage());
                logs.put(logs.size()+1,"Генерация подземелья|SAVE|NullRoom|Fail" + e.getMessage());
            }
            logs.put(logs.size()+1,"Генерация подземелья|SAVE|Rooms|Done");
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            logs.put(logs.size()+1,"Генерация подземелья|SAVE|Fail" + e.getMessage());
        }
    }
}
