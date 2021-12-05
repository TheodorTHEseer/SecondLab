package Settlement.Buildings;

public class Building {
    public int getLaborerSpends() {
        return laborerSpends;
    }

    public int getxCord() {
        return xCord;
    }

    private int xCord;

    public int getyCord() {
        return yCord;
    }

    private int yCord;
    protected int laborerSpends;
    protected String name;
    protected int cost;
    protected int yield;
    protected int spends;
    public String getName (){
        return name;
    }
    public int getYield(){
        return this.yield;
    }
    public int getSpends(){
        return this.spends;
    }
    public int getCost(){
        return this.cost;
    }
    public void getInfo(){
        System.out.printf("%6s: стоит %5d, приносит раз в минуту %3d, тратит %3d ресурсов раз в минуту. Требует %1d работников. Профит за минуту врмени %4d\n",
                this.name, this.cost, this.yield, this.spends, this.laborerSpends,this.yield-this.spends);
    }
    public void setCord(int xCord, int yCord){
        this.xCord = xCord;
        this.yCord = yCord;
    }
    public String getStringInfo(){
        String info = this.name+"," + this.xCord+"," + this.yCord;
        return info;
    }

}
