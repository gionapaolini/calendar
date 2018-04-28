package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private int dayOfMonth;

    public RepeatEveryDayOfMonth(int day){

        this.dayOfMonth = day;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }


    @Override
    public List<LocalDateTime> getValidDates(LocalDate startDate, LocalTime time, LocalDate endDate) {

        List<LocalDateTime> dates = new ArrayList<>();

        if(startDate.getDayOfMonth()==dayOfMonth){
            dates.add(LocalDateTime.of(startDate,time));
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
            dates.add(LocalDateTime.of(temp,time));
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
