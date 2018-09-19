package cekkaewnumchai.calendar.controller;

import java.awt.Dialog;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import cekkaewnumchai.calendar.view.CloseTimeslotPage;
import cekkaewnumchai.calendar.view.ErrorDialog;

public class ValidateTimeslotController {
	CloseTimeslotPage page;

	public ValidateTimeslotController(CloseTimeslotPage page) {
		this.page = page;
	}

	public boolean validate() {
		DateTimeFormatter dateFormatter =
			DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		boolean date = false, time = false;
		
		// try parse date
		try {
			LocalDate.parse(page.getTextFieldDate().getText(), dateFormatter);
			date = true;
		} catch (Exception e) {
			date = false;
		}
		
		// try parse time
		try {
			LocalTime.parse(page.getTextFieldTime().getText(), timeFormatter);
			time = true;
		} catch (Exception e) {
			time = false;
		}
		
		// decide which to type from the input
		if(!date && !time) {
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
