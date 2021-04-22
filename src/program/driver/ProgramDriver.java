package program.driver;

import program.logic.Scheduler;
import program.objects.Conference;
import program.objects.Event;
import program.validation.InputValidator;

import java.util.*;

public class ProgramDriver {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startWithUserInput(Conference conference) {


        int n = numberInput("Enter Number of talks", false);


        String name;
        int duration;
        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Talk name");
            name = scanner.nextLine();
            duration = numberInput("Enter Talk duration", true);
            events.add(new Event(name, duration));
        }

        Scheduler scheduler = new Scheduler(conference);

        printSchedule(scheduler.organise(events));

    }

    public static void startWithDataSet(Conference conference, ArrayList<Event> events) {

        System.out.println("EVENT DATA");
        System.out.println("**********");

        for (Event event : events)
            System.out.println("Name: " + event.getName() + " Duration: " + event.getDuration());

        Scheduler scheduler = new Scheduler(conference);

        printSchedule(scheduler.organise(new ArrayList<>(events)));

    }

    public static void startWithDataSet(Conference conference, Event[] events){
        startWithDataSet(conference, new ArrayList<Event>(Arrays.asList(events)));
    }


    private static void printSchedule(ArrayList<LinkedList<Event>> tracks) {

        int count = 1;
        for (LinkedList<Event> track : tracks) {
            System.out.println("\n\nTRACK " + count++);
            System.out.println("**********");
            for (Event event : track) {
                if (event == track.getLast())
                    System.out.println(String.format("%04d", event.getStartTime()) + " " + event.getName() + " ");
                else
                    System.out.println(String.format("%04d", event.getStartTime()) + " " +
                            event.getName()
                            + " " +
                            event.getDuration() + "mins");
            }
        }

    }

    private static int numberInput(String msg, boolean durationFlag) {
        int n;
        String s;
        do {
            System.out.println(msg);
            s = scanner.nextLine();
            if (durationFlag && s.toLowerCase().equals("lightning"))
                return 5;
            else
                n = InputValidator.isValidNumber(s); //return int if valid otherwise -1
        } while (n == -1);

        return n;
    }

}
