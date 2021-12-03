package Settlement.Buildings;

public class Building {
    public int getLaborerSpends() {
        return laborerSpends;
    }

    int laborerSpends;
    String name;
    int cost;
    int yield;
    int spends;
    public String getName (){
        return name;
    }
    public void display(){
        System.out.println(name);
    }
    public int getCost(){
        return this.cost;
    }
    public void getInfo(){
        System.out.printf("%6s: стоит %5d, приносит раз в минуту %3d, тратит %3d ресурсов раз в минуту. Требует %1d работников. Профит за минуту врмени %4d\n",
                this.name, this.cost, this.yield, this.spends, this.laborerSpends,this.yield-this.spends);
    }

}
