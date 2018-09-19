package cekkaewnumchai.calendar.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.MainPage;

public class BackToCalendarController extends TimeslotController {
	CalendarManagementSystem cms;

	public BackToCalendarController(CalendarManagementSystem cms,
		Calendar calendar, CalendarPage page) {
		super(calendar, page);
		this.cms = cms;
	}

	@Override
	public void process() {
		MainPage view = new MainPage(cms);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(page);
		page.setVisible(false);
		frame.add(view);
		frame.setContentPane(view);
		frame.validate();
		frame.repaint();
		view.setVisible(true);
	}

}
