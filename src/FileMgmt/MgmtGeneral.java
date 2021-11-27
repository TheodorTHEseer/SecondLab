package FileMgmt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static FileMgmt.Color.cResetA;
import static FileMgmt.Color.redA;
import static FileMgmt.MgmtCfg.home;

public class MgmtGeneral {
    public static Date date = new Date();
    public static Map <Integer, String> logs = new HashMap<>();

    public static void saveLogs(Map<Integer, String> logs){
        try{
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "logs.txt" , false);
            String logString= null;
            for (int count=0; count<logs.size();count++){
                logString=logs.get(count).toString();
                fileWriter.write(logString);
            }
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println(redA + "###Логи не сохранены###" + cResetA);
        }
    }
}
