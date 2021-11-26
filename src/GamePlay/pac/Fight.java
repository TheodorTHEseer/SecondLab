package GamePlay.pac;

import cretures.pac.Creature;
import cretures.pac.Hero;

import java.util.Scanner;

public class Fight {
    public static void heal(Hero player){
        Scanner in = new Scanner(System.in);
        if (player.getHp()<100 && player.Inventory.size()>0){
            int healKey=0;
            System.out.println("Вас ранили. Хотите использовать банку с зельем здоровья?\n" +
                    "[1] - да\n" +
                    "[2] - нет");
            healKey=in.nextInt();
            if(healKey==1){
                player.setHp(player.getHp()+player.Inventory.get(0).getHpBoost());
            }
        }
    }
    public void StartFight (Hero player, Creature enemy){
        System.out.println("Вы встречаете " + enemy.getName() + " в его руках вы наблюдаете " + enemy.Equipment.get(0).getName()+
                " один его удар способен нанести вам" + enemy.getDamage() + "///" + enemy.Equipment.get(0).getWeaponDmg());
            if (player.getDexteritySkill() >= enemy.getDexteritySkill()){
                System.out.println("Но вы оказываетесь ловким воином и бьёте врага первым нанося ему " + player.getDamage()
                + " урона");
                if(enemy.getHp()>0||player.getHp()>0){
                    enemy.setHp(enemy.getHp()-player.getDamage());
                    System.out.println(enemy.getName() +": Аааагрррррр, как больно кусает клинок!");
                    if(enemy.getHp()>0){
                        System.out.println(enemy.getName() +": Но я ещё жив. Умри же ты!");
                        player.setHp(player.getHp()-enemy.getDamage());
                    }
                }
            }

        if (player.getDexteritySkill() < enemy.getDexteritySkill()){
            if(enemy.getHp()>0||player.getHp()>0){
                player.setHp(player.getHp()-enemy.getDamage());
                System.out.println(enemy.getName() +": Вот и пришла твоя смерть!" + player.getName());
                if(player.getHp()>0){
                    System.out.println(player.getName() +": И это всё?!");
                    player.setHp(player.getHp()-enemy.getDamage());
                }
            }
        }
        if (player.getHp()>0){
            System.out.println("Победа");
            if (player.Equipment.get(0).getWeaponDmg()<enemy.Equipment.get(0).getWeaponDmg() &&
                    player.Equipment.get(0).getRequiredLvl()<=player.getLvl()){
                player.Equipment.remove(0);
                player.Equipment.add(enemy.Equipment.get(0));
                System.out.println("Вы взяли себе оружие врага: " + player.Equipment.get(0).getName());
                player.setExp(player.getExp()+1000);
                player.setLvl(player.getExp()/1000);
                player.setMoney(player.getMoney()+100);
            }
        }
        if (player.getHp()<=0)
            System.out.println("Проигрышь");
        System.out.println("Бой окончен");
    }
}
