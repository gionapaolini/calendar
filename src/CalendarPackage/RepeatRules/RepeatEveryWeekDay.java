package CalendarPackage.RepeatRules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


/*
 *
 * Repeat a task every specific day of the week
 *
 * Examples:
 *  RepeatEveryWeekDay(Tuesday)
 *
 *  The task will be repeated every Tuesday
 *
 *
 * */


public class RepeatEveryWeekDay implements RepeatRule{

    private DayOfWeek weekDay;

    public RepeatEveryWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }



    @Override
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();

        if(startDate.getDayOfWeek() == weekDay ){
            dates.add(startDate);
        }
        LocalDate temp = startDate.with(TemporalAdjusters.next(weekDay));
        while (!temp.isAfter(endDate)){
            dates.add(temp);
            temp = temp.with(TemporalAdjusters.next(weekDay));
        }

        return dates;



    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }
}
