package Dungeon;

import cretures.pac.Hero;

import static Dungeon.Speaker.showMenu;

public class MainEvent implements Runnable{
    private boolean eventStatus;
    private Hero player;
    public MainEvent (boolean eventStatus, Hero player){
        this.eventStatus=eventStatus;
        this.player = player;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(60000);
            eventStatus=true;
            System.out.println("\u001B[36m"+player.getName()+"\u001B[31m: что-то мне плоховато!\u001B[0m");
            boolean status = true;
            showMenu(status);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
