package FileMgmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static FileMgmt.MgmtGeneral.logs;

public class Start {
    public static String home = System.getProperty("user.home");
    public static boolean uploadCheck(){
        File startCheck = new File(home + File.separator + "Desktop" + File.separator + "testGameFolder" + File.separator + "config0.txt");
        boolean oldPlayerStatus=startCheck.isFile();
        return oldPlayerStatus;
    }
    public static void upload(){
        try {
            File dirCreateStream = new File(home + File.separator + "Desktop" + File.separator + "testGameFolder");
            dirCreateStream.mkdir();
            File subDirCreateSream = new File(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+"logs");
            subDirCreateSream.mkdir();
            File confSubDirCreateSream = new File(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+"lvlConf");
            confSubDirCreateSream.mkdir();
        }
        catch (Exception e){
            logs.put(logs.size(),"Создание папки игры|Fail" + e.getMessage() );
        }
        logs.put(logs.size(),"Создание папки игры|Done" );
    }

    public static int loadCurrentLvl() throws FileNotFoundException {
        int currentLvl = 0;
        try {
            FileReader fileReader = new FileReader(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder" + File.separator + "CurrentLvl.txt");
            Scanner scanner = new Scanner(fileReader);
            currentLvl = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            logs.put(logs.size(), "Ошибка загурзки уровня!");
        }
        return currentLvl;
    }
}
