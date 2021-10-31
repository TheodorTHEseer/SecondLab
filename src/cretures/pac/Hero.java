package cretures.pac;

public class Hero extends Creature {
    private int exp,money,magicId;
    private int defaultDamage;
    //Getters
    public int getMagicId(){return magicId;}
    public int getMoney(){return money;}
    public int getExp(){return exp;}
    public int getDefaultDamage(){return defaultDamage;}
    //Setters
    public void setMagicId(int magicId){this.magicId = magicId;}
    public void setMoney(int money){this.money = money;}
    public void setExp(int exp){this.exp = exp;}
    //Конструкторы
    public Hero(String name){
        super(name);
    }

    char holdspace;
    public Hero (String name, int hp, int damage, int exp, int dexteritySkill, int money) {
        super(name, hp, dexteritySkill, damage);
        this.money = money;
        this.exp = exp;
        this.magicId = 2;
        this.damage=damage;
        this.defaultDamage=damage;
        this.lvl = exp /1000;
        holdspace = name.charAt(0);
        this.shortName= "["+holdspace + "]";
    }
}
