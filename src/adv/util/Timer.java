package adv.util;

public class Timer {
    private final long timeCap;
    private long startTime;

    public Timer(long timeCap) {
        this.timeCap = timeCap;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean isTimeRemaining() {
        return timeCap < 0 || System.currentTimeMillis() - startTime < timeCap;
    }
}
