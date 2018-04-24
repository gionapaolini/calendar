package CalendarPackage;

import CalendarPackage.EventsPackage.ScheduledTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day {

    private final LocalDate dateOfDay;
    private HashMap<ScheduledTask, Boolean> taskOfDay;
    private HashMap<ScheduledTask, Boolean> specificTask;
    public Day(LocalDate dateOfDay) {
        this.dateOfDay = dateOfDay;
        taskOfDay = new HashMap<>();
        specificTask = new HashMap<>();
    }

    public LocalDate getDateOfDay() {
        return dateOfDay;
    }

    public void addTask(ScheduledTask task){
        taskOfDay.put(task,false);
    }
    public void addSpecificTask(ScheduledTask task){
        specificTask.put(task,false);
    }

    public void removeTask(ScheduledTask task){
        taskOfDay.remove(task);
    }
    public void removeSpecificTask(ScheduledTask task){
        specificTask.remove(task);
    }

    public void removeFromAllTask(){
        for(ScheduledTask task: taskOfDay.keySet()){
            task.removeDay(this);
        }
        for(ScheduledTask task: specificTask.keySet()){
            task.removeDay(this);
        }
    }



    public List<ScheduledTask> getTaskOfDay() {
        List<ScheduledTask> tasks = new ArrayList<>(taskOfDay.keySet());
        List<ScheduledTask> tasksSpecific = new ArrayList<>(specificTask.keySet());
        tasks.addAll(tasksSpecific);

        return tasks;
    }

    public void doTask(ScheduledTask task){
        taskOfDay.put(task, true);
    }

    public void unDoTask(ScheduledTask task){
        taskOfDay.put(task, false);
    }


    public void doSpecificTask(ScheduledTask task){
        specificTask.put(task, true);
    }

    public void unDoSpecificTask(ScheduledTask task){
        specificTask.put(task, false);
    }

}
