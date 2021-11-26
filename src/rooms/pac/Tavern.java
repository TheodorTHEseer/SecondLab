package rooms.pac;

import java.util.ArrayList;

public class Tavern extends Thread{ //TODO провести экстерминатус
    public Tavern (String name){
        super(name);
    }
    private static int wallet;
   private void getEnterMes(){
       System.out.println("Один человек из Вашего отряда берёт кошель с деньгами и направляется к стойке.");
   }
   private static void getTradeMes(int wallet){
       System.out.println("Опустощая Вашу казну, наёмники радуются, возможно, что больше они никогда так не погуляют. И им " +
               "предлагают ");
       ArrayList <Integer> winNums = new ArrayList<Integer>();
       String win = null;
       if (win == null){
           throw new NullPointerException();
       }
       System.out.println(win);
   }
   public void run(){
       getTest(wallet);
   }
   public static Exception getTest(int wallet){
       getTradeMes(wallet);
       return new NullPointerException(",','");
   }
}
