package Gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TimeSlot {

    private final int hour;
    private TimeSlot before;
    private TimeSlot after;
    private Text timeLabel;

    private static DoubleProperty xOffset = CalendarViewer.xOffsetProperty();
    private static DoubleProperty yOffset = CalendarViewer.yOffsetProperty();
    private static DoubleProperty rowHeight = CalendarViewer.rowHeightProperty();
    private static DoubleProperty rowWidth = CalendarViewer.calendarWidthProperty();

    Rectangle rectangle;

    private static Color rectangleFill = null;
    private static Color rectangleLine = Color.BLACK;
    private static float lineWidth = 2;

    public TimeSlot(int hour) {
        this.hour = hour;
        timeLabel = new Text(hour+"");

        rectangle = new Rectangle();
        initializeRectangle();
    }

    public TimeSlot(int hour, TimeSlot before) {
        this.hour = hour;
        timeLabel = new Text(hour+"");

        this.before = before;
        before.setAfter(this);

        rectangle = new Rectangle();
        initializeRectangle();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    public void setBefore(TimeSlot before) {
        this.before = before;
    }

    public void setAfter(TimeSlot after) {
        this.after = after;
    }

    public void initializeRectangle(){

        rectangle.setFill(rectangleFill);
        rectangle.setStroke(rectangleLine);
        rectangle.setStrokeWidth(lineWidth);
        rectangle.getStyleClass().add("timeSlot");

        if(before!=null){
            rectangle.xProperty().bind(before.getXproperty());
            rectangle.yProperty().bind(before.getYproperty().add(before.getHeightProperty()));

        }else {
            rectangle.xProperty().bind(xOffset);
            rectangle.yProperty().bind(yOffset);
        }

        rectangle.heightProperty().bind(rowHeight);
        rectangle.widthProperty().bind(rowWidth);

        timeLabel.xProperty().bind(getXproperty().subtract(15));
        timeLabel.yProperty().bind(getYproperty().add(10));

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


    public Text getText() {
        return timeLabel;
    }
}
