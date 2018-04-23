package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

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
}
