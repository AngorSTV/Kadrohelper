import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.Period;

/**
 * Created by Андрей on 10.10.2016.
 */
public class Vacantion extends AnchorPane {
	private IPeriod period;
	private TextField calculatedDays;
	private TextField usedDays;
	private TextField restDays;
	private TextField supDays;

	public Float result;

	public Vacantion() {
		super();
		construct();
	}

	private void construct() {
		period = new IPeriod();
		period.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (period.getPeriod() == null) return;
				count();
			}
		});

		Label lb = new Label("Начало периода");
		AnchorPane.setLeftAnchor(lb, 4.0);
		getChildren().add(lb);

		lb = new Label("Конец периода");
		AnchorPane.setLeftAnchor(lb, 115.0);
		getChildren().add(lb);

		lb = new Label("Отработано");
		AnchorPane.setLeftAnchor(lb, 230.0);
		getChildren().add(lb);

		lb = new Label("За год");
		AnchorPane.setLeftAnchor(lb, 370.0);
		getChildren().add(lb);

		supDays = new TextField();
		AnchorPane.setLeftAnchor(supDays, 365.0);
		AnchorPane.setTopAnchor(supDays, 20.0);
		supDays.setPrefColumnCount(3);
		getChildren().add(supDays);

		supDays.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				count();
			}
		});

		lb = new Label("Отпуск за период");
		AnchorPane.setLeftAnchor(lb, 14.0);
		AnchorPane.setTopAnchor(lb, 50.0);
		getChildren().add(lb);

		calculatedDays = new TextField();
		AnchorPane.setTopAnchor(calculatedDays, 68.0);
		calculatedDays.setEditable(false);
		getChildren().add(calculatedDays);

		lb = new Label("Использовано отпуска");
		AnchorPane.setLeftAnchor(lb, 200.0);
		AnchorPane.setTopAnchor(lb, 50.0);
		getChildren().add(lb);

		usedDays = new TextField();
		AnchorPane.setLeftAnchor(usedDays, 186.0);
		AnchorPane.setTopAnchor(usedDays, 68.0);
		getChildren().add(usedDays);

		usedDays.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				count();
			}
		});

		lb = new Label("Остаток отпуска");
		AnchorPane.setLeftAnchor(lb, 385.0);
		AnchorPane.setTopAnchor(lb, 50.0);
		getChildren().add(lb);

		restDays = new TextField();
		AnchorPane.setLeftAnchor(restDays, 375.0);
		AnchorPane.setTopAnchor(restDays, 68.0);
		getChildren().add(restDays);

		AnchorPane.setTopAnchor(period, 20.0);
		getChildren().add(period);
	}

	private void count() {
		Float calc;
		Period p = period.getPeriod();
		String rawSup = supDays.getText();

		if (p.toString().equals("P0D")) return;
		if (rawSup.isEmpty()) return;

		int supposed = Integer.valueOf(rawSup);
		Integer fullMonth = (p.getYears() * 12) + p.getMonths();
		if (p.getDays() > 13) fullMonth++;
		calc = (supposed / 12.0f) * fullMonth;
		calculatedDays.setText(calc.toString());

		if (usedDays.getText().isEmpty()) usedDays.setText("0");
		int used = Integer.valueOf(usedDays.getText());
		result = calc - used;
		restDays.setText(result.toString());
	}
}
