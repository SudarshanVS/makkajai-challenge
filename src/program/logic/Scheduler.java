package program.logic;

import program.objects.Conference;
import program.objects.Event;
import program.objects.Track;

import java.util.ArrayList;
import java.util.Collections;

public class Scheduler extends ScheduleOrganiser {

    public Scheduler(Conference conference) {
        super(conference);
    }

    public ArrayList<Track> organise(ArrayList<Event> events) {

        Collections.sort(events); //descending order for greedy algorithm

        ArrayList<Track> tracks = new ArrayList<>();

        int[] blocks = conference.getBlockDurations();
        int usedCount = 0;

        do {
            Track track = new Track();
            int startTime = conference.getStartTime();
            for (int block : blocks) {
                int[][] dp_Map = new int[events.size() + 1][block + 1];
                int[] used = new int[events.size()];  //track if an event was slotted
                trace(algorithm(block, dp_Map, events), used, block, dp_Map, events);
                int[] result = addToTrack(startTime, block, used, usedCount, events, track);
                usedCount = result[0];
                startTime = result[1];
            }
            //end track and add to list of tracks
            track.add(conference.getEndEvent());
            tracks.add(track);
        } while (usedCount < events.size()); //continue until all have a slot

        return tracks;
    }

    protected int algorithm(int block, int[][] dp_Map, ArrayList<Event> events) {
        //Modified DP Knapsack Algorithm
        int i = 1;
        for (; i < dp_Map.length; i++) {
            for (int d = 1; d <= block; d++) {
                dp_Map[i][d] = dp_Map[i - 1][d];
                if (events.get(i - 1).getDuration() <= d) {
                    int newD = dp_Map[i - 1][d - events.get(i - 1).getDuration()] + events.get(i - 1).getDuration();
                    if (dp_Map[i][d] < newD && newD <= d) {
                        dp_Map[i][d] = newD;
                    }
                }
            }
            if (dp_Map[i][block] == block) { //break if all slots are used
                break;
            }
        }
        //when there is no forced break the index must still be valid
        if (i >= dp_Map.length)
            i = dp_Map.length - 1;

        return i;
    }

    protected void trace(int i, int[] used, int block, int[][] dp_Map, ArrayList<Event> events) {
        //backtracking to find the events that were slotted in current block
        int j = block;
        for (; i >= 1; i--) {
            if (i - 2 >= 0 && dp_Map[i][j] == dp_Map[i - 1][j]) {
                used[i - 2] = 1;
            } else if ((j - events.get(i - 1).getDuration()) >= 0 && dp_Map[i][j] == dp_Map[i - 1][j - events.get(i - 1).getDuration()] + events.get(i - 1).getDuration()) {
                j = j - events.get(i - 1).getDuration();
                used[i - 1] = 1;
            }
        }
    }

    protected int[] addToTrack(int startTime, int block, int[] used, int usedCount, ArrayList<Event> events, Track track) {
        //adding slotted events to a track and calculating start times
        for (int i = events.size() - 1; i >= 0; i--)
            if (used[i] == 1) {
                events.get(i).setStartTime(startTime);
                startTime = addTime(startTime, events.get(i).getDuration());
                track.add(events.get(i));
                events.remove(i);
                usedCount++;
            }
        if (block == 180) {
            track.add(conference.getBreakEvent());
            startTime = addTime(startTime, conference.getBreakEvent().getDuration());
        }
        return new int[]{usedCount, startTime};
    }

}
