package items.pac;

public class Weaponry extends Item{
    private int weaponDmg;
    private String rare;

    //Getters
    public int getWeaponDmg(){return weaponDmg;}
    public String getRare() {return rare;}
    //Setters
    public void setWeaponDmg(int weaponDmg){this.weaponDmg=weaponDmg;}


    //Конструктор
    public Weaponry(){}
    public Weaponry (String name, int requiredLvl,
                     int weaponDmg){
        super(name, requiredLvl);
        this.weaponDmg = weaponDmg;
        this.rare = "обычный";
        this.cost=(weaponDmg+rnd.nextInt(20-5)+5)*requiredLvl;
        if (weaponDmg >= 100 && requiredLvl >= 10){this.rare = "редкий";}
        if (weaponDmg >= 200 && requiredLvl >= 20){this.rare = "очень редкий";}
        if (weaponDmg >= 1000){this.rare = "легендарный";}
    }
    public Weaponry (String name, int weaponDmg, int requiredLvl, int cost){
        this.name=name;
        this.weaponDmg=weaponDmg;
        this.requiredLvl=requiredLvl;
        this.cost=cost+weaponDmg*10;
    }
}
