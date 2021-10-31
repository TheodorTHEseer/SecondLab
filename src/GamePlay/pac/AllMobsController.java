package GamePlay.pac;

import cretures.pac.Creature;
import cretures.pac.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class AllMobsController implements Runnable{
    int fieldXlenght; //+1
    int fieldYlenght; //+1
    int minLenght = 0;
    Creature creature;
    ArrayList <Enemy> enemies;
    public AllMobsController (ArrayList <Enemy> creatures, int y, int x){
        this.fieldXlenght=x-1;
        this.fieldYlenght=y-1;
        this.enemies=creatures;
    }
    Random rnd = new Random();

    @Override
    public void run() {
        for(int count=0; count< enemies.size();count++){
            creature = enemies.get(count);
            int xPosP;
            int yPosP;
            xPosP = creature.xPos + rnd.nextInt(2 + 1) - 1;
            yPosP = creature.yPos + rnd.nextInt(2 + 1) - 1;
            if (xPosP > fieldXlenght || xPosP < minLenght) {
                if (xPosP > fieldXlenght) {
                    xPosP=fieldXlenght;
                }
                if (xPosP < minLenght) {
                    xPosP=minLenght;
                }
            }
            if (yPosP > fieldYlenght || yPosP < minLenght) {
                if (yPosP > fieldYlenght) {
                    yPosP=fieldYlenght;
                }
                if (yPosP < minLenght) {
                    yPosP=minLenght;
                }
            } else {
                creature.xPos = xPosP;
                creature.yPos = yPosP;
            }
        }
    }
}
