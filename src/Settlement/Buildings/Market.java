package Settlement.Buildings;

public class Market extends Building{
   public Market (){
        name = "[\u001B[36mMark\u001B[0m]";
        cost = 4500;
        yield = 100;
    }
    public void buyMarket(int bankWallet){
        bankWallet=bankWallet-cost;
    }
    public void display(){
        System.out.println(name);
    }
}
