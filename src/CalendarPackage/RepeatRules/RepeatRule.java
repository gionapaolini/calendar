package CalendarPackage.RepeatRules;

import java.time.LocalDate;
import java.util.List;

public interface RepeatRule {
    List<LocalDate> getValidDates(LocalDate startDate, LocalDate endDate);
}
