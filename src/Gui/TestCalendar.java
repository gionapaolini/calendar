package Gui;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TestCalendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        CalendarViewer calendarViewer = new CalendarViewer();

        BorderPane bpane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(CalendarViewer.getAllComponents());

        bpane.setCenter(scrollPane);
        Slider sliderRow = new Slider();
        sliderRow.setMin(10);
        sliderRow.setMax(100);
        sliderRow.setValue(20);
        sliderRow.setBlockIncrement(5);
        sliderRow.setOrientation(Orientation.VERTICAL);
        sliderRow.valueProperty().addListener((e)->{
            CalendarViewer.setRowHeight(sliderRow.getValue());
        });
        Slider sliderMin = new Slider();
        sliderMin.setMin(0);
        sliderMin.setMax(23);
        sliderMin.setValue(0);
        sliderMin.setBlockIncrement(1);
        sliderMin.setOrientation(Orientation.VERTICAL);
        sliderMin.valueProperty().addListener((e)->{
            if((int)sliderMin.getValue()>CalendarViewer.getMaxHour()){
                sliderMin.setValue(CalendarViewer.getMaxHour());
                return;
            }

            CalendarViewer.setMinHour((int)sliderMin.getValue());
        });
        Slider sliderMax = new Slider();
        sliderMax.setMin(0);
        sliderMax.setMax(23);
        sliderMax.setValue(23);
        sliderMax.setBlockIncrement(1);
        sliderMax.setOrientation(Orientation.VERTICAL);
        sliderMax.valueProperty().addListener((e)->{
            if((int)sliderMax.getValue()<CalendarViewer.getMinHour()){
                sliderMax.setValue(CalendarViewer.getMinHour());
                return;
            }

            CalendarViewer.setMaxHour((int)sliderMax.getValue());
        });

        HBox rigthSliders = new HBox();
        rigthSliders.getChildren().addAll(sliderRow,sliderMin,sliderMax);
        bpane.setRight(rigthSliders);

        Scene scene = new Scene(bpane,1200,700);

        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add("style.css");





    }

}
