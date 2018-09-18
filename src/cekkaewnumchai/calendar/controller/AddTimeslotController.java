package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.AddTimeslotPage;
import cekkaewnumchai.calendar.view.CalendarPage;

public class AddTimeslotController extends TimeslotController {
	public AddTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		AddTimeslotPage page = new AddTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			calendar
				.addDay(LocalDate.parse(page.getTextField().getText(),
												DateTimeFormatter.ofPattern("yyyy/MM/dd")));
			
			updateTimeslotListView();
		}

		page.dispose();

		this.page.repaint();
	}
}
