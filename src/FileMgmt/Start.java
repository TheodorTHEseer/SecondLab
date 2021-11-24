package FileMgmt;

import java.io.File;

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
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Только что была создана папка для хранения ваших конфигов");
    }
}
