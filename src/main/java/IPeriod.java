import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.time.Period;

/**
 * Created by Андрей on 08.07.2016.
 */
public class IPeriod extends HBox {
    private Period period;
    private DatePicker startDate;
    private DatePicker endDate;
    private TextField resultPeriod;

    public IPeriod() {
        this(Period.ZERO);
    }

    public IPeriod(Period period) {
        super();
        this.period = period;
        startDate = new DatePicker();
        action(startDate);
        endDate = new DatePicker();
        action(endDate);
        resultPeriod = new TextField();
        construct();

    }

    private void action(DatePicker datePicker){
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refresh();
            }
        });
    }

    private void construct() {
        getChildren().add(startDate);
        getChildren().add(new Label(" - "));
        getChildren().add(endDate);
        getChildren().add(new Label(" = "));
        getChildren().add(resultPeriod);

    }

    private void refresh() {
        if (startDate.getValue() == null || endDate.getValue() == null) return;
        period = Period.between(startDate.getValue(), endDate.getValue());
        String str = period.getYears() + " лет ";
        str += period.getMonths() + " мес ";
        str += period.getDays() + " дн.";
        resultPeriod.setText(str);
    }

    public Period getPeriod(){
        return period;
    }
}