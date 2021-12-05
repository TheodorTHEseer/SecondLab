package FileMgmt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static FileMgmt.Color.*;
import static FileMgmt.MgmtCfg.home;

public class MgmtGeneral implements Runnable{
    public static Map <Integer, String> logs = new HashMap<>();
    private static void saveLogs(){
        try{
            Date date = new Date();
            logs.put(0, date.toString());
            FileWriter fileWriter = new FileWriter(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder"+File.separator+ "logs.txt" , true);
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
                Thread.sleep(120000);
                System.out.println(blackA+"save done"+cResetA);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
