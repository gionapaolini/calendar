package CalendarPackage.EventsPackage;

import CalendarPackage.RepeatRules.InstanceRepeatRule;
import CalendarPackage.RepeatRules.RepeatRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduledTask extends Event{

    private int minDuration;
    private List<InstanceRepeatRule> repeatRules;
    private List<InstanceRepeatRule> exceptionRules;
    private List<LocalDateTime> specificDates;
    private List<LocalDateTime> specificException;


    public ScheduledTask(String name, String description, float importance, int minDuration, LocalDateTime specificDate) {
        super(name, description, importance);
        this.minDuration = minDuration;
        addSpecificDate(specificDate);
    }

    public ScheduledTask(String name, String description, float importance, int minDuration, InstanceRepeatRule firstRule) {
        super(name, description, importance);
        this.minDuration = minDuration;
        addRepeatRule(firstRule);

    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }



    public void addRepeatRule(InstanceRepeatRule rule){
        if(repeatRules == null){
            repeatRules = new ArrayList<>();
        }

        repeatRules.add(rule);
    }

    public void removeRepeatRule(InstanceRepeatRule rule){
        if(repeatRules == null){
            return;
        }

        repeatRules.remove(rule);
    }


    public void addExceptionRule(InstanceRepeatRule rule){
        if(exceptionRules == null){
            exceptionRules = new ArrayList<>();
        }

        exceptionRules.add(rule);
    }

    public void removeExceptionRule(InstanceRepeatRule rule){
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


    public List<InstanceRepeatRule> getRepeatRules() {
        return repeatRules;
    }

    public List<InstanceRepeatRule> getExceptionRules() {
        return exceptionRules;
    }

    public List<LocalDateTime> getSpecificDates() {
        return specificDates;
    }

    public List<LocalDateTime> getSpecificException() {
        return specificException;
    }

    public List<LocalDateTime> getAllDates(LocalDate endOfCalendar){

        List<LocalDateTime> fromRules = null;
        List<LocalDateTime> fromException = null;

        //If there are repeated rules create a new list to put all dates from the rules together
        if(repeatRules!=null){
            fromRules = new ArrayList<>();
            for(InstanceRepeatRule rule: repeatRules){
                fromRules.addAll(rule.getValidDates());
            }
        }
        //If there are exception rules create a new list to put all dates from the exception together
        if(exceptionRules!=null){
            fromException = new ArrayList<>();
            for(InstanceRepeatRule rule: exceptionRules){
                fromException.addAll(rule.getValidDates());
            }
        }

        List<LocalDateTime> goodDates = new ArrayList<>();
        //If there are specific dates add all those date to the new list of valid date
        if(specificDates!=null){
            goodDates.addAll(specificDates);
        }

        //Compare the dates from the rules with the exception, discarding the dates that are present in the exception list
        if((fromRules != null) && (fromException !=null)) {
            checkException:
            for (LocalDateTime date1 : fromRules) {
                for (LocalDateTime date2 : fromException) {
                    if (date1.isEqual(date2)) {
                        continue checkException;
                    }
                }

                goodDates.add(date1);
            }

        }else if(fromRules!=null){
            //if there are no exceptions, then don't discard any date from the rules
            goodDates.addAll(fromRules);
        }

        List<LocalDateTime> finalDates = new ArrayList<>();

        //last check against the specific exceptions
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

        //discard duplicates
        finalDates = finalDates.stream().distinct().collect(Collectors.toList());
        //sort the dates
        finalDates.sort((LocalDateTime p1, LocalDateTime p2) -> p1.isBefore(p2)?-1:1);


        return finalDates;


    }
}
