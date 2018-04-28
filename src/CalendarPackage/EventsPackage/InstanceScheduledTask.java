package CalendarPackage.EventsPackage;

import java.time.LocalDateTime;

public class InstanceScheduledTask {

    private ScheduledTask originalTask;
    private boolean isDone;
    private LocalDateTime instanceDate;

    public InstanceScheduledTask(ScheduledTask originalTask, LocalDateTime instanceDate) {
        this.originalTask = originalTask;
        this.instanceDate = instanceDate;
    }

    public ScheduledTask getOriginalTask() {
        return originalTask;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDateTime getInstanceDate() {
        return instanceDate;
    }

    public void printInstance(){
        System.out.println("************************************************************************");
        System.out.println("** Name: "+originalTask.getName());
        System.out.println("** Description: "+originalTask.getDescription());
        System.out.println("** Done: "+isDone);
        System.out.println("** DateTime: "+instanceDate.getDayOfMonth()+" "+instanceDate.getMonth()+" "+instanceDate.getYear()+", "+instanceDate.getDayOfWeek());
        System.out.println("************************************************************************\n");


    }
}
