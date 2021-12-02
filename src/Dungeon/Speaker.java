package Dungeon;

import java.util.HashMap;
import java.util.Map;

public class Speaker {
    private static Map<Integer, String> lvlEnter = new HashMap<>();

    public static void showMenu(boolean mainEventStatus){
        if (mainEventStatus == false) {
            System.out.println("Что вы хотите сделать?\n" +
                    "[1] - заглянуть на арену.\n" +
                    "[2] - нанять отряд и отправить его в экспедицию.\n" +
                    "[3] - зайти в магазин.\n" +
                    "[4] - зайти в банк.\n" +
                    "[5] - сменить имя.\n" +
                    "[6] - сохранить и выйти.\n");
        }
        if (mainEventStatus == true){
            System.out.println("Что вы хотите сделать?\n" +
                    "[1] - заглянуть на арену.\n" +
                    "[2] - нанять отряд и отправить его в экспедицию.\n" +
                    "[3] - зайти в магазин.\n" +
                    "[4] - зайти в банк.\n" +
                    "[5] - сменить имя.\n" +
                    "[6] - сохранить и выйти.\n" +
                    "\u001B[32m[7] - отправиться искать лекарство.\u001B[0m");
        }
    }
}
