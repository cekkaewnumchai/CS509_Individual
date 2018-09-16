package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cekkaewnumchai.calendar.view.AddCalendarPage;

public class ValidateCalendarController {
	AddCalendarPage page;

	public ValidateCalendarController(AddCalendarPage page) {
		this.page = page;
	}

	public boolean validate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		try {
			// try parsing
			if (page.getCalendarName().getText().equals(""))
				throw new Exception();
			LocalDate.parse(page.getStartDate().getText(), formatter);
			LocalDate.parse(page.getEndDate().getText(), formatter);
			int earlyHour = Integer.parseInt(page.getEarlyHour().getText());
			int latestHour = Integer.parseInt(page.getLatestHour().getText());
			if (earlyHour < 0 && earlyHour >= 24 && latestHour < 0 &&
					latestHour >= 24)
				throw new Exception();
			if (page.getDuration().getSelectedIndex() < 0)
				throw new Exception();

		} catch (Exception e) {
			ErrorDialog errorDialog = new ErrorDialog("Invalid input");
			errorDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			errorDialog.setVisible(true);

			// wait for user to close it
			errorDialog.dispose();
			return false;
		}

		return true;
	}
}
