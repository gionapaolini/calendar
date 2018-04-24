package CalendarPackage.EventsPackage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class RepeatedTask extends SimpleTask {

    private RepeatedType type;
    private LocalDate startDate;
    private List<LocalDateTime> occurred;
    public RepeatedTask(String name, String description, float importance, RepeatedType type, LocalDate startDate) {
        super(name, description, importance);
        this.type = type;
        this.startDate = startDate;
        occurred = new ArrayList<>();
    }

    @Override
    public boolean isDone(){
        return checkIfDone(LocalDateTime.now());
    }

    @Override
    public void doTask() {

       if(LocalDate.now().isBefore(startDate))
            return;
       if(!isDone()){
           occurred.add(LocalDateTime.now());
       }
    }

    @Override
    public void unDoTask() {
        if(isDone()){
            occurred.remove(occurred.size()-1);
        }
    }



    public boolean checkIfDone(LocalDateTime now){
        if(occurred.size()<=0)
            return false;

        switch (type){
            case Daily:
                if(occurred.get(occurred.size()-1).isAfter(now.withHour(0).withMinute(0)))
                    return true;
                break;
            case Weekly:
                if(occurred.get(occurred.size()-1).isAfter(now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0)))
                    return true;
                break;
            case Monthly:
                if(occurred.get(occurred.size()-1).isAfter(now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0)))
                    return true;
                break;
            case Yearly:
                if(occurred.get(occurred.size()-1).isAfter(now.with(TemporalAdjusters.firstDayOfYear()).withHour(0).withMinute(0)))
                    return true;
                break;
        }
        return false;
    }


}
