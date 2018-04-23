package CalendarPackage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {

    private final LocalDate dateOfDay;
    private List<ScheduledTask> taskOfDay;

    public Day(LocalDate dateOfDay) {
        this.dateOfDay = dateOfDay;
        taskOfDay = new ArrayList<>();
    }

    public LocalDate getDateOfDay() {
        return dateOfDay;
    }

    public void addTask(ScheduledTask task){
        taskOfDay.add(task);
    }

    public void removeTask(ScheduledTask task){
        taskOfDay.remove(task);
    }

    public void removeFromAllTask(){
        for(ScheduledTask task: taskOfDay){
            task.removeDay(this);
        }
    }


    public List<ScheduledTask> getTaskOfDay() {
        return taskOfDay;
    }

    public void setTaskOfDay(List<ScheduledTask> taskOfDay) {
        this.taskOfDay = taskOfDay;
    }
}
