import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduledTask extends Task {

    private LocalDate date;
    private LocalTime time;

    public ScheduledTask(String name, String description, int importanceLvl) {
        super(name, description, importanceLvl);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(int yyyy, int dd, int mm) {

        if(dd<1 || dd>31 || mm<1 || mm>12){
            System.out.println("Wrong input, date not changed");
            return;
        }
        this.date = LocalDate.of(yyyy, mm, dd);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(int hh, int mm) {
        if(mm>59 || hh>23){
            System.out.println("Wrong input, time not changed");
            return;
        }
        this.time = LocalTime.of(hh, mm);
    }
}
