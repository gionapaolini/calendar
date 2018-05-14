package Gui;

import CalendarPackage.Calendar;
import CalendarPackage.EventsPackage.InstanceScheduledTask;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaySlot {


    private LocalDate startDate;
    private DaySlot before;
    private DaySlot after;
    private static DoubleProperty xOffset = CalendarViewer.xOffsetProperty();
    private static DoubleProperty yOffset = CalendarViewer.yOffsetProperty();
    private static DoubleProperty dayHeight = CalendarViewer.calendarHeightProperty();
    private static DoubleProperty dayWidth = CalendarViewer.colWidthProperty();
    private DoubleProperty ownWidth;

    private Rectangle rectangle;
    private Text dayLabel;

    private static Color rectangleFill = null;
    private static Color rectangleLine = Color.BLACK;
    private static float lineWidth = 2;

    private HashMap<InstanceScheduledTask,Rectangle> rectangleMap;

    public double startDragY;
    private LocalDate startDragDate;
    private LocalDateTime startOriginalDate;

    public DaySlot(LocalDate startDate) {
        this.startDate = startDate;
        ownWidth = new SimpleDoubleProperty(0);
        ownWidth.bind(dayWidth);
        rectangle = new Rectangle();
        String text = startDate.format(DateTimeFormatter.ofPattern("EEE, MMM d"));
        dayLabel = new Text(text);

        initializeRectangle();

        rectangleMap = new HashMap<>();


    }
    public DaySlot(LocalDate startDate, DaySlot before) {
        this.startDate = startDate;
        ownWidth = new SimpleDoubleProperty(0);
        ownWidth.bind(dayWidth);
        String text = startDate.format(DateTimeFormatter.ofPattern("EEE, MMM d"));
        dayLabel = new Text(text);

        rectangle = new Rectangle();

        this.before = before;
        before.setAfter(this);

        initializeRectangle();

        rectangleMap = new HashMap<>();

    }


    public void setAfter(DaySlot after) {
        this.after = after;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    public double getOwnWidth() {
        return ownWidth.get();
    }

    public DoubleProperty ownWidthProperty() {
        return ownWidth;
    }

    private void initializeRectangle(){

        rectangle.setFill(rectangleFill);
        rectangle.setStroke(rectangleLine);
        rectangle.setStrokeWidth(lineWidth);
        rectangle.getStyleClass().add("timeSlot");


        if(before!=null){
            rectangle.xProperty().bind(before.getXproperty().add(before.getWidthProperty()));
            rectangle.yProperty().bind(before.getYproperty());

        }else {
            rectangle.xProperty().bind(xOffset);
            rectangle.yProperty().bind(yOffset);
        }

        rectangle.heightProperty().bind(dayHeight);
        rectangle.widthProperty().bind(dayWidth);

        dayLabel.yProperty().bind(getYproperty().subtract(10));
        dayLabel.xProperty().bind(getXproperty());

    }

    protected DoubleProperty getXproperty(){
        return rectangle.xProperty();
    }
    protected DoubleProperty getYproperty(){
        return rectangle.yProperty();
    }

    protected DoubleProperty getWidthProperty(){
        return rectangle.widthProperty();
    }
    protected DoubleProperty getHeightProperty(){
        return rectangle.heightProperty();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Text getText() {
        return dayLabel;
    }

    public HashMap<InstanceScheduledTask, Rectangle> getTasksRectangle() {
        return rectangleMap;
    }

    public void displayRectangle(List<InstanceScheduledTask> tasks) {

        tasks.forEach(task -> {

            LocalDateTime startTimeOfDay = LocalDateTime.of(startDate,LocalTime.of(CalendarViewer.getMinHour(),0));
            LocalDateTime endTimeOfDay = LocalDateTime.of(startDate,LocalTime.of(CalendarViewer.getMaxHour(),0));
            endTimeOfDay = endTimeOfDay.plusMinutes(60);
            LocalDateTime startTimeOfTask = task.getInstanceDate();
            LocalDateTime endTimeOfTask = startTimeOfTask.plusMinutes(task.getDuration());

            if(startTimeOfTask.isAfter(endTimeOfDay) || endTimeOfTask.isBefore(startTimeOfDay) ||
                 startTimeOfTask.isEqual(endTimeOfDay) || endTimeOfTask.isEqual(startTimeOfDay) ){

                if(rectangleMap.get(task)!=null)
                    rectangleMap.get(task).setVisible(false);
                rectangleMap.remove(task);

                return;

            }
            else {
                if((startTimeOfTask.isBefore(startTimeOfDay) || startTimeOfTask.isEqual(startTimeOfDay))  &&
                        (endTimeOfTask.isAfter(endTimeOfDay) || endTimeOfTask.isEqual(endTimeOfDay))){
                    fillRectangle(task);
                }else if(startTimeOfTask.isAfter(startTimeOfDay) && endTimeOfTask.isBefore(endTimeOfDay)){
                    partialRectangle(task);
                }else if(startTimeOfTask.isAfter(startTimeOfDay)){
                    lowerHalfFillRectangle(task);
                }else {

                    upperHalfFillRectangle(task);

                }

            }
        });


    }

    private void lowerHalfFillRectangle(InstanceScheduledTask task) {
        int startMinutes = task.getInstanceDate().getHour()*60 +task.getInstanceDate().getMinute() ;
        int endMinutes = 60*CalendarViewer.getMaxHour() + 60;

        fillFromTo(startMinutes,endMinutes,task);
    }

    private void upperHalfFillRectangle(InstanceScheduledTask task) {
        int startMinutes = 60*CalendarViewer.getMinHour();

        int endMinutes = task.getFinishDate().getHour()*60+task.getFinishDate().getMinute();

        fillFromTo(startMinutes,endMinutes,task);
    }

    private void partialRectangle(InstanceScheduledTask task) {

        int startMinutes = task.getInstanceDate().getHour()*60 +task.getInstanceDate().getMinute() ;
        int endMinutes = startMinutes + task.getDuration();
        fillFromTo(startMinutes,endMinutes,task);

    }

    private void fillRectangle(InstanceScheduledTask task) {
        int startMinutes = 60*CalendarViewer.getMinHour();
        int endMinutes = 60*CalendarViewer.getMaxHour() + 60;
        fillFromTo(startMinutes,endMinutes,task);
    }

    private void fillFromTo(int startMinute,int endMinute, InstanceScheduledTask task){
        int hour = startMinute/60;
        int minutes = startMinute%60;
        int realHours = hour - CalendarViewer.getMinHour();
        int duration = endMinute - startMinute;

        Rectangle rectangleEvent;
        if(rectangleMap.containsKey(task)){
            rectangleEvent = rectangleMap.get(task);
            rectangleEvent.setVisible(true);
        }else {
            rectangleEvent = new Rectangle();
            rectangleEvent.setStroke(Color.BLACK);
            rectangleEvent.setFill(Color.YELLOW);

            rectangleMap.put(task, rectangleEvent);
            CalendarViewer.getTaskInstances().getChildren().add(rectangleEvent);
            attachMouseListener(rectangleEvent,task);
        }

        rectangleEvent.xProperty().bind(this.rectangle.xProperty());
        rectangleEvent.yProperty().bind(CalendarViewer.getTimeSlots().get(realHours).getYproperty().add(CalendarViewer.rowHeightProperty().multiply(minutes).divide(60)));
        rectangleEvent.heightProperty().bind(CalendarViewer.rowHeightProperty().multiply(duration).divide(60));
        rectangleEvent.widthProperty().bind(dayWidth);

    }

    private void attachMouseListener(Rectangle rectangleEvent, InstanceScheduledTask task) {

        rectangleEvent.setOnMousePressed(event -> {
            startDragY = event.getY();
            startDragDate = startDate;
            startOriginalDate=task.getInstanceDate();
        });


        rectangleEvent.setOnMouseDragged(event -> {


            int minutesDifference = (int)(((event.getY()-startDragY)*60)/CalendarViewer.getRowHeight());

            startOriginalDate = startOriginalDate.plusMinutes(minutesDifference);

            startDragY = event.getY();

            CalendarViewer.updateTasksOnDays();

            for(int i = CalendarViewer.getDaySlots().size()-1;i>=0;i--){
                if(event.getX()>CalendarViewer.getDaySlots().get(i).getRectangle().getX()){
                    LocalDate x = CalendarViewer.getDaySlots().get(i).getStartDate();
                    long d = Period.between(startDragDate,x).getDays();
                    task.setStartDate(startOriginalDate.plusDays(d));
                    break;
                }
            }



        });



    }
}
