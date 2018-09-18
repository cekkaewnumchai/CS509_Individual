package cekkaewnumchai.calendar.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Calendar {
	String name;
	SortedMap<LocalDateTime, String> freeSlots, reservedSlots;
	// NOTE: end hour is excluded at the end
	LocalTime startTime, endTime;
	int duration;

	/**
	 * Initialize Calendar
	 * 
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param earlyHour
	 * @param latestHour
	 * @param duration   in minutes
	 */
	public Calendar(String name, LocalDate startDate, LocalDate endDate,
		int earlyHour, int latestHour, int duration) {
		this.name = name;
		this.freeSlots = new TreeMap<>();
		this.reservedSlots = new TreeMap<>();
		this.duration = duration;
		this.startTime = LocalTime.of(earlyHour, 0);
		this.endTime = LocalTime.of(latestHour, 59);

		for (	LocalDate date = startDate;
				date.compareTo(endDate) <= 0;
				date = date.plusDays(1)) {
			DayOfWeek day = date.getDayOfWeek();

			// skip saturday and sunday
			if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
				continue;

			// expect earlyHour to be less than latestHour
			if (earlyHour > latestHour)
				break;

			for (	LocalTime time = startTime;
					time.compareTo(endTime) < 0;
					time = time.plusMinutes(duration)) {
				freeSlots.put(date.atTime(time), "");
				if (time.compareTo(time.plusMinutes(duration)) > 0)
					break;
			}
		}
	}

	// 4
	public boolean addDay(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();

		// skip saturday and sunday
		if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
			return false;

		for (	LocalTime time = startTime;
				time.compareTo(endTime) <= 0;
				time = time.plusMinutes(duration)) {
			freeSlots.put(date.atTime(time), "");
			if (time.compareTo(time.plusMinutes(duration)) > 0)
				break;
		}
		return true;
	}

	// 5
	public boolean deleteDate(LocalDate date) {
		// remove free slots
		removeDate(date);

		// remove reserved slots
		Iterator<Map.Entry<LocalDateTime, String>> it =
			reservedSlots.entrySet().iterator();
		while (it.hasNext()) {
			LocalDateTime slot = it.next().getKey();
			if (slot.toLocalDate().equals(date))
				it.remove();
		}
		return true;
	}

	// 6a
	public boolean removeDateTime(LocalDateTime dateTime) {
		return freeSlots.remove(dateTime) != null;
	}

	// 6b
	public boolean removeWeekdayTime(DayOfWeek weekday, LocalTime time) {
		Iterator<Map.Entry<LocalDateTime, String>> it =
			freeSlots.entrySet().iterator();
		while (it.hasNext()) {
			LocalDateTime slot = it.next().getKey();
			if (slot.getDayOfWeek().equals(weekday) &&
					slot.toLocalTime().equals(time))
				it.remove();
		}
		return true;
	}

	// 6c
	public boolean removeDate(LocalDate date) {
		Iterator<Map.Entry<LocalDateTime, String>> it =
			freeSlots.entrySet().iterator();
		while (it.hasNext()) {
			LocalDateTime slot = it.next().getKey();
			if (slot.toLocalDate().equals(date))
				it.remove();
		}
		return true;
	}

	// 6d
	public boolean removeTime(LocalTime time) {
		Iterator<Map.Entry<LocalDateTime, String>> it =
			freeSlots.entrySet().iterator();
		while (it.hasNext()) {
			LocalDateTime slot = it.next().getKey();
			if (slot.toLocalTime().equals(time))
				it.remove();
		}
		return true;
	}

	// 7
	public boolean scheduleDateTime(LocalDateTime dateTime, String name) {
		if (freeSlots.remove(dateTime) != null) {
			if (reservedSlots.containsKey(dateTime))
				return false;
			else {
				reservedSlots.put(dateTime, name);
				return true;
			}
		} else
			return false;
	}

	// 8
	public boolean cancelDateTime(LocalDateTime dateTime) {
		if (reservedSlots.remove(dateTime) != null) {
			freeSlots.put(dateTime, "");
			return true;
		} else {
			return false;
		}
	}

	// 9
	public Iterator<Map.Entry<LocalDateTime, String>>
		getDailySchedule(LocalDate date) {
		SortedMap<LocalDateTime, String> slots = new TreeMap<>();
		slots.putAll(freeSlots.subMap(date.atStartOfDay(),
												date.plusDays(1).atStartOfDay()));
		slots.putAll(reservedSlots.subMap(	date.atStartOfDay(),
														date.plusDays(1).atStartOfDay()));
		return slots.entrySet().iterator();
	}

	// 10
	public Iterator<Map.Entry<LocalDateTime, String>>
		getMonthlySchedule(YearMonth yearMonth) {
		SortedMap<LocalDateTime, String> slots = new TreeMap<>();
		slots.putAll(freeSlots.subMap(atStartOfMonth(yearMonth),
												atStartOfMonth(yearMonth.plusMonths(1))));
		slots.putAll(reservedSlots
			.subMap(	atStartOfMonth(yearMonth),
						atStartOfMonth(yearMonth.plusMonths(1))));
		return slots.entrySet().iterator();
	}

	public Iterator<LocalDateTime> getFreeSlots() {
		return freeSlots.keySet().iterator();
	}

	public Iterator<Map.Entry<LocalDateTime, String>> getReservedSlots() {
		return reservedSlots.entrySet().iterator();
	}

	////////////////////////////////////////////////////////////////////////////
	// Helper
	private LocalDateTime atStartOfMonth(YearMonth yearMonth) {
		return yearMonth.atDay(1).atStartOfDay();
	}

	public static boolean validate() {
		return true;
	}
}
