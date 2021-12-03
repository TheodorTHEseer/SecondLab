package Settlement;

import Settlement.Buildings.Building;
import Settlement.Buildings.Market;

public class Settlement {
    public Settlement (){

    }
    public Building[][] Settlement = new Building[15][15];
    public void displaySettlement(){
        getSettlement(Settlement);
        for (int count=0; count<15;count++){
            for (int count1=0;count1<15;count1++){
                System.out.print(Settlement[count][count1].getName());
            }
            System.out.println(" ");
        }
    }
    private Building[][] getSettlement (Building [][] Settlement){
        for (int count=0; count<15;count++){
            for (int count1=0;count1<15;count1++){
                Settlement[count][count1]=new Market();
            }
        }
        return Settlement;
    }
}
