package com.company;


import Settlement.Settlement;
import cretures.pac.Enemy;
import cretures.pac.Hero;

import java.util.ArrayList;
import java.util.Scanner;

import static Speaker.LvlEnd.*;
import static Dungeon.Dungeon.generateEnemiesArray;
import static Dungeon.Dungeon.generateLvl;
import static Dungeon.Maps.*;
import static GamePlay.pac.Field.customMap;
import static GamePlay.pac.Field.displayMap;

public class Test {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

    }
    private void setlTest(){
        int bankWallet =10000000;
        Settlement settlement = new Settlement();
        settlement.displayEnterMomlog();
        settlement.displayMenu();
        settlement.getSettlement();
        settlement.download();
        Thread setl = new Thread(settlement, "Settlement");
        setl.start();
        int key = in.nextInt();
        while (key!=5) {
            settlement.displayMenu();
            settlement.upload();
            if (key == 1) {
                System.out.println("Сколько денег вы хотите вложить?");
                int value = in.nextInt();
                if (value <= bankWallet)
                    settlement.investMoney(value);
            }
            if (key == 2) {
                settlement.shopNewBuilding(bankWallet);
            }
            if (key == 3)
                settlement.getBackMoney(bankWallet);
            if (key == 4)
                settlement.displaySettlement();
        }
    }
    private static void endLvlTest(){
        for (int count = 0; count<10; count++){
            System.out.println(getEndDialog(count));
            String answer;
            answer=in.nextLine();
            if (answer.equalsIgnoreCase(getEndTAns(count)))
                System.out.println(getEndAfterY(count) + "Master");
            else
                System.out.println(getEndAfterN(count));
        }
    }
    private static void mapTest(){
        for(int lvlCounter =0; lvlCounter<10;lvlCounter++){
            String [][] map = generateLvl(lvlCounter);
            System.out.println(lvlCounter);
            displayMap(map, hexLvlY.get(lvlCounter), hexLvlX.get(lvlCounter));
        }
    }
    private static void lvlTest(){
        loadInfo();
        Hero player = new Hero("Gay Lord");
        player.xPos=0;
        player.yPos=0;
        String [][] map = generateLvl(8);
        ArrayList <Enemy> enemies = generateEnemiesArray(8);
        customMap(map,player,enemies);
        displayMap(map, 8);
    }
}
