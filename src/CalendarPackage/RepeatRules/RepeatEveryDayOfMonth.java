package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/*
*
* Repeat a task every nth day of the month
*
* Examples:
*  RepeatEveryDayOfMonth(10)
*
*  The task will be repeated every 10th day of the month
*                      -> 10th of Jan, 10th of Feb, 10th of March ...
*
*  NOTICE:
*  The task will not be repeated in months in which the day is not present (for example, day 31 as start date will always skip February)
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

    @Override
    public List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = new ArrayList<>();

        if(startDate.getDayOfMonth()==dayOfMonth){
            dates.add(startDate);
        }

        LocalDate temp = startDate;
        boolean caught;
        int addMonth=1;
        do {
            caught=false;
            try{
                temp = temp.plusMonths(addMonth).withDayOfMonth(dayOfMonth);
            }catch(Exception x){
                caught = true;
                addMonth++;
            }
        }while (caught);



        while (!temp.isAfter(endDate)){
            dates.add(temp);
            addMonth=1;
            do {
                caught=false;
                try{
                    temp = temp.plusMonths(addMonth).withDayOfMonth(dayOfMonth);
                }catch(Exception x){
                    caught = true;
                    addMonth++;
                }
            }while (caught);
        }

        return dates;



    }


}
