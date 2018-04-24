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


    public ScheduledTask(String name, String description, float importance, int minDuration, LocalTime time) {
        super(name, description, importance);
        this.minDuration = minDuration;
        this.startDate = LocalDate.now();
        days = new ArrayList<>();
        this.time=time;
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

    public List<LocalDateTime> getAllDates(LocalDate endOfCalendar){
        System.out.println("HERE");

        List<LocalDate> fromRules = null;
        List<LocalDate> fromException = null;

        if(repeatRules!=null){
            fromRules = new ArrayList<>();
            for(RepeatRule rule: repeatRules){
                fromRules.addAll(rule.getValidDates(startDate,endOfCalendar));
            }
        }
        if(exceptionRules!=null){
            fromException = new ArrayList<>();
            for(RepeatRule rule: exceptionRules){
                fromException.addAll(rule.getValidDates(startDate,endOfCalendar));
            }
        }






        List<LocalDateTime> goodDates = new ArrayList<>();
        if(specificDates!=null){
            goodDates.addAll(specificDates);
        }

        if((fromRules != null) && (fromException !=null)) {
            checkException:
            for (LocalDate date1 : fromRules) {

                for (LocalDate date2 : fromException) {
                    if (date1.isEqual(date2)) {
                        continue checkException;
                    }
                }

                goodDates.add(LocalDateTime.of(date1, time));
            }

        }else if(fromRules!=null){
            for (LocalDate date : fromRules) {
                goodDates.add(LocalDateTime.of(date, time));
            }
        }

        List<LocalDateTime> finalDates = new ArrayList<>();

        if(specificException!=null){
            checkException2:
            for(LocalDateTime date: goodDates) {

                for (LocalDateTime date2 : specificException) {
                    if (date.isEqual(date2)) {
                        continue checkException2;
                    }
                }

                finalDates.add(date);
            }

        }else {
            finalDates = goodDates;
        }


        return finalDates;


    }
}
