package CalendarPackage.RepeatRules;

import CalendarPackage.Day;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RepeatRule {

    boolean isDayValid(LocalDate startDate, Day day);

    List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate);
}
