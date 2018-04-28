package CalendarPackage.RepeatRules;

import java.time.LocalDate;
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
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();


        dates.add(startDate);

        LocalDate temp = startDate.plusWeeks(nWeeks);
        while (!temp.isAfter(endDate)){
            dates.add(temp);
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