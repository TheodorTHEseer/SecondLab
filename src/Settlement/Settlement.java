package Settlement;

import Settlement.Buildings.*;

import java.util.Scanner;

public class Settlement {
    Scanner in = new Scanner(System.in);
    private int xL=8;
    private int yL=16;
    public Settlement (){

    }
    public Building[][] SettlementMap = new Building[xL][yL];
    public void displaySettlement(){
        for (int count=0; count<xL;count++){
            for (int count1=0;count1<yL;count1++){
                System.out.print(SettlementMap[count][count1].getName());
            }
            System.out.println(" ");
        }
    }
    public Building[][] getSettlement (){
        for (int count=0; count<xL;count++){
            for (int count1=0;count1<yL;count1++){
                SettlementMap[count][count1]=new Locked();
            }
        }
        return SettlementMap;
    }
    public void shopNewBuilding(int bankWallet){
        addBuilding(buyBuilding(bankWallet));
    }

    public Building buyBuilding(int bankWallet){
        System.out.println("Что вы хотите купить" +
                "\n [1] - Торговую площадь." +
                "\n [2] - Шахту." +
                "\n [3] - Ратушу." +
                "");
        Building building = new Building();
        int key = in.nextInt();
        Market market = new Market();
        Mine mine = new Mine();
        TownHall townHall = new TownHall();
        if (key==1){
            bankWallet=bankWallet-market.getCost();
            building=market;
        }
        if (key==2){
            bankWallet = bankWallet - mine.getCost();
            building = mine;
        }
        if (key==3) {
            bankWallet = bankWallet - townHall.getCost();
            building = townHall;
        }
        return building;
    }
    public Building[][]addBuilding ( Building building){
        System.out.println("Введите x координату");
        int xC = in.nextInt();
        System.out.println("Введите y координату");
        int yC = in.nextInt();
        if (xC<=xL&&yL<=yL )
            SettlementMap[xC][yC] = building;
        return SettlementMap;
    }
}
