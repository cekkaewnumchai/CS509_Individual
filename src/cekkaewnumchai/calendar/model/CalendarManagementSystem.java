package cekkaewnumchai.calendar.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class CalendarManagementSystem {
	private SortedMap<String, Calendar> calendars;
	
	public CalendarManagementSystem() {
		calendars = new TreeMap<>();
	}

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
	
	public Iterator<String> getCalendarNames() {
		return calendars.keySet().iterator();
	}
}
