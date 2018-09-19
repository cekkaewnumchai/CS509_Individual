package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.DateTimeslotPage;
import cekkaewnumchai.calendar.view.ShowTimeslotPage;

public class ShowDailyTimeslotController extends TimeslotController {
	public ShowDailyTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		DateTimeslotPage page = new DateTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (!page.wasUpdated())
			return;

		page.dispose();

		LocalDate date =
			LocalDate.parse(	page.getTextField().getText(),
									DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		DefaultListModel<String> listModel = new DefaultListModel<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		Iterator<Map.Entry<LocalDateTime, String>> it =
			calendar.getDailySchedule(date);
		while (it.hasNext()) {
			Map.Entry<LocalDateTime, String> entry = it.next();
			String s = entry.getKey().format(formatter);
			if (entry.getValue().length() > 0) {
				s += " -- ";
				s += entry.getValue();
			}
			listModel.addElement(s);
		}

		ShowTimeslotPage showPage = new ShowTimeslotPage();
		showPage.getShowList().setModel(listModel);
		showPage.getLabel()
			.setText(date.format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy")));
		showPage.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		showPage.setVisible(true);
		
		showPage.dispose();
	}

}
