package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


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
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();


        dates.add(startDate);

        LocalDate temp = startDate.plusMonths(nMonths);
        while (!temp.isAfter(endDate)){
            if(temp.getDayOfMonth()!=startDate.getDayOfMonth())
                continue;
            dates.add(temp);
            temp = temp.plusMonths(nMonths);
        }

        return dates;



    }

    public int getnMonths() {
        return nMonths;
    }

    public void setnMonths(int nMonths) {
        this.nMonths = nMonths;
    }
}