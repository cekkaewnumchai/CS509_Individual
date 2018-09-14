package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.AddingCalendarPage;
import cekkaewnumchai.calendar.view.MainPage;

public class AddCalendarController {
	CalendarManagementSystem cms;
	MainPage mainPage;

	public AddCalendarController(CalendarManagementSystem cms, MainPage mp) {
		this.cms = cms;
		this.mainPage = mp;
	}

	public void process() {
		AddingCalendarPage page = new AddingCalendarPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd");

			String name = page.getCalendarName().getText();
			LocalDate startDate =
				LocalDate.parse(page.getStartDate().getText(), formatter);
			LocalDate endDate =
				LocalDate.parse(page.getEndDate().getText(), formatter);
			int earlyHour = Integer.parseInt(page.getEarlyHour().getText());
			int latestHour = Integer.parseInt(page.getLatestHour().getText());
			int duration = (Integer) page.getDuration().getSelectedItem();

			cms.createCalendar(	name, startDate, endDate, earlyHour, latestHour,
										duration);

			DefaultListModel<String> nameListModel =
				(DefaultListModel<String>) (mainPage.getCalendarNameList()
					.getModel());
			nameListModel.removeAllElements();
			Iterator<String> it = cms.getCalendarNames();
			while (it.hasNext()) {
				nameListModel.addElement(it.next());
			}
		}

		page.dispose();

		mainPage.repaint();
	}
}