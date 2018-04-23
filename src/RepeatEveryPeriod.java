import java.time.LocalDateTime;

public class RepeatEveryPeriod implements RecurringType{

    private LocalDateTime startDate;
    private int periodLength;
    private PeriodType periodType;


    public RepeatEveryPeriod(LocalDateTime startDate, int periodLength, PeriodType periodType) {
        this.startDate =  startDate.withSecond(0);
        this.periodLength = periodLength;
        this.periodType = periodType;
    }

    @Override
    public boolean isRecurringDate(LocalDateTime date) {

        LocalDateTime temp = date.withSecond(0);

        switch (periodType){
            case Minutes:
                while (temp.isAfter(startDate)){
                    temp = temp.minusMinutes(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;
            case Hours:
                while (temp.isAfter(startDate)){
                    temp = temp.minusHours(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;
            case Days:
                while (temp.isAfter(startDate)){
                    temp = temp.minusDays(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;
            case Weeks:
                while (temp.isAfter(startDate)){
                    temp = temp.minusWeeks(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;
            case Months:
                while (temp.isAfter(startDate)){
                    temp = temp.minusMonths(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;
            case Years:
                while (temp.isAfter(startDate)){
                    temp = temp.minusYears(periodLength);
                    if(temp.isEqual(startDate))
                        return true;
                }
                break;


        }




        return false;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }
}
