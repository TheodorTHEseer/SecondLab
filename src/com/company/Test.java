package com.company;


import Settlement.Buildings.Market;
import Settlement.Settlement;
import cretures.pac.Enemy;
import cretures.pac.Hero;

import java.util.ArrayList;
import java.util.Scanner;

import static Dialogs.LvlEnd.*;
import static Dungeon.Dungeon.generateEnemiesArray;
import static Dungeon.Dungeon.generateLvl;
import static Dungeon.Maps.*;
import static GamePlay.pac.Field.customMap;
import static GamePlay.pac.Field.displayMap;

public class Test {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int bankWallet =10000000;
        Market market = new Market();
        market.display();
        Settlement settlement = new Settlement();
        settlement.getSettlement();
        settlement.displaySettlement();
        settlement.shopNewBuilding(bankWallet);
        settlement.displaySettlement();
        //settlement.getInfoAboutBuildings();
    }
    private static void endLvlTest(){
        for (int count = 0; count<10; count++){
            System.out.println(getEndDialog(count));
            String answer;
            answer=in.nextLine();
            if (answer.toLowerCase().equals(getEndTAns(count).toLowerCase()))
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
