package cretures.pac;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static FileMgmt.MgmtCfg.home;
import static FileMgmt.MgmtGeneral.logs;

public class Enemy extends Creature{
    char holdspace;
    private int id;
    public Enemy (String name, int hp,  int damage, int dexteritySkill,
                  int lvl) {
        super(name, hp, dexteritySkill, damage);
        this.lvl = lvl;
        holdspace = name.charAt(0);
        this.shortName= "["+holdspace+"]";
    }
    public Enemy (String name, int hp,  int damage, int dexteritySkill,
                  int lvl, int xPos, int yPos) {
        super(name, hp, dexteritySkill, damage);
        this.lvl = lvl;
        holdspace = name.charAt(0);
        this.shortName= "["+holdspace+"]";
        this.xPos=xPos;
        this.yPos=yPos;
    }
    public void getId(ArrayList<Enemy> enemies){
        id=enemies.indexOf(this);
    }

    public String getLine(){
        String line = this.getName()+","+ this.getHp()+","+ this.getDamage()+","+this.getDexteritySkill()+","+
                this.getLvl()+","+this.xPos+","+this.yPos;
                return line;
    }
    public void record(){
        try {
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder" + File.separator + id+".txt");
            logs.put(logs.size()+1,"Запись файла врага: "+id+"|Done");
        }
        catch (Exception e){
            logs.put(logs.size()+1,"Запись файла врага: "+id+"|Fail" + e.getMessage());
        }
    }
}
