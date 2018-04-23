package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;

/*
*
* Repeat a task every nth day of the month
*
* Examples:
*  RepeatEveryDayOfMonth(10)
*
*  Every 10th day of the month the task will be repeated
*                      -> 10th of Jan, 10th of Feb, 10th of March ...
*
*
* */



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
