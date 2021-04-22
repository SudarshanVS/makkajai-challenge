import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Scheduler {

    private final Conference conference;

    public Scheduler(Conference conference) {
        this.conference = conference;
    }

    public ArrayList<LinkedList<Event>> organise(ArrayList<Event> events) {

        Collections.sort(events); //descending order for greedy algorithm

        ArrayList<LinkedList<Event>> tracks = new ArrayList<>();

        int[] blocks = conference.getBlockDurations();
        int usedCount = 0;

        do {
            LinkedList<Event> track = new LinkedList<>();
            int startTime = conference.getStartTime();
            for (int block : blocks) {
                int[][] dp_Map = new int[events.size() + 1][block + 1];
                int[] used = new int[events.size()];  //track if an event was slotted
                usedCount = 0;

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

                  //adding slotted events to a track and calculating start times
                for (i = events.size() - 1; i >= 0; i--)
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
            }
            //end track and add to list of tracks
            track.add(conference.getEndEvent());
            tracks.add(track);
        } while (usedCount < events.size()); //continue until all have a slot

        return tracks;
    }

    //Time based arithmetic
    private int addTime(int stime, int time) {
        int temp = stime % 100;
        stime -= temp;

        temp += time;
        stime += (temp / 60) * 100;
        stime += temp % 60;

        return stime;
    }

}
