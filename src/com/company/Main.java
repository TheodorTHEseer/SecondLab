package com.company;

import GamePlay.pac.*;
import cretures.pac.Creature;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import items.pac.Equipment;
import items.pac.Item;
import items.pac.Weaponry;
import rooms.pac.Bank;
import rooms.pac.Shop;

import java.awt.font.ShapeGraphicAttribute;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        //Переменные для изменения параметров
        int yLenght = 3;//кол-во строк
        int xLenght = 7;//кол-во столбцов
        int enemiesMass = 2;//Кол-во врагов
        int myBankWallet=100;
        //Классы
        Random rnd = new Random();
        Field field = new Field(yLenght, xLenght);//Чтобы работать с картой
        Fight fight = new Fight();
        String[] SMembersNames = {"Марис","Кас","Биттер","Олег","Дамир","Радис", "Кир","Ваард"};//Для членов отряда имена
        //Не уверен, что тут все имена - имена
        String MyMap[][] = new String[yLenght][xLenght];//карта для комнаты
        ArrayList<Creature> mySquad = new ArrayList<>();
        ArrayList<Weaponry> myStorage = new ArrayList<>();//Хранилище, в перспективе, оно будет хранить собранное оружие,
        //которое человек не хочет выкидывать,
        //но имеет желание продать
        ArrayList<Enemy> enemies = new ArrayList<>();//Множество врагов
        ArrayList<Weaponry> shopWeapons = new ArrayList<>();//Оружие в магазине
        ArrayList<Equipment> shopItems = new ArrayList<>();//Всякие приколы в магазе
        Weaponry mySword = new Weaponry("Мой меч", 1, rnd.nextInt(50- 1)+1);
        Hero player = new Hero("Я", 100, 100, 1000, 1, 1000);//хп всегда по дефолту 100
        GameLogic.startKit(player,mySword,xLenght,yLenght);
        int key =0;
        Shop.generateItems();
        while (player.getHp()>0) {
            System.out.println("Что вы хотите сделать?\n"+
                    "[1] - войти в комнату с врагами.\n"+
                    "[2] - нанять отряд и отправить его в экспедицию.\n" +
                    "[3] - зайти в магазин.\n" +
                    "[4] - зайти в банк.\n" +
                    "[5] - выйти из игры.");
            Scanner in = new Scanner(System.in);
            key = in.nextInt();
            //Цикл для комнаты
            if (key==1 ) {
                GameLogic.generateEnemies(enemiesMass, enemies, xLenght, yLenght);
                while (enemies.size() > 0) {
                    if (player.getHp() <= 0)
                        break; // Чтобы ливнуть из цикла при смерти гг
                    player.setDamage(GameLogic.calcDPS(player.Equipment, player.getDefaultDamage()));//Чтобы каждый ход отслеживать актульное оружие и дамаг
                    if (player.getHp()<100 && player.Inventory.size()>0){
                        int healKey=0;
                        System.out.println("Вас ранили. Хотите использовать банку с зельем здоровья?\n" +
                                "[1] - да\n" +
                                "[2] - нет");
                        healKey=in.nextInt();
                        if(healKey==1){
                            player.setHp(player.getHp()+player.Inventory.get(0).getHpBoost());
                        }
                    }
                    System.out.println("Врагов осталось:" + enemies.size());

                    System.out.println("У вас " + player.getHp() + " Hp, а в руках вы держите: " +
                            player.Equipment.get(0).getName() +
                            ", один его удар наносит " + player.Equipment.get(0).getWeaponDmg()
                            + " урона");
                    field.defaultMap(MyMap);
                    //Потоки
                    Thread Controller = new Thread(new Controller(player, yLenght, xLenght), "PlayerThread");
                    Controller.start();
                    Thread AllMobsController = new Thread(new AllMobsController(enemies, yLenght, xLenght), "MobsThread");
                    AllMobsController.start();

                    field.customMap(MyMap, player, enemies);//Для того, чтобы карта обновилась до актуальной
                    if (field.fightStatusCheck(player, enemies) == true) {//Если на клетке гг стоит враг, то начинается файт
                        fight.StartFight(player, enemies.get(field.fightStatusEnemyIndex(player, enemies)));
                        Thread.sleep(5300);
                    }
                    field.checkAliveStatus(enemies);//Проверяем кто жив
                    //Отрисовка карты
                    field.displayMap(MyMap, yLenght, xLenght);
                    Thread.sleep(1000);
                    System.out.println("\n\n");
                }
                //Врагов нет, а вы живы
                if (player.getHp() > 0) {
                    System.out.println("Поздравляю с победой");
                    key=0;
                }
            }
            if (key==2){
                int moneySpend;
                int wannaReward;
                System.out.println("Сколько вы хотите потратить на экипировку экспедиции? [Стоимость отряда не может быть" +
                        " меньше " +
                        "500 золотых] Сейчас в отряде находится " + mySquad.size() + " человек.");
                if(myBankWallet<500 && mySquad.size()==0) {
                    Thread.sleep(200);
                    System.out.println("Недостаточно средств в банке. \n[1] - выход, никто просто не захочет вступить в Ваш отряд.");
                }
                moneySpend= in.nextInt();
                if (moneySpend<=myBankWallet){
                    System.out.println("Транзакция успешна");
                    myBankWallet=myBankWallet-moneySpend;
                    System.out.println("А сколько вы хотите получить после возвращения экспедиции?");
                    wannaReward=in.nextInt();
                    for (int count=0;moneySpend>499;count++ ){
                        moneySpend=moneySpend-500;
                        String name = SMembersNames[rnd.nextInt(7-1)+1];
                        mySquad.add(new Creature(name, rnd.nextInt(100-1)+1, 1, rnd.nextInt(100-1)+1) );
                    }
                    Thread expedition = new Thread(new Expedition(mySquad, wannaReward, myBankWallet,player), "ExpeditionThread");
                    expedition.start();
                }
                key=0;
            }
            if(key==3){
                Shop.getInto(player,myBankWallet);
            }
            if (key==4){
                int reserve = player.getMoney();
                Bank.getInto(player, myBankWallet);
                reserve=reserve-player.getMoney();
                myBankWallet=+reserve;//TODO переписать банк для работы с отрядом
            }
            if (key==5)
                break;
            if (key>=6){
                for (int count=1;count>0;count++){
                    Thread.sleep(100);
                    System.out.println("Agent Orange");
                    if(count>2530)
                        break;
                }
            }
        }
        System.out.println("Спасибо за игру.");
    }
}