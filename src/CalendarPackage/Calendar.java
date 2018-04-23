package CalendarPackage;

import java.time.LocalDate;
import java.util.List;

public class Calendar {

    private List<Day> notEmptyDays;
    private List<ScheduledTask> allScheduledTask;


    public void addTask(ScheduledTask task, LocalDate startDate){
        Day day = getDayFromDate(startDate) ;
        if(day==null){
            day = new Day(startDate);
            notEmptyDays.add(day);
        }

        day.addTask(task);
        task.addDay(day);
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



}
