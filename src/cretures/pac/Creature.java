package cretures.pac;

import items.pac.Equipment;
import items.pac.Item;
import items.pac.Weaponry;

import java.util.ArrayList;

public class Creature {
    public String name;
    protected int hp;
    protected int damage;
    protected int lvl;
    protected int dexteritySkill;

    public int xPos;
    public int yPos;

    public String shortName;
    //Переменные из Hero
    private int exp,money;
    //Конструктор для hero
    public Creature(){

    }
    public Creature(String name){
        this.name = name;
    }

    //Перегрузка.
    //Конструктор для enemy
    public Creature (String name, int hp,int dexteritySkill, int damage)
    {
        this.damage = damage;
        this.name=name;
        this.hp=100;
        this.dexteritySkill= dexteritySkill;
    }
    //Методы для получения и назначения значений в приватных переменных
    //Getters
    public String getName(){return name;}
    public int getHp(){return hp;}
    public int getDamage(){return damage;}
    public int getLvl(){return lvl;}
    public int getDexteritySkill (){return dexteritySkill;}
    public int getMoney(){return money;}
    //Setters
    public void setName(String name){this.name = name;}
    public void setHp(int hp){this.hp = hp;}
    public void setDamage(int damage){this.damage = damage;}
    public void setLvl(int lvl){this.lvl =lvl;}
    public void setDexteritySkill(int dexteritySkill){this.dexteritySkill = dexteritySkill;}
    public void setMoney(int Money){this.money = money;}

    public ArrayList<Equipment> Inventory = new ArrayList<Equipment>();//Инвентарь, хилки+щит

    public ArrayList<Weaponry> Equipment = new ArrayList<Weaponry>();//Экипировка в руках - оружие

}