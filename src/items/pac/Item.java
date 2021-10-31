package items.pac;

import java.util.Random;

public class Item {
    protected String name;
    protected int cost;
    protected int requiredLvl;


    Random rnd = new Random();
    //Конструктор
    public Item (){}
    public Item (String name, int requiredLvl){
        this.name = name;
        this.requiredLvl = requiredLvl;
        this.cost = requiredLvl*10* rnd.nextInt(100-1)+1;
    }
    //Getters
    public String getName(){return name;}
    public int getCost(){return cost;}
    public int getRequiredLvl(){return requiredLvl;}

    //Setters
    public void setName(String name){this.name = name;}
    public void setCost(int cost){this.cost = cost;}
    public void setRequiredLvl(int requiredLvl){this.requiredLvl = requiredLvl;}

}
