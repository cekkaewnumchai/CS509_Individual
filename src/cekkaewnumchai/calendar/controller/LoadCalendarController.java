package cekkaewnumchai.calendar.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.MainPage;

public class LoadCalendarController extends CalendarController {

	public LoadCalendarController(CalendarManagementSystem cms, MainPage mp) {
		super(cms, mp);
	}

	@Override
	public void process() {
		Calendar calendar =
			cms.getCalendar(mainPage.getCalendarNameList().getSelectedValue());
		
		CalendarPage view = new CalendarPage(cms, calendar);
		
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPage);
		mainPage.setVisible(false);
		frame.add(view);
		frame.setContentPane(view);
		frame.validate();
		frame.repaint();
		view.setVisible(true);
	}

}
