package program.objects;

public class Conference {

    private final int[] blockDurations;
    private final int startTime;
    private final int endTime;
    private final Event breakEvent;
    private final Event endEvent;

    public Conference(int[] blockDurations, int startTime, int endTime, Event breakEvent, Event endEvent) {
        this.blockDurations = blockDurations;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakEvent = breakEvent;
        this.endEvent = endEvent;
    }

    public Conference(){
        this(
                new int[]{180, 240}, 900, 1700,
                new Event("lunch", 1200,60),
                new Event("networking event", 1700,0)
        );
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

    public Event getBreakEvent() {
        return breakEvent;
    }

    public Event getEndEvent() {
        return endEvent;
    }
}
