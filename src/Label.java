import java.awt.Color;
import java.util.List;

public class Label {
    private Color color;
    private String name;
    private List<Task> taskList;

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

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }
}
