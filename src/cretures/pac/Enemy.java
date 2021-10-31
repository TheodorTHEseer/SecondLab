package cretures.pac;

public class Enemy extends Creature{
    char holdspace;
    public Enemy (String name, int hp,  int damage, int dexteritySkill,
                  int lvl) {
        super(name, hp, dexteritySkill, damage);
        this.lvl = lvl;
        holdspace = name.charAt(0);
        this.shortName= "["+holdspace+"]";

    }
}
