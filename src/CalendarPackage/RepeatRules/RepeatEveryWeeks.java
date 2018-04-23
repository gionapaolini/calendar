package CalendarPackage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RepeatEveryWeeks implements RepeatRule {

    int nWeeks;

    public RepeatEveryWeeks(int nWeeks) {
        this.nWeeks = nWeeks;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        long gap = ChronoUnit.DAYS.between(startDate, day.getDateOfDay());

        if((gap/7)%nWeeks==0)
            return true;

        return false;
    }
}