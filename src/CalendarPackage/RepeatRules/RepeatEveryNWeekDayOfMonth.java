package CalendarPackage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class RepeatEveryNWeekDayOfMonth implements RepeatRule {

    DayOfWeek weekDay;
    int indexOfWeek;

    public RepeatEveryNWeekDayOfMonth(DayOfWeek weekDay, int indexOfWeek) {
        this.weekDay = weekDay;
        this.indexOfWeek = indexOfWeek;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        if(day.getDateOfDay().isEqual(day.getDateOfDay().with(TemporalAdjusters.dayOfWeekInMonth(indexOfWeek,weekDay)))){
            return true;
        }

        return false;
    }
}
