package CalendarPackage.EventsPackage;

import java.time.LocalDateTime;

public class SimpleTask extends Event{

    private boolean done;

    private LocalDateTime timestamp_doneAt;

    public SimpleTask(String name, String description, float importance) {
        super(name, description, importance);
        done = false;
    }


    public boolean isDone() {
        return done;
    }

    public void doTask() {
        this.done = true;
        timestamp_doneAt = LocalDateTime.now();
    }

    public void unDoTask() {
        this.done = false;
        timestamp_doneAt = null;
    }
}
