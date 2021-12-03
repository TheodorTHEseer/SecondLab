package Settlement.Buildings;

public class Building {
    String name;
    int cost;
    int yield;
    public String getName (){
        return name;
    }
    public void display(){
        System.out.println(name);
    }
    public int getCost(){
        return this.cost;
    }
}
