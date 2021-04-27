package program.objects;

import java.awt.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Conference {
    private final int startTime;
    private final int endTime;
    private final int[] blockDurations;
    private final ListIterator<Event> nextBreakEvent;

    public Conference(int startTime, int endTime, LinkedList<Event> breakEvents) {
        this.startTime = startTime;
        this.endTime = endTime;

        if (breakEvents == null) {
            breakEvents = new LinkedList<>();
            breakEvents.add(new Event("Lunch", 1200, 60));
            breakEvents.add(new Event("Networking Event", 1700, 0));
        }

        this.blockDurations = new int[breakEvents.size()];

        int i = 0;
        int stime = this.startTime;
        for (Event breakEvent : breakEvents) {
            blockDurations[i++] = Time.toMinutes(Time.subTime(breakEvent.getStartTime(), stime));
            stime = Time.addTime(breakEvent.getStartTime(), breakEvent.getDuration());
        }
        this.nextBreakEvent = breakEvents.listIterator();
    }

    public Conference() {
        this(900, 1700, null);
    }

    public int[] getBlockDurations() {
        return blockDurations;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Event getNextBreak() {
        if (this.nextBreakEvent.hasNext())
            return this.nextBreakEvent.next();
        else
            return null;
    }


    public void goToFirstBreak() {
        while (this.nextBreakEvent.hasPrevious())
            this.nextBreakEvent.previous();
    }
}
