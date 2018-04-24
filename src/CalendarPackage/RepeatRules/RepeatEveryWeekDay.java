package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

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
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        if(day.getDateOfDay().getDayOfWeek()==weekDay)
            return true;

        return false;
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
            temp = temp = startDate.with(TemporalAdjusters.next(weekDay));
        }

        return dates;



    }
}
