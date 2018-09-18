package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.CancelTimeslotPage;

public class CancelTimeslotController extends TimeslotController {
	public CancelTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		CancelTimeslotPage page = new CancelTimeslotPage(calendar);
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			String dateName = page.getMeetingList().getSelectedValue();
			calendar.cancelDateTime(LocalDateTime
				.parse(	dateName.split("-")[0],
							DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm ")));
			
			updateTimeslotListView();
		}

		page.dispose();

		this.page.repaint();
	}
}
