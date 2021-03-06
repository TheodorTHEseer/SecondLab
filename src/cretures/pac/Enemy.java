package cretures.pac;

import items.pac.Weaponry;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import static FileMgmt.MgmtCfg.home;
import static FileMgmt.Logs.logs;

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

    private String getLine(){
        String line = this.getName()+","+ this.getHp()+","+ this.getDamage()+","+this.getDexteritySkill()+","+
                this.getLvl()+","+this.xPos+","+this.yPos;
                return line;
    }
    public void record(){
        try {
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder" + File.separator + "enemy"+id+".txt", false);
            fileWriter.write(getLine());
            logs.add("Запись файла врага: "+id+"|Done");
            fileWriter.close();
        }
        catch (Exception e){
            logs.add("Запись файла врага: "+id+"|Fail" + e.getMessage());
        }
    }
    public void addWeapon(){
        Random rnd = new Random();
        Weaponry mySword = new Weaponry("Месть архонта", 1, rnd.nextInt(50- 1)+1);
       this.Equipment.add(mySword);
    }
    public void display(){
        System.out.println(getLine());
    }
}
