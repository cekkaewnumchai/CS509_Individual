package cekkaewnumchai.calendar.controller;

import java.util.Iterator;

import javax.swing.DefaultListModel;

import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.MainPage;

public abstract class CalendarController {
	CalendarManagementSystem cms;
	MainPage mainPage;

	public CalendarController(CalendarManagementSystem cms, MainPage mp) {
		this.cms = cms;
		this.mainPage = mp;
	}

	void updateCalendarListView() {
		DefaultListModel<String> nameListModel =
			(DefaultListModel<String>) (mainPage.getCalendarNameList()
				.getModel());
		nameListModel.removeAllElements();
		Iterator<String> it = cms.getCalendarNames();
		while (it.hasNext()) {
			nameListModel.addElement(it.next());
		}
	}
	
	public abstract void process();
}
