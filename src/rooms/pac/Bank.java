package rooms.pac;

import cretures.pac.Creature;
import cretures.pac.Hero;

import java.util.Scanner;

public class Bank {
    static int convert;


    public static void getInto(Hero player, int myBankWallet){
        Scanner in = new Scanner(System.in);
        int key;
        System.out.println("Есть желание положить деньги в банк, чтобы совершать покупки в магазинах?\n" +
                "[1] - да\n" +
                "[2] - нет");
        key = in.nextInt();
        if (key==1){
            if(player.getMoney()<0)
                System.out.println("Есть проблема: ваши карманы пусты.");
            if(player.getMoney()>0){
                System.out.println("Сколько вы хотите положить в банк? У вас в кармане сейчас болтается " +
                        player.getMoney()+ " золотых.");
                System.out.println("А в банке лежит " + myBankWallet + " золотых.");
                convert=in.nextInt();
                if(convert> player.getMoney()){
                    System.out.println("Столько у вас нет.");
                }
                if(convert<= player.getMoney()){
                    System.out.println("Транзакция успешна.");
                    player.setMoney(player.getMoney()-convert);
                }
            }
        }
    }
}
