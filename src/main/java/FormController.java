import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        Label lab = new Label("Итого:");
        AnchorPane.setTopAnchor(lab, 6.0);
        AnchorPane.setLeftAnchor(lab, 350.0);
        paneSumPeriod.getChildren().add(lab);

        resultSumPeriod = new PeriodView();
        AnchorPane.setTopAnchor(resultSumPeriod, 3.0);
        AnchorPane.setLeftAnchor(resultSumPeriod, 390.0);
        paneSumPeriod.getChildren().add(resultSumPeriod);

        vBox.getChildren().add(new IPeriod());
        vBox.getChildren().add(new IPeriod());

        buttonAddPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //PeriodView periodSum = new PeriodView();
                //periodList.add(periodSum);
                vBox.getChildren().add(new IPeriod());
            }
        });
        vBox.addEventHandler(Event.ANY, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sumPeriod();
            }
        });
        //--- End Sum Period Tab ---
    }

    private void sumPeriod() {
        int year = 0;
        int month = 0;
        int day = 0;

        List<Node> list = vBox.getChildren();
        for (Node node:list){
            IPeriod iper = (IPeriod)node;
            if (iper.getPeriod() == null) break;
            year += iper.getPeriod().getYears();
            month += iper.getPeriod().getMonths();
            day += iper.getPeriod().getDays();
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
