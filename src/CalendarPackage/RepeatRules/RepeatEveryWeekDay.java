package CalendarPackage.RepeatRules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public List<LocalDateTime> getValidDates(LocalDate startDate, LocalTime time, LocalDate endDate) {

        List<LocalDateTime> dates = new ArrayList<>();

        if(startDate.getDayOfWeek() == weekDay ){
            dates.add(LocalDateTime.of(startDate,time));
        }
        LocalDate temp = startDate.with(TemporalAdjusters.next(weekDay));
        while (!temp.isAfter(endDate)){
            dates.add(LocalDateTime.of(temp,time));
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
