package FileMgmt;

import java.io.*;
import java.util.Scanner;

public class MgmtCfg {
    static Scanner in = new Scanner(System.in);
    static String cfgWrite="";
    public static void createCfg(){
        try {
            File fileCreateStream = new File(".", "config0.txt");
            boolean created = fileCreateStream.createNewFile();
            System.out.println("Был создан файл конфигурации. Его индекс = 0.");

        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        try {
            FileWriter fileWriter = new FileWriter("config0.txt" , false);
            System.out.println("Как бы вы хотели назвать своего героя?");
            String cfgWrite=System.console().readLine();
            fileWriter.write(cfgWrite);
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void createCfg(int index){
        try {
            File fileCreateStream = new File(".", "config"+index+".txt");
            boolean created = fileCreateStream.createNewFile();
            System.out.println("Был создан файл конфигурации. Его индекс = " + index+".");
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        try {
            FileWriter fileWriter = new FileWriter("config"+index+".txt" , false);
            System.out.println("Как бы вы хотели назвать своего героя?");
            InputStream a = System.in;
            InputStreamReader b = new InputStreamReader(a);
            BufferedReader reader = new BufferedReader(b);
            cfgWrite= reader.readLine();
            fileWriter.write(cfgWrite);
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void useCfg(String playerName){
        try{
            FileReader fileReader = new FileReader("config0.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            playerName= bufferedReader.readLine();
            if (playerName==null)
                playerName="My Hero";
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void useCfg(String playerName, int index){
        try{
            FileReader fileReader = new FileReader("config"+index+".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            playerName= bufferedReader.readLine();
            System.out.println(playerName);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
