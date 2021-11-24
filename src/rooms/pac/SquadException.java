package rooms.pac;

public class SquadException implements Thread.UncaughtExceptionHandler{
    public SquadException(Thread allMobsController, Exception test) {
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
    }
}
