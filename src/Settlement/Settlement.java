package Settlement;

import Settlement.Buildings.*;

import java.util.Scanner;

import static FileMgmt.Color.*;

public class Settlement implements Runnable{
    Scanner in = new Scanner(System.in);
    private int xL=8;
    private int yL=14;
    private int moneyValue;
    private void displayMoney(){
        System.out.println("В казне " + moneyValue + " золотых!");
    }
    public void gainMoney(int money){
        moneyValue=moneyValue+money;
    }
    public void investMoney(int mMoney){ //TODO добавить инвестиции
        if (mMoney>0)
            gainMoney(mMoney);
    }
    public int getBackMoney(int mMoney){//TODO добавить вывод денег
        System.out.println("Сколько хотите забрать?");
        int value = in.nextInt();
        if (value<=moneyValue){
            mMoney=mMoney+value;
            moneyValue=moneyValue-value;
        }
        else
            System.out.println("Вы ошиблись в цифрах!");
        return mMoney;
    }

    public Settlement (){
    }
    public Building[][] SettlementMap = new Building[xL][yL];
    public void displayEnterMomlog(){
        System.out.printf("\u001B[36mПривратник\u001B[0m: Добро пожаловать на ваши замли, милорд!\n");
    }
    public void displayMenu(){
        System.out.printf(" [1] - Инвестировать в посление.\n" +
                " [2] - Приступить к строительству. \n" +
                " [3] - Забрать казну. \n" +
                " [4] - Посмотреть карту. \n" +
                " [5] - Выйти.\n");
        displayMoney();
        displayDef();
    }
    private void displayDef(){
        int def = laborersP() - laborersM();
        if (def<0)
            System.out.println("В послении не хватает " +(def*(-1))+ " рабочих.");
        if (def>0)
            System.out.println("В поселении свободны от работы " +def+ " рабочих.");
    }
    public void displaySettlement(){
        System.out.printf("\u001B[32mx\\y%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s%6s\u001B[0m\n",
                "0↴","1↴","2↴","3↴","4↴","5↴","6↴","7↴","8↴","9↴","10↴","11↴","12↴","13↴");
        for (int count=0; count<xL;count++){
            System.out.print(greenA+count + "↱ " + cResetA);
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
        displayMoney();
        addBuilding(buyBuilding(bankWallet));
    }

    public Building buyBuilding(int bankWallet){
        System.out.println("Что вы хотите купить?" +
                "\n [1] - Торговую площадь." +
                "\n [2] - Шахту." +
                "\n [3] - Ратушу.\n" +
                " [4] - Ничего не хочу покупать, хочу посмотреть информацию о зданиях. \n" +
                " [5] - Выйти.");
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
        if (key==5){
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
        if (xC<=xL&&yC<=yL && building.getCost()<=moneyValue) {
            SettlementMap[xC][yC] = building;
            moneyValue=moneyValue-building.getCost();
        }
        else
            System.out.printf("Индексы доступные для х координаты: %2d, для у координаты: %2d. \n" +
                    "И не забудьте проверить баланс!\n", xL-1,yL-1 );
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
    private int laborersM(){
        int count=0;
        for (int count0=0; count0<xL; count0++){
            for (int count1=0; count1<yL; count1++){
                 count=count+SettlementMap[count0][count1].getLaborerSpends();
            }
        }
        return count;
    }
    private int laborersP(){
        int count=0;
        for (int count0=0; count0<xL; count0++){
            for (int count1=0; count1<yL; count1++){
                if(SettlementMap[count0][count1].getName().equals("[\u001B[36mHall\u001B[0m]") == true){
                    count=count+1;}
            }
        }
        return count*3;
    }

    private int getFine(){
        int fine =0;
        if (laborersM()>laborersP()){
            int def = laborersP()-laborersM();
            def = def*(-1);
            if (def>=5&&def<10){
                fine = def*100;
                System.out.println("В посленении \u001B[31mне хватает людей: " + def + cResetA);
            }
            if (def>=10&&def<20){
                fine = def*150;
                System.out.println("В посленении очень сильно \u001B[31mне хватает людей: " + def + cResetA);
            }
            if (def>=20){
                fine = def*200;
                System.out.println("Милорд, люди исчезли, осталось только: " + def);
            }
            System.out.println("Прибыль снижена!");
        }
        return fine;
    }
    @Override
    public void run() {
        for (int count=1; count>0; count++) {
            try {
                Thread.sleep(60000);
                if ((countYield()-countCosts()>=0))
                    System.out.println("Казна поселения получила " + (countYield() - countCosts())+ " золотых!");
                if ((countYield()-countCosts()<0))
                    System.out.println("Казна ничего не получила, только потртила " + (countYield() - countCosts()) + " золотых!");
                gainMoney(countYield() - countCosts() - getFine());
                displayMenu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
