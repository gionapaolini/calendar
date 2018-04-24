package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


/*
 *
 * Repeat a task every nth day
 *
 * Examples:
 *  RepeatEveryDays(3)
 *
 *  The task will be repeated every 3 days
 *
 * */

public class RepeatEveryDays implements RepeatRule {

    int nDays;

    public RepeatEveryDays(int nDays) {
        this.nDays = nDays;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        long gap = ChronoUnit.DAYS.between(startDate, day.getDateOfDay());
        if(gap%nDays==0)
            return true;

        return false;
    }

    @Override
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();


        dates.add(startDate);

        LocalDate temp = startDate.plusDays(nDays);
        while (!temp.isAfter(endDate)){
            dates.add(temp);
            temp = temp.plusDays(nDays);
        }

        return dates;



    }
}
