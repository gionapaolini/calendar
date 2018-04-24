package CalendarPackage;

import CalendarPackage.EventsPackage.Event;

import java.awt.Color;
import java.util.List;

public class Label {
    private Color color;
    private String name;
    private List<Event> events;

    public Label(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEventList() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
    public void removeEvent(Event event) {
        this.events.remove(event);
    }
}
