package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public int getnDays() {
        return nDays;
    }

    public void setnDays(int nDays) {
        this.nDays = nDays;
    }

    @Override
    public List<LocalDateTime> getValidDates(LocalDate startDate, LocalTime time, LocalDate endDate) {

        List<LocalDateTime> dates = new ArrayList<>();


        dates.add(LocalDateTime.of(startDate, time));


        LocalDate temp = startDate.plusDays(nDays);
        while (!temp.isAfter(endDate)){
            dates.add(LocalDateTime.of(temp, time));
            temp = temp.plusDays(nDays);
        }

        return dates;



    }
}
