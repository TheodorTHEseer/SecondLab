package GamePlay.pac;

import cretures.pac.Creature;

import java.util.Random;

public class Controller implements Runnable{
    private Creature creature = new Creature();
    int fieldXlenght; //+1
    int fieldYlenght;
    public Controller (Creature myCreature, int y, int x){
        this.creature = myCreature;
        this.fieldXlenght=x-1;
        this.fieldYlenght=y-1;
    }
    Random rnd = new Random();

    int minLenght = 0;
    @Override
    public void run() {
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
