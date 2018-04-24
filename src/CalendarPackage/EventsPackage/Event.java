package CalendarPackage.EventsPackage;

import CalendarPackage.Label;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private String name;
    private String description;

    private float importance;
    private List<Label> labels;

    private LocalDateTime timestamp_createdOn;
    private LocalDateTime timestamp_deletedOn;

    public Event(String name, String description, float importance) {
        this.name = name;
        this.description = description;
        this.importance = importance;
        labels = new ArrayList<>();
        timestamp_createdOn = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getImportance() {
        return importance;
    }

    public void setImportance(float importance) {
        this.importance = importance;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void addLabel(Label label){
        labels.add(label);
    }

    public void removeLabel(Label label){
        labels.remove(label);
    }

    public void delete(){
        timestamp_deletedOn = LocalDateTime.now();
    }
    public void unDelete(){
        timestamp_deletedOn = null;
    }

    public boolean isDeleted(){
        return timestamp_deletedOn!=null;
    }
}
