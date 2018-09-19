package cekkaewnumchai.calendar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import cekkaewnumchai.calendar.model.CalendarManagementSystem;
import cekkaewnumchai.calendar.view.Application;

public class Main {

	public static void main(String[] args) {
		CalendarManagementSystem cms =
			CalendarManagementSystem.parseFromFile("calendars.json");

		Application mainApp = new Application(cms);

		mainApp.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					cms.writeToFile("calendars.json");
				} finally {
					
				}
			}
		});

		mainApp.setVisible(true);
	}

}
