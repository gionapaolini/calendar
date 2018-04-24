package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

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
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        long gap = ChronoUnit.DAYS.between(startDate, day.getDateOfDay());

        if((gap/7)%nWeeks==0)
            return true;

        return false;
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

}