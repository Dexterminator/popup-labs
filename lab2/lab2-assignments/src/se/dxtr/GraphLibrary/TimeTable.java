package se.dxtr.graphlibrary;

/**
 * Created by dexter on 06/10/15.
 */
public class TimeTable {
    public final int t0;
    public final int interval;
    public final int traversalTime;

    public TimeTable (int t0, int interval, int traversalTime) {
        this.t0 = t0;
        this.interval = interval;
        this.traversalTime = traversalTime;
    }
}
