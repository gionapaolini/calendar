package CalendarPackage;

import java.time.LocalDate;
import java.time.MonthDay;

public class RepeatEveryDayOfMonth implements RepeatRule {

    int dayOfMonth;

    public RepeatEveryDayOfMonth(int day) throws Exception{

        if(day<1 || day>31)
            throw new Exception("WTF - Days are wrong dude");

        this.dayOfMonth = day;
    }


    @Override
    public boolean isDayValid(LocalDate startDate, Day day) {

        if(day.getDateOfDay().isBefore(startDate))
            return false;

        if(day.getDateOfDay().getDayOfMonth() == dayOfMonth){
            return true;
        }

        return false;
    }
}
