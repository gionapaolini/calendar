import java.time.LocalDateTime;

public class TestTask {
    public static void main(String[] args){
        ScheduledTask t = new ScheduledTask("Prova","Description",10);
        t.setDate(2000,1,10);
        LocalDateTime date1 = LocalDateTime.now();
        RepeatEveryPeriod rule = new RepeatEveryPeriod(date1, 1, PeriodType.Weeks);
        LocalDateTime date2 = date1.plusMinutes(77);
        LocalDateTime date3 = date1.plusHours(2);
        LocalDateTime date4 = date1.plusHours(24*7);

        System.out.println(rule.isRecurringDate(date2));
        System.out.println(rule.isRecurringDate(date3));
        System.out.println(rule.isRecurringDate(date4));

    }
}
