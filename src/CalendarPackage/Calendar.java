package CalendarPackage;

import CalendarPackage.EventsPackage.ScheduledTask;

import java.time.LocalDate;

import java.util.List;

public class Calendar {

    private List<Day> notEmptyDays;
    private List<ScheduledTask> allScheduledTask;


    public void addTask(ScheduledTask task){
        allScheduledTask.add(task);
    }

    public void removeTask(ScheduledTask task){
        task.removeFromAllDays();
        allScheduledTask.remove(task);
    }

    public void removeDay(Day day){

    }

    private Day getDayFromDate(LocalDate startDate){
        for(Day day: notEmptyDays){
            if(day.getDateOfDay().isEqual(startDate)){
                return day;
            }
        }
        return null;
    }

    public void printAllCalendar(){

    }

    public void updateDays(ScheduledTask task, LocalDate endOfCalendar){




    }

}
