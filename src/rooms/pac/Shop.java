package rooms.pac;

import cretures.pac.Hero;
import items.pac.Equipment;
import items.pac.Item;
import items.pac.Weaponry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Shop {
    static ArrayList<Equipment> equipment;
    static ArrayList<Weaponry> shopWeapons = new ArrayList<>();

    static String[][] Tags = {{"Стальной", "Железный", "Медный", "Бронзовый"},
            {"одноручный меч", "топор", "кинжал", "полуторный меч"}};//тэги оружия
    static ArrayList<Equipment> shopItems = new ArrayList<>();//Всякие приколы в магазе
    static int shopKey;

    public static void generateItems(){
        Random rnd = new Random();
        for (int count = 0; count < 10; count++) {
            int firstPt = rnd.nextInt(4);
            int secondPt = rnd.nextInt(4);
            String tag = Tags[0][firstPt] + " " + Tags[1][secondPt];
            //Добавление нового в список
            shopWeapons.add(new Weaponry(tag, rnd.nextInt(400 - 20) + 20, rnd.nextInt(20 - 1) + 1, rnd.nextInt(600 - 50) + 50));
        }
        for (int count = 0; count < 10; count++) {
            shopItems.add(new Equipment("Банка с зельем здоровья", rnd.nextInt(10 - 1) + 1));
        }

    }

    public static void getInto(Hero player, int myBankWallet) {
        Scanner in = new Scanner(System.in);
        Random rnd = new Random();
        System.out.println("Что вы хотите купить? \n[1] - оружие. \n[2] - прочую экипировку. \n[3] - выход.");
        shopKey=in.nextInt();
        if (shopKey==1) {
            shopWeapons.stream().sorted((item1, item2) -> item1.getCost()-item2.getCost()).
                    forEach(Weaponry-> System.out.println("Name: "+Weaponry.getName() + ", dmg: "+
                            Weaponry.getWeaponDmg() +", req lvl: "+
                            Weaponry.getRequiredLvl()+ ", cost: " +
                            Weaponry.getCost()));
            System.out.println("Что берём?\n ["+shopWeapons.size()+"] - выход");
            shopKey = in.nextInt();
            try{ //В действительности всё ограничено if
            if (shopKey < shopWeapons.size()&&shopKey>=0) {
                if (myBankWallet >= shopWeapons.get(shopKey).getCost()
                        && player.getLvl() >= shopWeapons.get(shopKey).getRequiredLvl()) {
                    player.Equipment.remove(0);
                    player.Equipment.add(shopWeapons.get(shopKey));
                    myBankWallet = myBankWallet - shopWeapons.get(shopKey).getCost();
                    System.out.println("Транзакция успешна.");
                }
                if (myBankWallet < shopWeapons.get(shopKey).getCost()
                        || player.getLvl() < shopWeapons.get(shopKey).getRequiredLvl()) {
                    System.out.println("Уууу, шекелей не хватает. А может уровня? Вперёд к приключениям!");
                }
            }
            }
           catch(IndexOutOfBoundsException e){
                System.out.println("Прощайте");
            }
        }

        if (shopKey == 2) {
            shopItems.stream().sorted((item1, item2) -> item1.getCost()-item2.getCost()).forEach(Item->
                    System.out.println("Name: "+ Item.getName()+", heal "+
                            Item.getHpBoost()+", req lvl: " +
                            Item.getRequiredLvl()+", cost: "+
                            Item.getCost()));
            System.out.println("Что берём?" +
                    " ["+shopItems.size() +"] - выход");
            shopKey = in.nextInt();

            try {
                if (myBankWallet < shopItems.get(shopKey).getCost()
                        || player.getLvl() < shopItems.get(shopKey).getRequiredLvl()) {
                    System.out.println("Уууу, шекелей не хватает. А может нужно ещё подкачаться?");
                }
                if (myBankWallet >= shopItems.get(shopKey).getCost()
                        && player.getLvl() >= shopItems.get(shopKey).getRequiredLvl()) {
                    player.Inventory.add(shopItems.get(shopKey));
                    myBankWallet = myBankWallet - shopItems.get(shopKey).getCost();
                    System.out.println("Транзакция успешна.");
                }
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Прощайте");
            }
        }
    }
}
