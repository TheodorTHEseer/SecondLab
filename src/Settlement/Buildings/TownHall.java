package Settlement.Buildings;

public class TownHall extends Building{
    public int getLaborersValue() {
        return laborersValue;
    }

    private int laborersValue;
    public TownHall (){
        name = "[\u001B[36mHall\u001B[0m]";
        cost = 2000;
        yield = 0;
        spends = 50;
        laborersValue=3;
        laborerSpends =1;
    }

}
