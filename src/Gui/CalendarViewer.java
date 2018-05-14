package Gui;

import CalendarPackage.EventsPackage.InstanceScheduledTask;
import CalendarPackage.EventsPackage.ScheduledTask;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import javax.script.SimpleBindings;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class CalendarViewer {

    private static DoubleProperty rowHeight = new SimpleDoubleProperty(20);
    private static DoubleProperty colWidth = new SimpleDoubleProperty(100);
    private static DoubleProperty calendarHeight = new SimpleDoubleProperty(0);
    private static DoubleProperty calendarWidth = new SimpleDoubleProperty(0);

    private static DoubleProperty xOffset = new SimpleDoubleProperty(25);
    private static DoubleProperty yOffset = new SimpleDoubleProperty(25);

    private static int minHour =0;
    private static int maxHour =23;

    private static LocalDate startDate=LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    private static LocalDate endDate=LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));


    private static List<InstanceScheduledTask> tasks;

    private static List<TimeSlot> timeSlots;
    private static List<DaySlot> daySlots;


    private static Group calendarColumns;
    private static Group calendarRows;
    private static Group taskInstances;

    private static Group allComponents;

    public CalendarViewer(){

        calendarColumns = new Group();
        calendarRows = new Group();
        taskInstances = new Group();
        allComponents = new Group();

        allComponents.getChildren().addAll(calendarColumns,calendarRows,taskInstances);

        daySlots = new ArrayList<>();
        timeSlots = new ArrayList<>();
        tasks = new ArrayList<>();
        initializeDaySlots();
        initializeTimeSlots();
        updateTasksOnDays();
        System.out.println(calendarWidth);
        testAddTask();
    }

    private void initializeDaySlots() {

        daySlots.clear();
        calendarColumns.getChildren().clear();

        daySlots.add(new DaySlot(startDate));
        calendarColumns.getChildren().add(daySlots.get(0).getRectangle());
        calendarColumns.getChildren().add(daySlots.get(0).getText());

        int gap = Period.between(startDate,endDate).getDays();
        for(int i=1;i<gap;i++){
            DaySlot d = new DaySlot(startDate.plusDays(i),daySlots.get(daySlots.size()-1));
            daySlots.add(d);
            calendarColumns.getChildren().add(d.getRectangle());
            calendarColumns.getChildren().add(d.getText());
        }


        calculateWidth();




    }


    private static void initializeTimeSlots() {

        timeSlots.clear();
        calendarRows.getChildren().clear();


        timeSlots.add(new TimeSlot(minHour));
        calendarRows.getChildren().add(timeSlots.get(0).getRectangle());
        calendarRows.getChildren().add(timeSlots.get(0).getText());

        for(int i=minHour+1;i<=maxHour;i++){
            TimeSlot t = new TimeSlot(i,timeSlots.get(timeSlots.size()-1));
            timeSlots.add(t);
            calendarRows.getChildren().add(t.getRectangle());
            calendarRows.getChildren().add(t.getText());

        }

        calendarRows.getChildren().size();
        calculateHeight();


    }


    private static void calculateHeight() {

        SimpleDoubleProperty property = new SimpleDoubleProperty();
        property.bind(rowHeight.multiply(maxHour-minHour+1));
        calendarHeight.bind(property);

    }

    private static void calculateWidth() {

        DoubleBinding totalWidth = new SimpleDoubleProperty().add(0);

        for(int i=0;i<daySlots.size();i++){
            totalWidth = totalWidth.add(daySlots.get(i).ownWidthProperty());
        }
        calendarWidth.bind(totalWidth);
    }

    public static void setMaxHour(int hour){
        maxHour=hour;
        initializeTimeSlots();
        updateTasksOnDays();
    }



    public static void setMinHour(int hour){
        minHour=hour;
        initializeTimeSlots();
        updateTasksOnDays();
    }

    public static void updateTasksOnDays(){

        //taskInstances.getChildren().clear();
        daySlots.forEach(daySlot -> {
            daySlot.displayRectangle(tasks);
           // taskInstances.getChildren().addAll(daySlot.getTasksRectangle().values());
        });

    }

    public static double getRowHeight() {
        return rowHeight.get();
    }

    public static DoubleProperty rowHeightProperty() {
        return rowHeight;
    }

    public static double getColWidth() {
        return colWidth.get();
    }

    public static DoubleProperty colWidthProperty() {
        return colWidth;
    }

    public static double getxOffset() {
        return xOffset.get();
    }

    public static DoubleProperty xOffsetProperty() {
        return xOffset;
    }

    public static double getyOffset() {
        return yOffset.get();
    }

    public static DoubleProperty yOffsetProperty() {
        return yOffset;
    }

    public static int getMinHour() {
        return minHour;
    }

    public static int getMaxHour() {
        return maxHour;
    }

    public static LocalDate getStartDate() {
        return startDate;
    }

    public static LocalDate getEndDate() {
        return endDate;
    }

    public static List<InstanceScheduledTask> getTasks() {
        return tasks;
    }

    public static List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public static List<DaySlot> getDaySlots() {
        return daySlots;
    }

    public static double getCalendarHeight() {
        return calendarHeight.get();
    }

    public static DoubleProperty calendarHeightProperty() {
        return calendarHeight;
    }

    public static double getCalendarWidth() {
        return calendarWidth.get();
    }

    public static DoubleProperty calendarWidthProperty() {
        return calendarWidth;
    }

    public static Group getAllComponents() {
        return allComponents;
    }

    public static void setRowHeight(double rowHeight) {
        CalendarViewer.rowHeight.set(rowHeight);
    }


    public static Group getTaskInstances() {
        return taskInstances;
    }

    private void testAddTask(){
        LocalDateTime timeDate = LocalDateTime.of(2018,05,14,3,0);
        LocalDateTime timeDate1 = LocalDateTime.of(2018,05,16,3,0);

        ScheduledTask scheduledTask = new ScheduledTask("ProvaTitle","Little Description",5,1*24*60,timeDate);
        ScheduledTask scheduledTask1 = new ScheduledTask("ProvaTitle","Little Description",5,1*24*60,timeDate1);

        InstanceScheduledTask task = scheduledTask.generateInstances(LocalDate.now().plusYears(10)).get(0);
        InstanceScheduledTask task1 = scheduledTask1.generateInstances(LocalDate.now().plusYears(10)).get(0);

        tasks.add(task);
        tasks.add(task1);

        updateTasksOnDays();

    }




}
