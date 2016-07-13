import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.time.Period;

/**
 * Created by Андрей on 05.07.2016.
 */
public class PeriodView extends AnchorPane {
    private Period period;
    private TextField yearsField;
    private TextField monthField;
    private TextField daysField;

    public PeriodView() {
        this(Period.ZERO);
    }

    public PeriodView(Period period) {
        super();
        this.period = period;

        yearsField = new TextField(String.valueOf(period.getYears()));
        yearsField.setPrefHeight(25);
        yearsField.setPrefWidth(35);
        AnchorPane.setTopAnchor(yearsField, 0.0);
        AnchorPane.setLeftAnchor(yearsField, 0.0);
        getChildren().add(yearsField);
        yearsField.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Period.ofYears()
            }
        });

        Label label = new Label("лет");
        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 35.0);
        getChildren().add(label);

        monthField = new TextField(String.valueOf(period.getMonths()));
        monthField.setPrefHeight(25);
        monthField.setPrefWidth(35);
        AnchorPane.setTopAnchor(monthField, 0.0);
        AnchorPane.setLeftAnchor(monthField, 60.0);
        getChildren().add(monthField);

        label = new Label("месяц");
        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 95.0);
        getChildren().add(label);

        daysField = new TextField(String.valueOf(period.getDays()));
        daysField.setPrefHeight(25);
        daysField.setPrefWidth(35);
        AnchorPane.setTopAnchor(daysField, 0.0);
        AnchorPane.setLeftAnchor(daysField, 135.0);
        getChildren().add(daysField);

        label = new Label("дней");
        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 170.0);
        getChildren().add(label);
    }

    public void setPeriod(Period period) {
        this.period = period;
        refresh();
    }

    public Period getPeriod() {
        return period;
    }

    private void refresh() {
        yearsField.setText(String.valueOf(period.getYears()));
        monthField.setText(String.valueOf(period.getMonths()));
        daysField.setText(String.valueOf(period.getDays()));
    }
}
