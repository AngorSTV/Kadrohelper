import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Андрей on 10.10.2016.
 */
public class Vacantion extends HBox{
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

		getChildren().add(period);
		getChildren().add(calculatedDays);
		getChildren().add(usedDays);
		getChildren().add(restDays);
	}
}
