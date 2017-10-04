package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.Period;

/**
 * Created by angor on 15.01.2017.
 * кастомный элемент для отображения
 * начала периода, его конца и
 * подсчёта разницы между этими датами
 */
public class KPeriod extends HBox {

    private Period period;

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField resultPeriod;

    @FXML
    protected void refresh() {
        if (startDate.getValue() == null || endDate.getValue() == null) return;
        period = Period.between(startDate.getValue(), endDate.getValue());
        String str = period.getYears() + " лет ";
        str += period.getMonths() + " мес ";
        str += period.getDays() + " дн.";
        resultPeriod.setText(str);
    }

    public KPeriod() {
        this(Period.ZERO);
    }

    public KPeriod(Period period) {
        this.period = period;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../kperiod.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Period getPeriod() {
        return period;
    }
}
