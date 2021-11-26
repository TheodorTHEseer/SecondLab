package GamePlay.pac;

import cretures.pac.Creature;
import cretures.pac.Hero;
import items.pac.Equipment;
import items.pac.Weaponry;

import javax.swing.*;
import javax.transaction.xa.XAResource;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static GamePlay.pac.GameLogic.SMembersNames;

public class Expedition implements Runnable{
    private int reward;
    private ArrayList<Creature> mySquad;
    private int wannaReward;
    private int wallet;
    private Hero player;
    Random rnd = new Random();
    public Expedition (ArrayList<Creature> mySquad, int wannaReward, int myBankWallet, Hero player){
        this.mySquad=mySquad;
        this.wallet=myBankWallet;
        this.wannaReward = wannaReward;
        this.player=player;
    }

    public static boolean checkExpeditionStatus(ArrayList<Creature>mySquad, int myBankWallet, int wannaReward){
        Random rnd = new Random();
        Scanner in = new Scanner(System.in);
        int moneySpend;
        System.out.println("Сколько вы хотите потратить на экипировку экспедиции? [Стоимость отряда не может быть" +
                " меньше " +
                "500 золотых] Сейчас в отряде находится " + mySquad.size() + " человек.");
        if(myBankWallet<500 && mySquad.size()==0) {
            System.out.println("Недостаточно средств в банке. \n[1] - выход.\n#никто не хочет ввязываться в авантюру за копейки.");
        }
        moneySpend= in.nextInt();
        if (mySquad.size()>0&&moneySpend<500){
            return true;
        }
        else {
            if (moneySpend <= myBankWallet && moneySpend >= 500) {
                System.out.println("Транзакция успешна");
                myBankWallet = myBankWallet - moneySpend;
                for (int count = 0; moneySpend > 499; count++) {
                    moneySpend = moneySpend - 500;
                    String name = SMembersNames[rnd.nextInt(7 - 1) + 1];
                    mySquad.add(new Creature(name, rnd.nextInt(100 - 1) + 1, 1, rnd.nextInt(100 - 1) + 1));
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public void run() {
        try{
            System.out.println("Экспедиция отправлена");
            Thread.sleep(2000);
            while (mySquad.size()>0){
                try {
                    for (int count = 0; count < mySquad.size(); count++) {
                        mySquad.get(count).setHp(mySquad.get(count).getHp() - rnd.nextInt(20 - 1) + 1);
                        if (mySquad.get(count).getHp() <= 0) {
                            mySquad.remove(count);
                            System.out.println(mySquad.get(count).getName() + " умер в экспедиции.");
                        }
                        reward = reward + rnd.nextInt(150 - 50) + 50;
                        Thread.sleep(300);
                        if (reward >= wannaReward)
                            break;
                    }
                    if (reward >= wannaReward)
                        break;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Отряд оказался пуст, воины разбежались");
                }
            }
            if(mySquad.size()>0){
                wallet=wallet+reward;
                player.setMoney(player.getMoney()+wallet);
                System.out.println("Экспедиция успешно вернулась. И принесла вам " +
                      reward + " золотых.");
                int bonusDrop = rnd.nextInt(100-1)+1;
                if(bonusDrop>80&&bonusDrop<90){
                    String [] Tag = {"Золотистый топор из меди", "Большой палаш", "Тупой камень", "Острый стальной палет от забора"};
                    Weaponry drop = new Weaponry(Tag[rnd.nextInt(3-1)+1], rnd.nextInt(100-5)+5,rnd.nextInt(10-2)+2, rnd.nextInt(1000-40)+40);
                    System.out.println("#Так же ваши наёмники приносит Вам оружие: "  + drop.getName());
                    player.Equipment.add(drop);
                }
                if (bonusDrop>=90&& bonusDrop<=100){
                    System.out.println("Так же " + mySquad.get(0).getName() + " держит в руках банку с хилом, протягивая её Вам");
                    Equipment drop = new Equipment("банка с хилом",1);
                    player.Inventory.add(drop);
                }
            }
            if(mySquad.size()==0){
                System.out.println("Весь отряд уничтожен!");
            }
        }
        catch (InterruptedException e){
            System.out.println("Экспедиция была прервана!");
        }
    }
    public static Exception getNPE(ArrayList<Creature> mySquad){
        try {
            if (mySquad.get(0).getName()==null)
                return new NullPointerException("Отряд уничтожен");
        }
        catch (IndexOutOfBoundsException e){
            mySquad.add(null);
            if (mySquad.get(0).getName()==null)
                return new NullPointerException("Отряд уничтожен");
        }
        return new Exception("Всё алрайт");
    }
}
