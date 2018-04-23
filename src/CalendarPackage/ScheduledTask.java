package CalendarPackage;

import CalendarPackage.RepeatRules.RepeatRule;

import java.util.ArrayList;
import java.util.List;

public class ScheduledTask {

    private List<Day> days;
    private List<RepeatRule> rules;

    public ScheduledTask() {
        this.days = new ArrayList<>();
    }

    public void addDay(Day day){
        days.add(day);
    }

    public void removeDay(Day day) {
        days.remove(day);
    }

    public void removeFromAllDays(){
        for(Day day: days){
            day.removeTask(this);
        }
    }
}
