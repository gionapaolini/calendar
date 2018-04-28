package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class InstanceRepeatRule {
    private RepeatRule repeatRule;
    private LocalDate startDate;
    private LocalTime timeOfRepetition;
    private LocalDate endDate;

    public InstanceRepeatRule(RepeatRule repeatRule, LocalDate startDate, LocalTime timeOfRepetition, LocalDate endDate) {
        this.repeatRule = repeatRule;
        this.startDate = startDate;
        this.timeOfRepetition = timeOfRepetition;
        this.endDate = endDate;
    }


    public List<LocalDateTime> getValidDates(){
        return repeatRule.getValidDates(startDate,timeOfRepetition,endDate);
    }
}
