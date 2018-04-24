import CalendarPackage.Label;

import java.util.List;

public class Task {
    private String name;
    private String description;
    private int importanceLvl;
    private boolean status; //done or not
    private List<Label> userLabels;


    public Task(String name, String description, int importanceLvl) {
        this.name = name;
        this.description = description;
        this.importanceLvl = importanceLvl;
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

    public int getImportanceLvl() {
        return importanceLvl;
    }

    public void setImportanceLvl(int importanceLvl) {
        this.importanceLvl = importanceLvl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Label> getUserLabels() {
        return userLabels;
    }


}


/*
* Scheduled
*   -Single
*   -Repeated
*
* NoDeadline
*
*
*
* */
