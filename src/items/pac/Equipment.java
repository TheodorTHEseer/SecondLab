package items.pac;

public class Equipment extends Item{
    private int hpBoost;

    //Getter
    public int getHpBoost(){return hpBoost;}
    //Setter
    public void setHpBoost(int hpBoost){this.hpBoost = hpBoost;}
    public Equipment(String name, int requiredLvl){
        super(name, requiredLvl);
        setCost(requiredLvl*3* rnd.nextInt(100-1)+1);
        this.hpBoost= requiredLvl+getCost()/25;
    }
}
