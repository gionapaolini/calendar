package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/*
 *
 * Repeat a task every nth month
 *
 * Examples:
 *  RepeatEveryMonths(3)
 *
 *  The task will be repeated every 3 months (on the same day)
 *
 *
 *  NOTICE:
 *  The task will not be repeated in months in which the day is not present (for example, day 31 as start date will always skip February)
 *
 *
 * */



public class RepeatEveryMonths implements RepeatRule {

    int nMonths;

    public RepeatEveryMonths(int nMonths) {
        this.nMonths = nMonths;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        long gap = ChronoUnit.MONTHS.between(startDate, day.getDateOfDay());

        if(gap%nMonths==0){
            if(startDate.getDayOfMonth()==day.getDateOfDay().getDayOfMonth())
                return true;
        }

        return false;
    }
}