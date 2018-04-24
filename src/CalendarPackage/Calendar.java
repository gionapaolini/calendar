package CalendarPackage;

import CalendarPackage.EventsPackage.ScheduledTask;
import CalendarPackage.RepeatRules.RepeatRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

    private List<Day> notEmptyDays;
    private List<ScheduledTask> allScheduledTask;


    public void addTask(ScheduledTask task){
        allScheduledTask.add(task);
        updateDays(task,LocalDate.now().plusYears(1));

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
        List<RepeatRule> rules = task.getRepeatRules();
        List<RepeatRule> exceptions = task.getExceptionRules();
        List<LocalDateTime> specificDate = task.getSpecificDates();
        List<LocalDateTime> specificException = task.getSpecificException();

        List<LocalDate> fromRules = new ArrayList<>();
        List<LocalDate> fromException = new ArrayList<>();

        for(RepeatRule rule: rules){
            fromRules.addAll(rule.getValidDates(task.getStartDate(),endOfCalendar));
        }

        for(RepeatRule rule: exceptions){
            fromException.addAll(rule.getValidDates(task.getStartDate(),endOfCalendar));
        }


        List<LocalDate> goodDates = new ArrayList<>();

        checkException:
        for(LocalDate date1: fromRules){

            for(LocalDate date2: fromException){
                if(date1.isEqual(date2)){
                    continue checkException;
                }
            }

            goodDates.add(date1);

        }





    }

}
