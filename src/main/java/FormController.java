import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
    private VBox vBox;
    @FXML
    private Button buttonAddPeriod;
    @FXML
    private AnchorPane paneSumPeriod;
    private List<PeriodView> periodList = new ArrayList<>();
    private PeriodView resultSumPeriod;


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
        vBox.getChildren().add(new IPeriod());
        vBox.getChildren().add(new IPeriod());
        /*PeriodView periodSum = new PeriodView();
        periodList.add(periodSum);
        vBox.getChildren().add(periodSum);

        periodSum = new PeriodView();
        periodList.add(periodSum);
        vBox.getChildren().add(periodSum);
        Label label = new Label("Итого:");
        AnchorPane.setBottomAnchor(label, 32.0);
        AnchorPane.setLeftAnchor(label, 14.0);
        paneSumPeriod.getChildren().add(label);
        resultSumPeriod = new PeriodView();
        AnchorPane.setBottomAnchor(resultSumPeriod, 4.0);
        AnchorPane.setLeftAnchor(resultSumPeriod, 14.0);
        paneSumPeriod.getChildren().add(resultSumPeriod);*/

        buttonAddPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //PeriodView periodSum = new PeriodView();
                //periodList.add(periodSum);
                vBox.getChildren().add(new IPeriod());
            }
        });
        vBox.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event);
                sumPeriod();
            }
        });
        //--- End Sum Period Tab ---
    }

    private void sumPeriod() {
        int year = 0;
        int month = 0;
        int day = 0;
        for (PeriodView period:periodList){
            year += period.getPeriod().getYears();
            month += period.getPeriod().getMonths();
            day += period.getPeriod().getDays();
        }
        Period period = Period.of(year,month,day);
        resultSumPeriod.setPeriod(period);
    }

    private void calcPeriod() {
        if (startDatePicker.getValue() == null) {
            resultPeriod.setPeriod(Period.ZERO);
            return;
        }
        if (endDatePicker.getValue() == null) {
            resultPeriod.setPeriod(Period.ZERO);
            return;
        }

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        Period period = Period.between(startDate, endDate);
        resultPeriod.setPeriod(period);
    }
}
