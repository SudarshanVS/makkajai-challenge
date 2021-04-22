public class Event implements Comparable<Event> {
    private final String name;
    private final int duration;
    private int startTime;


    public Event(String name, int startTime, int duration) {
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
    }


    public Event(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }


    @Override
    public int compareTo(Event o) {
        return Integer.compare(o.duration, this.duration);

    }
}
