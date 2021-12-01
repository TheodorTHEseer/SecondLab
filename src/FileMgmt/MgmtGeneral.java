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

public class MgmtGeneral implements Runnable{
    public static Date date = new Date();
    public static Map <Integer, String> logs = new HashMap<>();


    private static void saveLogs(){
        try{
            Date date = new Date();
            logs.put(0, date.toString());
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "logs.txt" , false);
            String logString;
            for (int count=0; count<logs.size();count++){
                logString=logs.get(count);
                fileWriter.write(logString+"\n");
            }
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println(redA + "Логи не сохранены!" + cResetA + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            for (int count=0; count>=0; count++) {
                saveLogs();
                Thread.sleep(5000);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
