package cekkaewnumchai.calendar.model;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

public class CalendarManagementSystem {
	private SortedMap<String, Calendar> calendars;

	public CalendarManagementSystem() {
		calendars = new TreeMap<>();
	}

	public static CalendarManagementSystem parseFromFile(String path) {
		CalendarManagementSystem cms = new CalendarManagementSystem();
		try {
			JsonParser parser = Json.createParser(new FileInputStream(path));

			// START_OBJECT
			if (!parser.next().equals(JsonParser.Event.START_OBJECT))
				throw new Exception();

			// KEY_NAME
			if (!parser.next().equals(JsonParser.Event.KEY_NAME))
				throw new Exception();

			// START_ARRAY
			if (!parser.next().equals(JsonParser.Event.START_ARRAY))
				throw new Exception();

			// read calendar
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			DateTimeFormatter dateFormatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
			while (!parser.next().equals(JsonParser.Event.END_ARRAY)) {
				parser.next(); // KEY_NAME
				parser.next(); // VALUE_STRING
				String name = parser.getString();

				parser.next(); // KEY_NAME
				parser.next(); // VALUE_STRING
				LocalTime startTime =
					LocalTime.parse(parser.getString(), timeFormatter);

				parser.next(); // KEY_NAME
				parser.next(); // VALUE_STRING
				LocalTime endTime =
					LocalTime.parse(parser.getString(), timeFormatter);

				parser.next(); // KEY_NAME
				parser.next(); // VALUE_NUMBER
				int duration = parser.getInt();

				parser.next(); // KEY_NAME
				parser.next(); // KEY_NAME
				SortedMap<LocalDateTime, String> freeSlots = new TreeMap<>();
				while (!parser.next().equals(JsonParser.Event.END_ARRAY)) { // VALUE_STRING
					freeSlots
						.put(	LocalDateTime.parse(parser.getString(), dateFormatter),
								"");
				}

				parser.next(); // KEY_NAME
				parser.next(); // START_ARRAY
				SortedMap<LocalDateTime, String> reservedSlots = new TreeMap<>();
				while (!parser.next().equals(JsonParser.Event.END_ARRAY)) { // START_OBJECT
					parser.next(); // KEY_NAME
					parser.next(); // VALUE_STRING
					String meetingName = parser.getString();

					parser.next(); // KEY_NAME
					parser.next(); // VALUE_STRING
					reservedSlots
						.put(	LocalDateTime.parse(parser.getString(), dateFormatter),
								meetingName);

					parser.next(); // END_OBJECT
				}
				
				// add read values to calendar
				cms.createCalendar(	name, startTime, endTime, duration, freeSlots,
											reservedSlots);
				
				parser.next(); // END_OBJECT
			}

		} catch (Exception e) {
			cms = new CalendarManagementSystem();
		}
		
		return cms;
	}

	public void writeToFile(String path) {
		FileWriter writer;
		try {
			writer = new FileWriter(path);

			Map<String, Object> properties = new HashMap<>(1);
			properties.put(JsonGenerator.PRETTY_PRINTING, true);

			JsonGenerator gen = Json.createGeneratorFactory(properties)
				.createGenerator(writer)
				.writeStartObject()
				.write("calendars", genJsonValue())
				.writeEnd();
			gen.flush();

			// add new line to the end of text file
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			
		}
	}

	private JsonValue genJsonValue() {
		JsonArrayBuilder calendarBuild = Json.createArrayBuilder();
		Iterator<Map.Entry<String, Calendar>> it =
			calendars.entrySet().iterator();
		while (it.hasNext()) {
			calendarBuild.add(it.next().getValue().genJsonValue());
		}
		return calendarBuild.build();
	}

	// 1
	public boolean createCalendar(String name,
											LocalDate startDate,
											LocalDate endDate,
											int earlyHour,
											int latestHour,
											int duration) {
		if (calendars.containsKey(name))
			return false;

		calendars.put(name, new Calendar(name, startDate, endDate, earlyHour,
													latestHour, duration));
		return true;
	}

	public boolean
		createCalendar(String name,
							LocalTime startTime,
							LocalTime endTime,
							int duration,
							SortedMap<LocalDateTime, String> freeSlots,
							SortedMap<LocalDateTime, String> reservedSlots) {
		if (calendars.containsKey(name))
			return false;

		calendars.put(name, new Calendar(name, startTime, endTime, duration,
													freeSlots, reservedSlots));
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
