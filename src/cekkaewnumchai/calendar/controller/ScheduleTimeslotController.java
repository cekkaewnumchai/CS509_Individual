package cekkaewnumchai.calendar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;

public class ScheduleTimeslotController extends TimeslotController {
	public ScheduleTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		try {
			calendar.scheduleDateTime(
												LocalDateTime.parse(
																			page.getSlotList()
																				.getSelectedValue(),
																			formatter),
												page.getMeetingNameField().getText());
			
			updateTimeslotListView();
			page.getMeetingNameField().setText("");
		} catch (Exception e) {

		}
	}
}
