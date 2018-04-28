package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * Repeat a task every nth week
 *
 * Examples:
 *  RepeatEveryWeeks(3)
 *
 *  The task will be repeated every 3 weeks
 *
 *
 * */

public class RepeatEveryWeeks implements RepeatRule {

    int nWeeks;

    public RepeatEveryWeeks(int nWeeks) {
        this.nWeeks = nWeeks;
    }



    @Override
    public List<LocalDateTime> getValidDates(LocalDate startDate, LocalTime time, LocalDate endDate) {

        List<LocalDateTime> dates = new ArrayList<>();


        dates.add(LocalDateTime.of(startDate,time));

        LocalDate temp = startDate.plusWeeks(nWeeks);
        while (!temp.isAfter(endDate)){
            dates.add(LocalDateTime.of(temp,time));
            temp = temp.plusWeeks(nWeeks);
        }

        return dates;



    }

    public int getnWeeks() {
        return nWeeks;
    }

    public void setnWeeks(int nWeeks) {
        this.nWeeks = nWeeks;
    }
}