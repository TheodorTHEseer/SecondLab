package com.company;

import FileMgmt.Start;
import GamePlay.pac.*;
import cretures.pac.Creature;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import items.pac.Equipment;
import items.pac.Weaponry;
import rooms.pac.Bank;
import rooms.pac.Shop;
import GamePlay.pac.SquadException;

import java.io.IOException;
import java.util.*;

import static FileMgmt.MgmtCfg.*;
import static GamePlay.pac.Expedition.checkExpeditionStatus;
import static GamePlay.pac.Expedition.getNPE;
import static GamePlay.pac.Fight.heal;
import static GamePlay.pac.GameLogic.gameMenuDisplay;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Hero player = new Hero("My Hero", 100, 100, 1000, 1, 1000); //Даже если сейв умер, то персонаж будет создан
        int myBankWallet=100;//Деньги на банковском счете по дефолту
        int playerIndex;
        if (Start.uploadCheck()==false) {
            Start.upload();
            createCfg();
            player.setName(useCfg(player.getName()));
        }
        else{
            loadGame(player,myBankWallet);
            System.out.println("С возвращением! " + player.getName());
        }
        Thread.sleep(1575);
        int yLenght = 3;//кол-во строк
        int xLenght = 3;//кол-во столбцов
        int enemiesMass = 2;//Кол-во врагов
        //Классы
        Random rnd = new Random();
        Field field = new Field(yLenght, xLenght);//Чтобы работать с картой
        Fight fight = new Fight();
        String[] SMembersNames = {"Марис","Кас","Биттер","Олег","Дамир","Радис", "Кир","Ваард"};//Для членов отряда имена
        String MyMap[][] = new String[yLenght][xLenght];//карта для комнаты
        ArrayList<Creature> mySquad = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();//Множество врагов
        ArrayList<Weaponry> shopWeapons = new ArrayList<>();//Оружие в магазине
        ArrayList<Equipment> shopItems = new ArrayList<>();//Всякие приколы в магазине
        Weaponry mySword = new Weaponry("Мой меч", 1, rnd.nextInt(50- 1)+1);
        FHW.generate();
        GameLogic.startKit(player,mySword,xLenght,yLenght);
        int key =0;
        Shop.generateItems();
        while (player.getHp()>0) {
            gameMenuDisplay();
            Scanner in = new Scanner(System.in);
            try {
                key = in.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Введите цифру!");
                Thread.sleep(1200);
            }
            //Цикл для комнаты
            if (key==1) {//TODO разбить на методы, часть методов сдлеать приватными, засунуть в fight
                GameLogic.generateEnemies(enemiesMass, enemies, xLenght, yLenght);
                while (enemies.size() > 0) {
                    if (player.getHp() <= 0)
                        break; // Чтобы ливнуть из цикла при смерти гг
                    player.setDamage(GameLogic.calcDPS(player.Equipment, player.getDefaultDamage()));//Чтобы каждый ход отслеживать актульное оружие и дамаг
                    heal(player);//Отхилить гг
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
                int wannaReward =0;
                System.out.println("Сколько денег вы хотите вы хотите получить от экспедиции?");
                wannaReward=in.nextInt();
                if (checkExpeditionStatus(mySquad,myBankWallet,wannaReward)==true){
                    Thread expedition = new Thread(new Expedition(mySquad, wannaReward, myBankWallet,player), "ExpeditionThread");
                    expedition.start();
                    new SquadException(expedition, getNPE(mySquad));
                }
                else {
                    System.out.println("#загляни в банк и обращайся снова");
                }
            }
            if(key==3){
                Shop.getInto(player,myBankWallet);
            }
            if (key==4){
                myBankWallet=Bank.getInto(player, myBankWallet);
            }
            if (key==5) {
                renameCfg();
                player.setName(useCfg(player.getName()));
            }
            if (key==6){
                gameSave(player, myBankWallet);
                break;
            }
            if (key==7){
                System.out.println("Ага угу ну всё.");
                Thread.sleep(1337);
                throw new NullPointerException("sli" +
                        "to");
            }
        }
        System.out.println("Спасибо за игру.");
    }
}
