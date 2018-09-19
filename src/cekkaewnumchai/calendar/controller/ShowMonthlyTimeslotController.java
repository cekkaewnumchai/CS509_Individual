package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.CalendarPage;
import cekkaewnumchai.calendar.view.DateTimeslotPage;
import cekkaewnumchai.calendar.view.MonthTimeslotPage;
import cekkaewnumchai.calendar.view.ShowTimeslotPage;

public class ShowMonthlyTimeslotController extends TimeslotController {
	public ShowMonthlyTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		MonthTimeslotPage page = new MonthTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (!page.wasUpdated())
			return;

		page.dispose();

		YearMonth month =
			YearMonth.parse(	page.getTextField().getText(),
									DateTimeFormatter.ofPattern("yyyy/MM"));

		DefaultListModel<String> listModel = new DefaultListModel<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd HH:mm");

		Iterator<Map.Entry<LocalDateTime, String>> it =
			calendar.getMonthlySchedule(month);
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
			.setText(month.format(DateTimeFormatter.ofPattern("MMM yyyy")));
		showPage.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		showPage.setVisible(true);
		
		showPage.dispose();
	}

}
