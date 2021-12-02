package FileMgmt;

import cretures.pac.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static FileMgmt.MgmtGeneral.logs;

public class MgmtCfg {
    static Scanner in = new Scanner(System.in);
    public static String home = System.getProperty("user.home");
    public static int findex;
    public static String patch;
    public static void createCfg(){
        try {
            File fileCreateStream = new File(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "config0.txt");
            fileCreateStream.createNewFile();
            System.out.println("Был создан файл конфигурации. Его индекс = 0.");
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        try {
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "config0.txt" , false);
            System.out.println("Как бы вы хотели назвать своего героя?");
            String cfgWrite=in.nextLine();
            fileWriter.write(cfgWrite);
            fileWriter.close();
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void getIndex(int index){
        findex=index;
        patch = "config"+findex+".txt";
    }
    public static void renameCfg(){
        try {
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "config0.txt" , false);
            System.out.println("Как бы вы хотели назвать своего героя?");
            String cfgWrite= in.nextLine();
            fileWriter.write(cfgWrite);
            fileWriter.close();
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static String useCfg(String playerName){
        try{
            FileReader fileReader = new FileReader(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "config0.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            playerName= bufferedReader.readLine();
            if (playerName==null)
                playerName="My Hero";
            fileReader.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return playerName;
    }

    public static void saveGame(Hero player, int bankWallet){
        try{
        FileWriter fileWriter = new FileWriter (home + File.separator + "Desktop" + File.separator +
                "testGameFolder"+File.separator+ "config1.txt" , false);
        fileWriter.write("Name: " + player.getName()  +
                "\n");
            fileWriter.write("Hp: " + player.getHp()  +
                    "\n");
            fileWriter.write("Damage: " + player.getDamage() +
                    "\n");
            fileWriter.write("Exp: " + player.getExp()  +
                    "\n");
            fileWriter.write("DS: " + player.getDexteritySkill()  +
                    "\n");
            fileWriter.write("Money: " + player.getMoney() +
                    "\n");
            fileWriter.write("BankMoney: " + bankWallet +
                    "\n");
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveGame (Hero player, int bankWallet, int currentLvl){
        saveGame(player, bankWallet);
        try {
            FileWriter fileWriter = new FileWriter (home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "lvl.txt" , false);
            fileWriter.write(currentLvl);
            fileWriter.close();
            logs.put(logs.size()+1, "Уровень сохранён успешно");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    static public void loadGame(Hero player, int bankWallet){
        String line = null;
        try {
            FileReader fileReader = new FileReader(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "config1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String words [] = {"Name: ", "Hp: ","Damage: ", "Exp: ", "DS: ", "Money: ", "BankMoney: "};
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<String> flines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            for (int count=0; count<lines.size();count++) {
                String fLine = lines.get(count).replace(words[count], "");
                flines.add(fLine);
            }
            player.getSave(flines);
            try {
                int money = Integer.parseInt(flines.get(0));
                bankWallet = money;
            }
            catch (NumberFormatException exception) //Тут может не быть цифр. Но банковский счёт - не часть персанажа, поэтому нестрашно
            {
                System.out.println("Файл был повреждён.");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
