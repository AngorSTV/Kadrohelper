import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Андрей on 10.10.2016.
 */
public class Vacantion extends AnchorPane{
	private IPeriod period;
	private TextField calculatedDays;
	private TextField usedDays;
	private TextField restDays;
	public Vacantion(){
		super();
		construct();
	}

	private void construct() {
		period = new IPeriod();
		period.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
			if (period.getPeriod() == null) return;
				//TODO рассчёт отпуска
			}
		});


		calculatedDays = new TextField();
		usedDays = new TextField();
		restDays = new TextField();

		Label lb = new Label("Начало периода");
		AnchorPane.setLeftAnchor(lb, 14.0);
		getChildren().add(lb);
		lb = new Label("Конец периода");
		AnchorPane.setLeftAnchor(lb, 250.0);
		getChildren().add(lb);

		AnchorPane.setTopAnchor(period, 20.0);
		getChildren().add(period);
		//getChildren().add(calculatedDays);
		//getChildren().add(usedDays);
		//getChildren().add(restDays);
	}
}
