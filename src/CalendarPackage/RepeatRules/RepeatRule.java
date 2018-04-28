package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface RepeatRule {
    List<LocalDateTime> getValidDates(LocalDate startDate, LocalTime time, LocalDate endDate);
}
