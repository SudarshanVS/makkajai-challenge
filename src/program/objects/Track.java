package program.objects;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Track {
    private Event lastEvent;
    LinkedList<Event> events;

    public Track(){
        this.events = new LinkedList<>();
    }
    public void add(Event event){
        events.add(event);
        lastEvent = event;
    }

    public Event getLast(){
    return lastEvent;
    }

    public List<Event> getEvents(){
        return Collections.unmodifiableList(events);
    }


}
