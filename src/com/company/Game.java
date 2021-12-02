package com.company;

import FileMgmt.Start;
import cretures.pac.Hero;

import static FileMgmt.MgmtCfg.*;

public class Game {
    public static void main(String[] args) {

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

}
