import java.util.List;


public class RepeatedTask extends Task {

    private List<RecurringRule> recurringRuleList;


    public RepeatedTask(String name, String description, int importanceLvl) {
        super(name, description, importanceLvl);
    }
}


/*
* Every Monday at 3
* Every 3rd Tuesday at 4
* Every 2nd day at 5
*
*
* */