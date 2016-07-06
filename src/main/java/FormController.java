import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FormController {
    @FXML
    private AnchorPane periodAPane;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    private PeriodView resultPeriod;


    @FXML
    private void initialize() {
        //--- Period Tab ---
        resultPeriod = new PeriodView();
        AnchorPane.setLeftAnchor(resultPeriod, 14.0);
        AnchorPane.setTopAnchor(resultPeriod, 132.0);
        periodAPane.getChildren().add(resultPeriod);

        endDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calcPeriod();
            }
        });
        startDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calcPeriod();
            }
        });
        //--- End Period Tab ---

        //--- Sum Period Tab ---

        //--- End Sum Period Tab ---
    }

    private void calcPeriod() {
        if(startDatePicker.getValue() == null) {
            resultPeriod.setPeriod(Period.ZERO);
            return;
        }
        if(endDatePicker.getValue() == null) {
            resultPeriod.setPeriod(Period.ZERO);
            return;
        }

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        Period period = Period.between(startDate, endDate) ;
        resultPeriod.setPeriod(period);
    }
}
