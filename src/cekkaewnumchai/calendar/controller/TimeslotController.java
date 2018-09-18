package cekkaewnumchai.calendar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.swing.DefaultListModel;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;

public abstract class TimeslotController {
	Calendar calendar;
	CalendarPage page;

	public TimeslotController(Calendar calendar, CalendarPage view) {
		this.calendar = calendar;
		this.page = view;
	}

	void updateTimeslotListView() {
		DefaultListModel<String> listModel =
			(DefaultListModel<String>) (page.getSlotList().getModel());
		listModel.removeAllElements();
		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		Iterator<LocalDateTime> it = calendar.getFreeSlots();
		while (it.hasNext()) {
			listModel.addElement(it.next().format(formatter));
		}
	}

	public abstract void process();
}
