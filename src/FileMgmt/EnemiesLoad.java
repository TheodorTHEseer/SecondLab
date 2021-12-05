package FileMgmt;

import cretures.pac.Enemy;

import java.io.File;
import java.io.FileReader;

import static FileMgmt.MgmtCfg.home;

public class EnemiesLoad {
    public static Enemy loadEnemy(int count) {
        Enemy _enemy = null;
        try {
            FileReader fileReader = new FileReader(home + File.separator + "Desktop" + File.separator +
                    "testGameFolder" + File.separator + "enemy" + count + ".txt");
            String cfg = String.valueOf(fileReader.read());
            String[] rdcfg = cfg.split(",");
            int hp = Integer.parseInt(rdcfg[1]);
            int damage = Integer.parseInt(rdcfg[2]);
            int dexSkill = Integer.parseInt(rdcfg[3]);
            int lvl = Integer.parseInt(rdcfg[4]);
            int xPos = Integer.parseInt(rdcfg[5]);
            int yPos = Integer.parseInt(rdcfg[6]);

            Enemy enemy = new Enemy(rdcfg[0], hp, damage, dexSkill, lvl, xPos, yPos);
            enemy.addWeapon();
            _enemy = enemy;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return _enemy;
    }
}
