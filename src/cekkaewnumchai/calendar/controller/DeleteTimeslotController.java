package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.DeleteTimeslotPage;
import cekkaewnumchai.calendar.view.CalendarPage;

public class DeleteTimeslotController extends TimeslotController {

	public DeleteTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}
	
	@Override
	public void process() {
		DeleteTimeslotPage page = new DeleteTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			calendar
				.deleteDate(LocalDate.parse(page.getTextField().getText(),
												DateTimeFormatter.ofPattern("yyyy/MM/dd")));

			updateTimeslotListView();
		}

		page.dispose();

		this.page.repaint();
	}

}
