package com.company;

import FileMgmt.Start;
import GamePlay.pac.AllMobsController;
import GamePlay.pac.Controller;
import GamePlay.pac.GameLogic;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import items.pac.Weaponry;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static Dialogs.LvlEnd.*;
import static Dungeon.Maps.*;
import static FileMgmt.Color.*;
import static FileMgmt.MgmtCfg.*;
import static GamePlay.pac.Field.*;
import static GamePlay.pac.Fight.StartFight;
import static GamePlay.pac.Fight.heal;

public class Game {
    static Scanner in = new Scanner(System.in);
    static Random rnd = new Random();
    public static void main(String[] args) throws InterruptedException {
        loadInfo();
        loadDialogs();
        int bankWallet = 0;
        Hero player =checkSaves(bankWallet);
        Weaponry mySword = new Weaponry("Мой меч", 1, rnd.nextInt(50- 1)+1);
        player.Equipment.add(mySword);
        for (int lvlValue = 0; lvlValue<10; lvlValue++){
            fightInRoom(lvlValue, player);
            Thread.sleep(1000);
            if (player.getHp()<=0)
                break;
            endLvlDialog(player, lvlValue);
        }
    }
    private static int giveMoney(){
        int bankWallet=0;
        return bankWallet;
    }
    private static Hero checkSaves(int bankWallet){
        Hero player = new Hero("My Hero", 100, 100, 1000, 1, 1000);
        if (Start.uploadCheck()==false) {
            Start.upload();
            createCfg();
            player.setName(useCfg(player.getName()));
        }
        else{
            loadGame(player, bankWallet);
            System.out.println("С возвращением! " + player.getName());
        }
        return player;
    }
    private static void gameLoop(Hero player, int money){


    }
    private static String [][] defaulMap (String [][] map,int lvlValue ,String defaultSymbol){
        for (int count=0; count<hexLvlY.get(lvlValue);count++){
            for(int count2=0; count2<hexLvlX.get(lvlValue);count2++){
                map[count][count2] = defaultSymbol;
            }
        }
        return map;
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
            if (fightStatusCheck(player, enemies) == true) {//Если на клетке гг стоит враг, то начинается файт
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
            if (answer.toLowerCase().equals(getEndTAns(lvlValue).toLowerCase())) {
                System.out.println(getEndAfterY(lvlValue) + purpleA + player.getName() + cResetA);
            }
            else {
                System.out.println(getEndAfterN(lvlValue));
                player.setHp(player.getHp()-10);
            }
    }
}

