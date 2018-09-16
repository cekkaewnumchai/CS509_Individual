package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.swing.DefaultListModel;

import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.view.AddTimeslotPage;
import cekkaewnumchai.calendar.view.CalendarPage;

public class AddTimeslotController extends TimeslotController {
	public AddTimeslotController(Calendar calendar, CalendarPage view) {
		super(calendar, view);
	}

	@Override
	public void process() {
		AddTimeslotPage page = new AddTimeslotPage();
		page.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		page.setVisible(true);

		if (page.wasUpdated()) {
			calendar
				.addDay(LocalDate.parse(page.getTextField().getText(),
												DateTimeFormatter.ofPattern("yyyy/MM/dd")));

			DefaultListModel<String> listModel =
				(DefaultListModel<String>) this.page.getSlotList().getModel();
			listModel.removeAllElements();
			DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			Iterator<LocalDateTime> it = calendar.getFreeSlots();
			while (it.hasNext()) {
				listModel.addElement(it.next().format(formatter));
			}
		}

		page.dispose();

		this.page.repaint();
	}
}
