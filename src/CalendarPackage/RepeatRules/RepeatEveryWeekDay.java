package CalendarPackage;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RepeatEveryWeekDay implements RepeatRule{

    private DayOfWeek weekDay;

    public RepeatEveryWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        if(day.getDateOfDay().getDayOfWeek()==weekDay)
            return true;

        return false;
    }
}
