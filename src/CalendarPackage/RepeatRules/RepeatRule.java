package CalendarPackage;

import java.time.LocalDate;

public interface RepeatRule {

    boolean isDayValid(LocalDate startDate, Day day);

}
