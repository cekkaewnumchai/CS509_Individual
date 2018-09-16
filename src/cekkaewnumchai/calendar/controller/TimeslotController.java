package cekkaewnumchai.calendar.controller;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;

public abstract class TimeslotController {
	Calendar calendar;
	CalendarPage page;
	
	public TimeslotController(Calendar calendar,CalendarPage view) {
		this.calendar = calendar;
		this.page = view;
	}
	
	public abstract void process();
}
