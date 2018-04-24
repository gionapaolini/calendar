package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * Repeat a task every nth occurrence of a weekday in a month
 *
 * Examples:
 *
 *  RepeatEveryNWeekDayOfMonth(Tuesday, 2)
 *
 *  The task will be repeated every 2nd Tuesday of the month

 *  RepeatEveryNWeekDayOfMonth(Tuesday, -2)
 *
 *  The task will be repeated every last 2nd Tuesday of the month
 *
 * */



public class RepeatEveryNWeekDayOfMonth implements RepeatRule {

    DayOfWeek weekDay;
    int indexOfWeek;

    public RepeatEveryNWeekDayOfMonth(DayOfWeek weekDay, int indexOfWeek) {
        this.weekDay = weekDay;
        this.indexOfWeek = indexOfWeek;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        if(day.getDateOfDay().isEqual(day.getDateOfDay().with(TemporalAdjusters.dayOfWeekInMonth(indexOfWeek,weekDay)))){
            return true;
        }

        return false;
    }

    @Override
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();

        if(startDate.with(TemporalAdjusters.dayOfWeekInMonth(indexOfWeek,weekDay)).isEqual(startDate)){
            dates.add(startDate);
        }
        LocalDate temp = startDate.with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.dayOfWeekInMonth(indexOfWeek,weekDay));
        while (!temp.isAfter(endDate)){
            dates.add(temp);
            temp = temp.with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.dayOfWeekInMonth(indexOfWeek,weekDay));
        }

        return dates;



    }


}
