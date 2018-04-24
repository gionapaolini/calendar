package CalendarPackage.EventsPackage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Habit extends Event{

    private HabitType type;
    private List<LocalDateTime> occurred;


    public Habit(String name, String description, float importance, HabitType type) {
        super(name, description, importance);
        this.type = type;
        occurred = new ArrayList<>();
    }

    public void occur(){
        occurred.add(LocalDateTime.now());
    }

    public void removeLastOccur(){
        if(occurred.size()<=0)
            return;
        occurred.remove(occurred.size()-1);
    }

    public int getCount(){
        return occurred.size();
    }

    public void printOccurences(){
        for(LocalDateTime date: occurred){
            System.out.println(date);
        }
    }




}
