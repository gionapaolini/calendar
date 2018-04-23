package CalendarPackage;


import CalendarPackage.RepeatRules.RepeatEveryMonths;
import CalendarPackage.RepeatRules.RepeatEveryWeeks;
import CalendarPackage.RepeatRules.RepeatRule;

import java.time.LocalDate;

public class TestRules {

    public static void main(String[] args){

        Day today = new Day(LocalDate.now().plusMonths(2).plusDays(61));
        RepeatRule rule = new RepeatEveryMonths(2);


        System.out.println(rule.isDayValid(LocalDate.now(),today));



    }

}
