package CalendarPackage;

import CalendarPackage.EventsPackage.InstanceScheduledTask;
import CalendarPackage.EventsPackage.ScheduledTask;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

    private final int calendarLengthYear = 5;
    private List<ScheduledTask> allScheduledTask;
    private List<InstanceScheduledTask> instances;
    private LocalDate endOfCalendar;
    public Calendar(){
        allScheduledTask = new ArrayList<>();
        instances = new ArrayList<>();
        endOfCalendar = LocalDate.now().plusYears(calendarLengthYear);
    }

    public void addTask(ScheduledTask task){

        createInstancesUntil(task,endOfCalendar);
        allScheduledTask.add(task);



    }

    private void createInstancesUntil(ScheduledTask task, LocalDate endDate) {

        List<LocalDateTime> dates = task.getAllDates(endDate);

        for(LocalDateTime date: dates){
            instances.add(new InstanceScheduledTask(task,date));
        }


    }

    public void updateTask(ScheduledTask task){
        deleteAllInstancesOfTask(task);
        createInstancesUntil(task,endOfCalendar);
    }

    private void deleteAllInstancesOfTask(ScheduledTask task){
        ArrayList<InstanceScheduledTask> copy = new ArrayList<>(instances);

        for(InstanceScheduledTask instance: copy){
            if(instance.getOriginalTask().equals(task)){
                instances.remove(instance);
            }
        }

    }

    public void removeTask(ScheduledTask task){
        allScheduledTask.remove(task);
    }

    private void sortInstancesByTime(){
        instances.sort((InstanceScheduledTask p1, InstanceScheduledTask p2) -> p1.getInstanceDate().isBefore(p1.getInstanceDate())?-1:1);

    }

    public void printTaskFromTo(LocalDate start, LocalDate end){
        for(InstanceScheduledTask instance: instances){
            if(instance.getInstanceDate().toLocalDate().isBefore(start))
                continue;
            if(instance.getInstanceDate().toLocalDate().isAfter(end))
                return;
            instance.printInstance();
        }
    }



}
