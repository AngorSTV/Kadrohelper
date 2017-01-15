import controllers.KPeriod;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    //--- Sum Period Tab ---
    @FXML
    private VBox vBox;
    @FXML
    private Button buttonAddPeriod;
    @FXML
    private AnchorPane paneSumPeriod;
    private List<PeriodView> periodList = new ArrayList<>();
    private PeriodView resultSumPeriod;
    //--- End Sum Period Tab ---

    //--- Date Plus Period Tab ---
    @FXML
    private AnchorPane panelDatePlusPeriod;
    @FXML
    private DatePicker datePicker;
    @FXML
    private DatePicker datePicker1;
    private PeriodView addPeriod;
    //--- End Date Plus Period Tab ---

    //--- Vacantion Tab ---
    @FXML
    private AnchorPane tableVacantion;
    @FXML
    private VBox vBoxVacan;
    @FXML
    private Button buttonAddPeriodVacan;

    private Vacantion vacantion;
    //--- End Vacantion Tab ---


    @FXML
    private void initialize() {

        //--- Sum Period Tab ---
        Label lab = new Label("Итого:");
        AnchorPane.setTopAnchor(lab, 6.0);
        AnchorPane.setLeftAnchor(lab, 300.0);
        paneSumPeriod.getChildren().add(lab);

        resultSumPeriod = new PeriodView();
        AnchorPane.setTopAnchor(resultSumPeriod, 3.0);
        AnchorPane.setLeftAnchor(resultSumPeriod, 340.0);
        paneSumPeriod.getChildren().add(resultSumPeriod);

        vBox.getChildren().add(new KPeriod());
        vBox.getChildren().add(new KPeriod());

        buttonAddPeriod.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //PeriodView periodSum = new PeriodView();
                //periodList.add(periodSum);
                vBox.getChildren().add(new KPeriod());
            }
        });
        vBox.addEventHandler(Event.ANY, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sumPeriod();
            }
        });
        //--- End Sum Period Tab ---

        //--- Date Plus Period Tab ---
        addPeriod = new PeriodView();
        AnchorPane.setLeftAnchor(addPeriod, 14.0);
        AnchorPane.setTopAnchor(addPeriod, 80.0);
        panelDatePlusPeriod.getChildren().add(addPeriod);
        addPeriod.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                newPeriod();
            }
        });
        //--- End Date Plus Period Tab ---

        //--- Vacantion Tab ---
        vacantion = new Vacantion();
        vBoxVacan.getChildren().add(vacantion);
        //--- End Vacantion Tab ---

    }

    private void sumPeriod() {
        int year = 0;
        int month = 0;
        int day = 0;

        List<Node> list = vBox.getChildren();
        for (Node node:list){
            KPeriod iper = (KPeriod)node;
            if (iper.getPeriod() == null) break;
            year += iper.getPeriod().getYears();
            month += iper.getPeriod().getMonths();
            day += iper.getPeriod().getDays();
        }
        if (day > 30){
            double adMonth = day/30;
            month += (int) adMonth;
            day = day - (int)(adMonth * 30);
        }
        Period period = Period.of(year,month,day);
        resultSumPeriod.setPeriod(period.normalized());
    }

    private void newPeriod(){
        if (datePicker.getValue() == null){
            return;
        }
        LocalDate times = datePicker.getValue();
        Period ap = addPeriod.getPeriod();
        times = times.plusDays(ap.getDays());
        times = times.plusMonths(ap.getMonths());
        times = times.plusYears(ap.getYears());

        datePicker1.setValue(times);

    }
}
