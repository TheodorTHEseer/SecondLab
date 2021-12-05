package GamePlay.pac;

import cretures.pac.Creature;
import cretures.pac.Enemy;
import cretures.pac.Hero;
import items.pac.Equipment;
import items.pac.Weaponry;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    public static String[][] Names = {{"Мол", "Гоур", "Фре", "Них", "Кир", "Сон", "Вор", "Моор", "Стэв", "Дне"},
            {"ов", "гар", "'Даал", "кац", "'Та", "лав", "'Муур", "ки", "сан", "эст"},
            {" Беспощадный", " Кровавый", " Безумный", " Еретик", " Палач", " Сверепый", " Смертный",
                    " Последний палач Абаддона", " Проклятый", " Проводник"}};//имена врагов
    public static String[][] Tags = {{"Стальной", "Железный", "Медный", "Бронзовый"},
            {"одноручный меч", "топор", "кинжал", "полуторный меч"}};//тэги оружия
    static String[] SMembersNames = {"Марис","Кас","Биттер","Олег","Дамир","Радис", "Кир","Ваард"};//Для членов отряда имена
    static ArrayList<Creature> mySquad = new ArrayList<>();
    static ArrayList<Weaponry> enemyWeapons = new ArrayList<>();
    public static int dps;
    public static int calcDPS(ArrayList<Weaponry> weapon, int defaultDamage) {
        dps = defaultDamage + weapon.get(0).getWeaponDmg();
        return dps;
    }
    static public void generateEnemies(int enemiesMass, ArrayList <Enemy> enemies, int xLenght, int yLenght){
        Random rnd = new Random();
        for (int count = 0; count < enemiesMass; count++) {
            int firstPt = rnd.nextInt(4);
            int secondPt = rnd.nextInt(4);
            String tag = Tags[0][firstPt] + " " + Tags[1][secondPt];
            enemyWeapons.add(new Weaponry(tag, rnd.nextInt(3-1)+1, rnd.nextInt(55 - 1) + 1));
        }
        for (int count = 0; count < enemiesMass; count++) {
            //Для имён
            int firstPt = rnd.nextInt(10);
            int secondPt = rnd.nextInt(10);
            int nicknameId = rnd.nextInt(10);
            //Создание имени
            String name = Names[0][firstPt] + Names[1][secondPt] + Names[2][nicknameId];
            //Добавление нового в список
            enemies.add(new Enemy(name, 100, rnd.nextInt(100 - 1)+1, 1, 1));
            enemies.get(count).Equipment.add(enemyWeapons.get(count));
            enemies.get(count).setDamage(GameLogic.calcDPS(enemies.get(count).Equipment, enemies.get(count).getDamage()));
        }
        for (int count = 0; count<enemiesMass; count++){
            enemies.get(count).xPos= rnd.nextInt(xLenght-1)+1;
            enemies.get(count).yPos= rnd.nextInt(yLenght-1)+1;
        }
    }
    static public void generateShopItems(ArrayList<Weaponry> shopWeapons, ArrayList<Equipment> shopEquip){
        Random rnd = new Random();
        for (int count=0; count<10;count++){
            shopEquip.add(new Equipment("Банка с зельем здоровья", rnd.nextInt(10-1)+1));
        }
        for (int count = 0; count < 10; count++) {
            int firstPt = rnd.nextInt(4);
            int secondPt = rnd.nextInt(4);
            String tag = Tags[0][firstPt] + " " + Tags[1][secondPt];
            //Добавление нового в список
            shopWeapons.add(new Weaponry(tag, rnd.nextInt(400-20)+20, rnd.nextInt(20-1)+1, rnd.nextInt(600-50)+50) );
        }
    }
    static public void startKit (Hero player, Weaponry mySword, int xLenght, int yLenght){
        Random rnd = new Random();
        player.Equipment.add(mySword);
        player.xPos = rnd.nextInt(xLenght - 1) + 1;
        player.yPos = rnd.nextInt(yLenght - 1) + 1;
    }
    public static void gameMenuDisplay(){
        System.out.println("Что вы хотите сделать?\n"+
                "[1] - войти в комнату с врагами.\n"+
                "[2] - нанять отряд и отправить его в экспедицию.\n" +
                "[3] - зайти в магазин.\n" +
                "[4] - зайти в банк.\n" +
                "[5] - сменить имя.\n" +
                "[6] - сохранить и выйти.");
    }
}
