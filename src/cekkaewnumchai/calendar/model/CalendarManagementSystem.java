package cekkaewnumchai.calendar.model;

import java.time.LocalDate;
import java.util.SortedMap;

public class CalendarManagementSystem {
	private SortedMap<String, Calendar> calendars;

	// 1
	public boolean createCalendar(String name, LocalDate startDate,
		LocalDate endDate, int earlyHour, int latestHour, int duration) {
		if (calendars.containsKey(name))
			return false;

		calendars.put(name, new Calendar(name, startDate, endDate, earlyHour,
			latestHour, duration));
		return true;
	}
	
	// 2
	public boolean deleteCalendar(String name) {
		if (calendars.containsKey(name)) {
			calendars.remove(name);
			return true;
		} else 
			return false;
	}
	
	// 3
	public Calendar getCalendar(String name) {
		return calendars.get(name);
	}
}
