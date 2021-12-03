package Settlement;

import Settlement.Buildings.*;

import java.util.Scanner;

import static FileMgmt.Color.*;

public class Settlement implements Runnable{
    Scanner in = new Scanner(System.in);
    private int xL=8;
    private int yL=14;
    private int moneyValue;
    public void gainMoney(int money){
        moneyValue=moneyValue+money;
    }

    public Settlement (){
    }
    public Building[][] SettlementMap = new Building[xL][yL];
    public void displaySettlement(){
        System.out.printf("\u001B[32m%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s\u001B[0m\n",
                "0⟱","1⟱","2⟱","3⟱","4⟱","5⟱","6⟱","7⟱","8⟱","9⟱","10⟱","11⟱","12⟱","13⟱");
        for (int count=0; count<xL;count++){
            System.out.print(greenA+count + "⇶ " + cResetA);
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
        System.out.println("Что вы хотите купить?" +
                "\n [1] - Торговую площадь." +
                "\n [2] - Шахту." +
                "\n [3] - Ратушу.\n" +
                " [4] - Я ничего не хочу покупать, хочу посмотреть информацию о зданиях.");
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
        if (key==4){
            getInfoAboutBuildings();
            System.out.println("Так что вы возьмёте?");
            building=new Locked();
        }
        return building;
    }
    public Building[][]addBuilding ( Building building){
        if (building.getName().equals(new Locked().getName()))
            return SettlementMap;
        System.out.println("Введите x координату");
        int xC = in.nextInt();
        System.out.println("Введите y координату");
        int yC = in.nextInt();
        if (xC<=xL&&yC<=yL )
            SettlementMap[xC][yC] = building;
        else
            System.out.printf("Индексы доступные для х координаты: %2d, для у координаты: %2d \n", xL-1,yL-1 );
        return SettlementMap;
    }

    public void getInfoAboutBuildings(){
        Market market = new Market();
        Mine mine = new Mine();
        TownHall townHall = new TownHall();
        market.getInfo();
        mine.getInfo();
        townHall.getInfo();
        System.out.printf(townHall.getName() + blueA +": пополняет население вашего поселения! На " + townHall.getLaborersValue() + " в минуту!\n"+ cResetA);
    }
    private int countBuildings(String name){
        int count= 0;
        for (int count0=0; count0<xL; count0++){
            for (int count1=0; count1<yL; count1++){
               if( SettlementMap[count0][count1].getName().equals(name))
                   count=+1;
            }
        }
        return count;
    }
    private int countYield(){
        int yield;
        yield = countBuildings("[\u001B[36mMine\u001B[0m]")*55+countBuildings("[\u001B[36mMark\u001B[0m]")*100;
        return yield;
    }
    private int countCosts(){
        int costs;
        costs = countBuildings("[\u001B[36mMine\u001B[0m]")*20+countBuildings("[\u001B[36mMark\u001B[0m]")*10+countBuildings("[\u001B[36mHall\u001B[0m]")*50;
        return costs;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
            System.out.println("Вы получили"+(countYield()-countCosts()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
