package com.company;

import Dungeon.MainEvent;
import FileMgmt.Logs;
import FileMgmt.Start;
import GamePlay.pac.*;
import Settlement.Settlement;
import cretures.pac.Creature;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import rooms.pac.Bank;
import rooms.pac.Shop;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Speaker.LvlEnd.*;
import static Dungeon.MainEvent.returnStatuses;
import static Dungeon.Maps.*;
import static FileMgmt.Color.*;
import static FileMgmt.Logs.logs;
import static FileMgmt.MgmtCfg.*;
import static FileMgmt.Start.loadCurrentLvl;
import static GamePlay.pac.Expedition.checkExpeditionStatus;
import static GamePlay.pac.Expedition.getNPE;
import static GamePlay.pac.Field.*;
import static GamePlay.pac.Fight.StartFight;
import static GamePlay.pac.Fight.heal;
import static Speaker.Speaker.*;

public class Game {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Thread logsThread = new Thread(new Logs(),"LogsThread");
        logsThread.start();
        loadInfo();
        loadDialogs();
        int myBankWallet = 0;
        boolean eventStatus = false;
        int bankWallet = 0;
        int currentLvl = loadCurrentLvl();
        Hero player = loadSaves(bankWallet);
        player.giveStartedSword();
        ArrayList<Creature> mySquad = new ArrayList<>();
        Thread MainEvent = new Thread(new MainEvent(eventStatus, player), "MainEventThread");
        MainEvent.start();
        loadMonologs();
        while (player.getHp() > 0) {
            showMenu(returnStatuses());
            int key = in.nextInt();
            if (key == 1)
                fightInRoom(player);
            if (key == 2) {
                int wannaReward = 0;
                System.out.println("Сколько денег вы хотите вы хотите получить от экспедиции?");
                wannaReward = in.nextInt();
                if (checkExpeditionStatus(mySquad, myBankWallet, wannaReward) == true) {
                    Thread expedition = new Thread(new Expedition(mySquad, wannaReward, myBankWallet, player), "ExpeditionThread");
                    expedition.start();
                    new SquadException(expedition, getNPE(mySquad));
                } else {
                    System.out.println("загляни в банк и обращайся снова");
                }
            }
            if (key == 3) {
                Shop.getInto(player, myBankWallet);
            }
            if (key == 4) {
                myBankWallet = Bank.getInto(player, myBankWallet);
            }
            if (key == 5) {
                renameCfg();
                player.setName(useCfg(player.getName()));
            }
            if (key == 6) {
                saveGame(player, myBankWallet);
                saveGame(currentLvl);
                break;
            }
            if (key == 7 && returnStatuses()) {
                Thread.sleep(6000);
                showMenu(returnStatuses());
                for (int lvlValue = 0; lvlValue < 10; lvlValue++) {
                    if (currentLvl >= lvlValue) {
                        lvlValue=currentLvl;
                    }
                    getMonolog(lvlValue);
                    fightInRoom(lvlValue, player);
                    Thread.sleep(1000);
                    if (player.getHp() <= 0)
                        break;
                    endLvlDialog(player, lvlValue);
                    currentLvl++;
                }
            }
            if (key == 8 && returnStatuses()) {
                Settlement settlement = new Settlement();
                settlement.displayEnterMomlog();
                settlement.getSettlement();
                settlement.download();
                Thread setl = new Thread(settlement, "Settlement");
                setl.start();
                settlement.displayMenu();
                int keyS = in.nextInt();
                while (keyS != 5) {
                    settlement.displayMenu();
                    settlement.upload();
                    if (keyS == 1) {
                        System.out.println("Сколько денег вы хотите вложить?");
                        int value = in.nextInt();
                        if (value <= bankWallet)
                            settlement.investMoney(value);
                    }
                    if (keyS == 2) {
                        settlement.shopNewBuilding(bankWallet);
                    }
                    if (keyS == 3)
                        settlement.getBackMoney(bankWallet);
                    if (keyS == 4)
                        settlement.displaySettlement();
                    if (keyS == 5)
                        break;
                    settlement.displayMenu();
                    keyS = in.nextInt();
                }
                setl.stop();
            }
        }
        System.out.println("Спасибо за игру! "+cyanA + player.getName());
        MainEvent.stop();
        logsThread.stop();


    }

    private static Hero loadSaves(int bankWallet){
        Hero player = new Hero("My Hero", 100, 100, 1000, 1, 1000);
        if (Start.uploadCheck()==false) {
            Start.upload();
            createCfg();
            player.setName(useCfg(player.getName()));
        }
        else{
            loadGame(player, bankWallet);
            System.out.println("С возвращением! \u001B[36m" + player.getName()+"\u001B[0m");
        }
        logs.add("Cfg загружен");
        return player;
    }
    private static String [][] defaulMap (String [][] map,int lvlValue ,String defaultSymbol){
        for (int count=0; count<hexLvlY.get(lvlValue);count++){
            for(int count2=0; count2<hexLvlX.get(lvlValue);count2++){
                map[count][count2] = defaultSymbol;
            }
        }
        logs.add("Карта загружена");
        return map;
    }
    private static void fightInRoom(Hero player) throws InterruptedException {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int enemiesMass = 4; //Кол-во врагов
        int yLenght =4;
        int xLenght =4;
        String MyMap[][] = new String[yLenght][xLenght];
        Field field = new Field(yLenght, xLenght);
        Fight fight = new Fight();
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
            logs.add("Ход на карте удался");
        }
        //Врагов нет, а вы живы
        if (player.getHp() > 0) {
            System.out.println(greenA + "Поздравляю с победой"+ cResetA);
            Thread.sleep(3000);
            logs.add("Битва на уровне выиграна");
        }
    }
    private static void fightInRoom(int lvlValue, Hero player) throws InterruptedException {
        ArrayList<Enemy> enemies = new ArrayList<>();
        String [][] map = new String[hexLvlY.get(lvlValue)][hexLvlX.get(lvlValue)];
        GameLogic.generateEnemies(enemyLvl.get(lvlValue), enemies, hexLvlX.get(lvlValue), hexLvlY.get(lvlValue));
        player.xPos=0;
        player.yPos=0;
        while (enemies.size() > 0) {
            if (player.getHp() <= 0)
                break;
            player.setDamage(GameLogic.calcDPS(player.Equipment, player.getDefaultDamage()));
            heal(player);
            defaulMap(map, lvlValue, "[ ]");
            System.out.printf("Вы сейчас в %2d комнате c %2d врагами и у Вас %3d hp!\n", lvlValue, enemies.size(),player.getHp());
            customMap(map,player,enemies);
            Thread Controller = new Thread(new Controller(player, hexLvlY.get(lvlValue), hexLvlX.get(lvlValue)), "PlayerThread");
            Controller.start();
            Thread AllMobsController = new Thread(new AllMobsController(enemies, hexLvlY.get(lvlValue), hexLvlX.get(lvlValue)), "MobsThread");
            AllMobsController.start();
            if (fightStatusCheck(player, enemies)) {//Если на клетке гг стоит враг, то начинается файт
                StartFight(player, enemies.get(fightStatusEnemyIndex(player, enemies)));
            }
            checkAliveStatus(enemies);//Проверяем кто жив
            displayMap(map, lvlValue);
            Thread.sleep(200);
            System.out.println("\n\n");
        }
        //Врагов нет, а вы живы
        if (player.getHp() > 0) {
            System.out.println(greenA + "Поздравляю с победой в "+lvlValue +" комнате!\n"+purpleA+"Вы видите босса."+ cResetA);
            Thread.sleep(3000);
        }
    }
    private static void endLvlDialog(Hero player, int lvlValue){
            System.out.println(getEndDialog(lvlValue));
            String answer;
            answer=in.nextLine();
            if (answer.equalsIgnoreCase(getEndTAns(lvlValue))) {
                System.out.println(getEndAfterY(lvlValue) + purpleA + player.getName() + cResetA);
            }
            else {
                System.out.println(getEndAfterN(lvlValue));
                player.setHp(player.getHp()-10);
            }
    }
}

