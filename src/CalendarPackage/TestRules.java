package CalendarPackage;


import CalendarPackage.EventsPackage.*;
import CalendarPackage.RepeatRules.RepeatEveryMonths;
import CalendarPackage.RepeatRules.RepeatRule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestRules {

    public static void main(String[] args){


        testRepeatedTask();



    }



    public static void testHabits(){
        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Habit");
        Habit h1 = new Habit(name,desc,importance,HabitType.Positive);

        h1.occur();
        h1.occur();
        h1.occur();

        System.out.println(h1.getCount());

        h1.removeLastOccur();

        System.out.println(h1.getCount());
        System.out.println(h1.getCount());

        h1.printOccurences();
    }
    public static void testSimpleTask(){
        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new SimpleTask");

        SimpleTask t1 = new SimpleTask(name,desc,importance);
        System.out.println(t1.isDone());
        t1.doTask();
        System.out.println(t1.isDone());
        t1.unDoTask();
        System.out.println(t1.isDone());
        t1.doTask();
        System.out.println(t1.isDone());


    }
    public static void testRepeatedTask(){

        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Repeated Task");

        RepeatedTask r1 = new RepeatedTask(name,desc,importance,RepeatedType.Yearly,LocalDate.now());
        System.out.println(r1.isDone());
        r1.doTask();
        System.out.println(r1.isDone());
        System.out.println(r1.checkIfDone(LocalDateTime.now().plusMonths(8)));
        r1.unDoTask();
        System.out.println(r1.checkIfDone(LocalDateTime.now().plusMonths(8)));





    }
    public void testScheduledTask(){

    }




}
