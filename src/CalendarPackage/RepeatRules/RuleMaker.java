package CalendarPackage.RepeatRules;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class RuleMaker {

    private static List<RepeatEveryDayOfMonth> dayOfMonthRules;
    private static List<RepeatEveryDays> daysRules;
    private static List<RepeatEveryMonths> monthsRules;
    private static List<RepeatEveryNWeekDayOfMonth> nWeekDayOfMonthRules;
    private static List<RepeatEveryWeekDay> weekDayRules;
    private static List<RepeatEveryWeeks> weeksRules;


    public static RepeatEveryDayOfMonth getRuleDayOfMonth(int dayOfMonth){

        if(dayOfMonthRules==null)
            dayOfMonthRules = new ArrayList<>();

        for(RepeatEveryDayOfMonth rule: dayOfMonthRules){
            if(rule.getDayOfMonth()==dayOfMonth)
                return rule;
        }

        RepeatEveryDayOfMonth rule = new RepeatEveryDayOfMonth(dayOfMonth);
        dayOfMonthRules.add(rule);
        return rule;
    }

    public static RepeatEveryDays getRuleDays(int days){

        if(daysRules==null)
            daysRules = new ArrayList<>();

        for(RepeatEveryDays rule: daysRules){
            if(rule.getnDays()==days)
                return rule;
        }

        RepeatEveryDays rule = new RepeatEveryDays(days);
        daysRules.add(rule);
        return rule;
    }

    public static RepeatEveryWeekDay getRuleWeekDay(DayOfWeek weekday){

        if(weekDayRules==null)
            weekDayRules = new ArrayList<>();

        for(RepeatEveryWeekDay rule: weekDayRules){
            if(rule.getWeekDay()==weekday)
                return rule;
        }

        RepeatEveryWeekDay rule = new RepeatEveryWeekDay(weekday);
        weekDayRules.add(rule);
        return rule;
    }

    public static RepeatEveryNWeekDayOfMonth getRuleNWeekDayOfMonth(DayOfWeek weekday, int indexOfWeek){

        if(nWeekDayOfMonthRules==null)
            nWeekDayOfMonthRules = new ArrayList<>();

        for(RepeatEveryNWeekDayOfMonth rule: nWeekDayOfMonthRules){
            if(rule.getWeekDay()==weekday && rule.getIndexOfWeek()==indexOfWeek)
                return rule;
        }

        RepeatEveryNWeekDayOfMonth rule = new RepeatEveryNWeekDayOfMonth(weekday,indexOfWeek);
        nWeekDayOfMonthRules.add(rule);
        return rule;
    }

    public static RepeatEveryMonths getRuleMonths(int months){

        if(monthsRules==null)
            monthsRules = new ArrayList<>();

        for(RepeatEveryMonths rule: monthsRules){
            if(rule.getnMonths()==months)
                return rule;
        }

        RepeatEveryMonths rule = new RepeatEveryMonths(months);
        monthsRules.add(rule);
        return rule;
    }

    public static RepeatEveryWeeks getRuleWeeks(int weeks){

        if(weeksRules==null)
            weeksRules = new ArrayList<>();

        for(RepeatEveryWeeks rule: weeksRules){
            if(rule.getnWeeks()==weeks)
                return rule;
        }

        RepeatEveryWeeks rule = new RepeatEveryWeeks(weeks);
        weeksRules.add(rule);
        return rule;
    }
}
