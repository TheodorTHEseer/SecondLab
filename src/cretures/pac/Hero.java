package cretures.pac;

import items.pac.Weaponry;

import java.util.ArrayList;
import java.util.Random;

public class Hero extends Creature {

    Random rnd = new Random();
    private int exp,money;
    private int defaultDamage;
    //К 4 практике
    private int maxHp;
    private int armour;
    private int magicSkill;
    private int stamina;
    private int magicId;
    private String magicName;
    private int staminaRefresh;//вост. энергии. TES буст талантов и прочего
    public static String prefix = "l33t";
    //Getters
    public int getMoney(){return money;}
    public int getExp(){return exp;}
    public int getDefaultDamage(){return defaultDamage;}
    public int getMaxHp(){return  maxHp;}
    public int getArmour(){return armour;}
    public int getMagicSkill(){return magicSkill;}
    public int getMagicId(){return magicId;}
    public int getStamina(){return stamina;}
    public int getStaminaRefresh(){return staminaRefresh;}
    public String getMagicName(){return magicName;}
    //Setters
    public void setMagicId(int magicId){this.magicId = magicId;}
    public void setMoney(int money){this.money = money;}
    public void setExp(int exp){this.exp = exp;}
    public void setArmour(int armour){this.armour=armour;}
    public void setMagicSkill(int magicSkill){this.magicSkill=magicSkill;}
    public void setStamina(int stamina){this.stamina=stamina;}
    public static String [] HNames = {"Гавейн", "Ланселот", "Ламорак" , "Бомейн", "Мордред", "Уриенс", "Борс", "Оуэн",
            "Гахерис", /*тут начинается тес*/"Лоркалин", "Агила", "Агвар", "Лларен", "Уриэль","Небия", "Маннимарко", "Николин"};
    private String [] MagicNames = {"Огонь", "Вода", "Воздух"};
    //Конструкторы
     public Hero(String name){
         super(name);
         this.maxHp = rnd.nextInt(120-60)+60;
        this.hp = maxHp/2;
        this.armour = rnd.nextInt(50-10)+10;
        this.magicId = rnd.nextInt(MagicNames.length);
        this.magicSkill= rnd.nextInt(5);
        this.defaultDamage=(magicSkill*magicId)+rnd.nextInt(45-10)+10;
        this.stamina=100;
        this.staminaRefresh = rnd.nextInt(10);
        this.magicName=MagicNames[magicId];
         holdspace = name.charAt(0);
        this.shortName= "["+holdspace + "]";
    }

    char holdspace;
    public Hero (String name, int hp, int damage, int exp, int dexteritySkill, int money) {
        super(name, hp, dexteritySkill, damage);
        holdspace = name.charAt(0);
        this.shortName= "["+holdspace + "]";
        this.money = money;
        this.exp = exp;
        this.magicId = 2;
        this.damage=damage;
        this.defaultDamage=damage;
        this.lvl = exp /1000;
    }
    public void display(){
        System.out.println(getName() + " dmg: " + this.getDamage());
    }
    public void getSave(ArrayList<String> flines){
        if (flines.size()==7){
        this.setName(flines.get(0));
        flines.remove(0);
        this.hp = xRelay(flines);
        this.damage=xRelay(flines);
        this.exp=xRelay(flines);
        this.dexteritySkill=xRelay(flines);
        this.money=xRelay(flines);
        }
        else
            System.out.println("Файл сохранения повреждён!");
    }
    private int xRelay(ArrayList <String> flines){
        int x = Integer.parseInt(flines.get(0));
        flines.remove(0);
        return x;
    }
    public void giveStartedSword(){
        Random rnd = new Random();
        Weaponry mySword = new Weaponry("Мой меч", 1, rnd.nextInt(50- 1)+1);
        this.Equipment.add(mySword);
    }
}
