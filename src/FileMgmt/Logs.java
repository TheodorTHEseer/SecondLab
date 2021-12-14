package FileMgmt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static FileMgmt.Color.*;
import static FileMgmt.MgmtCfg.home;

public class Logs implements Runnable{
    public static ArrayList <String> logs = new ArrayList<>(100);
    public static void add (String str){
        logs.add(logs.size(), str);
    }
    private static void saveLogs(){
        try{
            Date date = new Date();
            logs.add(0, date.toString());
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
                Thread.sleep(5000);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
