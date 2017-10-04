package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.Period;

/**
 * кастомный элемент для отображения и подсчёта
 * остатка отпуска
 */
public class Vperiod extends VBox {
    @FXML
    private KPeriod kPeriod;
    @FXML
    private TextField forYear;
    @FXML
    private TextField calcDays;
    @FXML
    private TextField usedDays;
    @FXML
    private TextField restDays;

    @FXML
    protected void refresh() {
        Float calc;
        Period p = kPeriod.getPeriod();
        String rawForYear = forYear.getText();

        if (p.toString().equals("P0D")) return;
        if (rawForYear.isEmpty()) return;

        Integer supposed = Integer.valueOf(rawForYear);
        Integer fullMonth = (p.getYears() * 12) + p.getMonths();
        if (p.getDays() > 15) fullMonth++;
        calc = (supposed / 12.0f) * fullMonth;
        calcDays.setText(String.valueOf(calc));

        if (usedDays.getText().isEmpty()) usedDays.setText("0");
        int used = Integer.valueOf(usedDays.getText());
        Float result = calc - used;
        restDays.setText(result.toString());
    }

    public Vperiod (){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../vperiod.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
