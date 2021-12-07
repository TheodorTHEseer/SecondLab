package Dialogs;

import java.util.HashMap;
import java.util.Map;

public class Speaker {
    private static final Map<String, String> speakerMonologs = new HashMap<>();
    private void setSpeakerMonologs(){
        speakerMonologs.put("welcome", "Ваше вашество, добро пожаловать на земли свободной Фракии, путник!");
        speakerMonologs.put("arena", "На арене путник хотел подзаработать легкого и звонкого золота для безбедной жизни, но нашёл испытания своих сил.");
    }
    public void getMonolog(String action){
        speakerMonologs.get(action);
    }

    public static void showMenu(boolean mainEventStatus){
        if (mainEventStatus == false)
            System.out.println("Что вы хотите сделать?\n" +
                    " [1] - заглянуть на арену.\n" +
                    " [2] - нанять отряд и отправить его в экспедицию.\n" +
                    " [3] - зайти в магазин.\n" +
                    " [4] - зайти в банк.\n" +
                    " [5] - сменить имя.\n" +
                    " [6] - сохранить и выйти.\n");

        if (mainEventStatus == true)
            System.out.println("Что вы хотите сделать?\n" +
                    " [1] - заглянуть на арену.\n" +
                    " [2] - нанять отряд и отправить его в экспедицию.\n" +
                    " [3] - зайти в магазин.\n" +
                    " [4] - зайти в банк.\n" +
                    " [5] - сменить имя.\n" +
                    " [6] - сохранить и выйти.\n" +
                    "\u001B[32m [7] - отправиться искать лекарство.\u001B[0m\n" +
                    " [8] - отправиться в посление.");
    }


}
