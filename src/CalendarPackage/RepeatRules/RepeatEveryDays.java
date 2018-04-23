package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



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
}
