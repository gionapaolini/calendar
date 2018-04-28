package CalendarPackage;


import CalendarPackage.EventsPackage.*;
import CalendarPackage.RepeatRules.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class TestRules {

    public static void main(String[] args){


        testCalendar();






    }



    public static void testHabits(){
        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Habit");
        Habit h1 = new Habit(name,desc,importance,HabitType.POSITIVE);

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

        RepeatedTask r1 = new RepeatedTask(name,desc,importance,RepeatedType.YEARLY,LocalDate.now());
        System.out.println(r1.isDone());
        r1.doTask();
        System.out.println(r1.isDone());
        System.out.println(r1.checkIfDone(LocalDateTime.now().plusMonths(8)));
        r1.unDoTask();
        System.out.println(r1.checkIfDone(LocalDateTime.now().plusMonths(8)));





    }
    public static void testScheduledTask(){
        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Repeated Task");

        ScheduledTask task = new ScheduledTask(name,desc,importance,50, LocalDateTime.now());
        task.addRepeatRule(new RepeatEveryDayOfMonth(11));
        task.addRepeatRule(new RepeatEveryWeekDay(DayOfWeek.MONDAY));
        task.addRepeatRule(new RepeatEveryDayOfMonth(12));

        // task.addExceptionRule(new RepeatEveryDays(10));
       // task.addExceptionRule(new RepeatEveryWeekDay(DayOfWeek.MONDAY));
        for (LocalDateTime time: task.getAllDates(LocalDate.now().plusMonths(5))){
            System.out.println(time.getDayOfMonth()+" "+time.getMonth()+", "+time.getDayOfWeek());

        }



    }

    public static void testCalendar(){
        Calendar cal = new Calendar();
        String name = "Drink Water";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Scheduled Task");
        ScheduledTask task = new ScheduledTask(name,desc,importance,60,LocalDateTime.now().plusDays(4));
        task.addRepeatRule(RuleMaker.getRuleDays(2));
        task.addExceptionRule(RuleMaker.getRuleWeekDay(DayOfWeek.MONDAY));
        task.addExceptionRule(RuleMaker.getRuleWeekDay(DayOfWeek.TUESDAY));
        task.addExceptionRule(RuleMaker.getRuleWeekDay(DayOfWeek.WEDNESDAY));

        cal.addTask(task);
        cal.printTaskFromTo(LocalDate.now(),LocalDate.now().plusYears(7));
    }


}
