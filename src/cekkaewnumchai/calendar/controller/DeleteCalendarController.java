package cekkaewnumchai.calendar.controller;

import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.MainPage;

public class DeleteCalendarController extends CalendarController {

	public DeleteCalendarController(CalendarManagementSystem cms, MainPage mp) {
		super(cms, mp);
	}
	
	public void process() {
		cms.deleteCalendar(mainPage.getCalendarNameList().getSelectedValue());
		
		updateCalendarListView();
	}
}
