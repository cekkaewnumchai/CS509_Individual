package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.CloseTimeslotPage;

public class CloseTimeslotController extends TimeslotController {
	public CloseTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		CloseTimeslotPage page = new CloseTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			DateTimeFormatter dateFormatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

			LocalDate date;
			LocalTime time;
			DayOfWeek weekday;

			// parse date
			try {
				date = LocalDate.parse(	page.getTextFieldDate().getText(),
												dateFormatter);
			} catch (Exception e) {
				date = null;
			}

			// parse time
			try {
				time = LocalTime.parse(	page.getTextFieldTime().getText(),
												timeFormatter);
			} catch (Exception e) {
				time = null;
			}
			
			// parse weekday
			try {
				weekday = (DayOfWeek)page.getWeekday().getSelectedItem();
			} catch (Exception e) {
				weekday = null;
			}
			
			
			if (date != null && time != null) { // a
				calendar.removeDateTime(date.atTime(time));
			} else if (time != null && weekday != null) { // b
				calendar.removeWeekdayTime(weekday, time);
			} else if (date != null) { // c
				calendar.removeDate(date);
			} else if (time != null) { // d
				calendar.removeTime(time);
			}

			updateTimeslotListView();
		}

		page.dispose();

		this.page.repaint();
	}

}
