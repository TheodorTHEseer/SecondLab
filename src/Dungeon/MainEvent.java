package Dungeon;

import cretures.pac.Hero;

public class MainEvent implements Runnable{
    private static boolean eventStatus;
    private Hero player;
    public MainEvent (boolean eventStatus, Hero player){
        this.eventStatus=eventStatus;
        this.player = player;
    }
    public static boolean returnStatuses(){
        return eventStatus;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(60);
            eventStatus=true;
            System.out.println("\u001B[36m"+player.getName()+"\u001B[31m: что-то мне плоховато!\u001B[0m");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
