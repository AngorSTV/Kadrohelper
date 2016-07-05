import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FormController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField result;

    @FXML
    private void initialize() {
        result.setEditable(false);
        endDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calculate();
            }
        });
        startDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calculate();
            }
        });
    }

    private void calculate() {
        if(startDatePicker.getValue() == null) {
            result.setText("");
            return;
        }
        if(endDatePicker.getValue() == null) {
            result.setText("");
            return;
        }

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        Period period = Period.between(startDate, endDate) ;
        String str = period.getYears() +" лет, " + period.getMonths() + " месяцев, ";
        str += period.getDays() + " дней.";
        result.setText(str);
    }
}
