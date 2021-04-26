package program.logic;

import program.objects.Conference;
import program.objects.Event;
import program.objects.Track;

import java.util.ArrayList;

public abstract class ScheduleOrganiser {
    protected final Conference conference;

    public ScheduleOrganiser(Conference conference) {
        this.conference = conference;
    }

    protected abstract int algorithm(int block, int[][] dp_Map, ArrayList<Event> events);
    protected abstract void trace(int i, int[] used, int block, int[][] dp_Map, ArrayList<Event>events);
    protected abstract int[] addToTrack(int startTime, int block, int[] used,int usedCount, ArrayList<Event>events, Track track);
    //Time based arithmetic
    protected int addTime(int stime, int time) {
        int temp = stime % 100;
        stime -= temp;

        temp += time;
        stime += (temp / 60) * 100;
        stime += temp % 60;

        return stime;
    }
}
