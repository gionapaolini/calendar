package CalendarPackage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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