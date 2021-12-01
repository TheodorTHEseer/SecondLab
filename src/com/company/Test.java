package com.company;


import java.util.Scanner;

import static Dialogs.LvlEnd.*;

public class Test {
     static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        loadDialogs();
        endLvlTest();


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
}
