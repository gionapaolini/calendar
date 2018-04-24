package CalendarPackage.EventsPackage;

import CalendarPackage.Day;
import CalendarPackage.RepeatRules.RepeatRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledTask extends Event{

    private int minDuration;
    private List<RepeatRule> repeatRules;
    private List<RepeatRule> exceptionRules;
    private List<LocalDateTime> specificDates;
    private List<LocalDateTime> specificException;
    private LocalDate startDate;
    private LocalTime time;
    private List<Day> days;


    public ScheduledTask(String name, String description, float importance, int minDuration) {
        super(name, description, importance);
        this.minDuration = minDuration;
        this.startDate = LocalDate.now();
        days = new ArrayList<>();
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void addDay(Day day){
        days.add(day);
    }

    public void removeDay(Day day){
        days.remove(day);
    }

    public void removeFromAllDays(){
        for(Day day: days){
            days.remove(this);
        }
    }

    public void addRepeatRule(RepeatRule rule){
        if(repeatRules == null){
            repeatRules = new ArrayList<>();
        }

        repeatRules.add(rule);
    }

    public void removeRepeatRule(RepeatRule rule){
        if(repeatRules == null){
            return;
        }

        repeatRules.remove(rule);
    }


    public void addExceptionRule(RepeatRule rule){
        if(exceptionRules == null){
            exceptionRules = new ArrayList<>();
        }

        exceptionRules.add(rule);
    }

    public void removeExceptionRule(RepeatRule rule){
        if(exceptionRules == null){
            return;
        }

        exceptionRules.remove(rule);
    }
    public void addSpecificDate(LocalDateTime date){
        if(specificDates == null){
            specificDates = new ArrayList<>();
        }

        specificDates.add(date);
    }

    public void removeSpecificDate(LocalDateTime date){
        if(specificDates == null){
            return;
        }

        specificDates.remove(date);
    }

    public void addSpecificException(LocalDateTime date){
        if(specificException == null){
            specificException = new ArrayList<>();
        }

        specificException.add(date);
    }

    public void removeSpecificException(LocalDateTime date){
        if(specificException == null){
            return;
        }

        specificException.remove(date);
    }


    public List<RepeatRule> getRepeatRules() {
        return repeatRules;
    }

    public List<RepeatRule> getExceptionRules() {
        return exceptionRules;
    }

    public List<LocalDateTime> getSpecificDates() {
        return specificDates;
    }

    public List<LocalDateTime> getSpecificException() {
        return specificException;
    }

    public List<Day> getDays() {
        return days;
    }
}
