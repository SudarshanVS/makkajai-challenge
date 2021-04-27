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

    public abstract ArrayList<Track> organise(ArrayList<Event> events);

}
