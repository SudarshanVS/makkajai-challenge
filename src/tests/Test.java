package tests;

import program.objects.Conference;
import program.objects.Event;
import program.driver.ProgramDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static void testDataSet(){
    Event[] events = new Event[19];

    events[0] = new Event("Writing Fast Tests Against Enterprise Rails", 60);
    events[1] = new Event("Overdoing it in Python", 45);
    events[2] = new Event("Lua for the Masses", 30);
    events[3] = new Event("Ruby Errors from Mismatched Gem Versions", 45);
    events[4] = new Event("Common Ruby Errors", 45);
    events[5] = new Event("Rails for Python Developers", 5);
    events[6] = new Event("Accounting-Driven Development", 45);
    events[7] = new Event("Woah", 30);
    events[8] = new Event("Sit Down and Write", 30);
    events[9] = new Event("Pair Programming vs Noise", 45);
    events[10] = new Event("Rails Magic", 60);
    events[11] = new Event("Ruby on Rails: Why We Should Move On", 60);
    events[12] = new Event("Clojure Ate Scala (on my project)", 45);
    events[13] = new Event("Programming in the Boondocks of Seattle", 30);
    events[14] = new Event("Ruby vs. Clojure for Back-End Development", 30);
    events[15] = new Event("Ruby on Rails Legacy App Maintenance", 60);
    events[16] = new Event("A World Without HackerNews", 30);
    events[17] = new Event("User Interface CSS in Rails Apps", 30);
    events[18] = new Event("Communicating Over Distance", 60);


         ProgramDriver.startWithDataSet(new Conference(), new ArrayList<>(Arrays.asList(events)));
    }

    public static void testUserInput(){
        ProgramDriver.startWithUserInput(new Conference());
    }


}
