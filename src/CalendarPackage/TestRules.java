package CalendarPackage;


import CalendarPackage.EventsPackage.*;
import CalendarPackage.RepeatRules.*;

import java.time.*;


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

        // task.addExceptionRule(new RepeatEveryDays(10));
       // task.addExceptionRule(new RepeatEveryWeekDay(DayOfWeek.MONDAY));
        for (LocalDateTime time: task.getAllDates(LocalDate.now().plusMonths(5))){
            System.out.println(time.getDayOfMonth()+" "+time.getMonth()+", "+time.getDayOfWeek());

        }



    }

    public static void testCalendar(){
        Calendar cal = new Calendar();
        String name = "Gym";
        String desc = "Title quite self explanatory";
        float importance = 5;
        System.out.println("Creating new Scheduled Task");
        LocalTime timeForGym = LocalTime.now();

        InstanceRepeatRule onlyMondayFirstMonth = new InstanceRepeatRule(RuleMaker.getRuleWeekDay(DayOfWeek.MONDAY),LocalDate.now(),timeForGym,LocalDate.now().plusMonths(2));
        InstanceRepeatRule onlyWednesdayFirstMonth = new InstanceRepeatRule(RuleMaker.getRuleWeekDay(DayOfWeek.WEDNESDAY),LocalDate.now(),timeForGym,LocalDate.now().plusMonths(2));
        InstanceRepeatRule onlyFridayFirstMonth = new InstanceRepeatRule(RuleMaker.getRuleWeekDay(DayOfWeek.FRIDAY),LocalDate.now(),timeForGym,LocalDate.now().plusMonths(2));


        ScheduledTask task = new ScheduledTask(name,desc,importance,60,onlyMondayFirstMonth);


        task.addRepeatRule(onlyWednesdayFirstMonth);
        task.addRepeatRule(onlyFridayFirstMonth);

        InstanceRepeatRule onlySaturdayFirstMonth = new InstanceRepeatRule(RuleMaker.getRuleWeekDay(DayOfWeek.SATURDAY),LocalDate.now().plusMonths(2),timeForGym,LocalDate.now().plusMonths(4));
        InstanceRepeatRule onlySundayFirstMonth = new InstanceRepeatRule(RuleMaker.getRuleWeekDay(DayOfWeek.SUNDAY),LocalDate.now().plusMonths(2),timeForGym,LocalDate.now().plusMonths(4));

        task.addRepeatRule(onlySaturdayFirstMonth);
        task.addRepeatRule(onlySundayFirstMonth);

        InstanceRepeatRule from18to8everyday = new InstanceRepeatRule(RuleMaker.getRuleDays(1),LocalDate.of(2018,Month.JUNE,18),timeForGym,LocalDate.of(2018,Month.JULY,8));

        task.addExceptionRule(from18to8everyday);
        cal.addTask(task);
      //  cal.printTaskFromTo(LocalDate.now(),LocalDate.now().plusYears(7));


        task.removeExceptionRule(from18to8everyday);

        cal.updateTask(task);
        cal.printTaskFromTo(LocalDate.now(),LocalDate.now().plusYears(7));

    }


}
