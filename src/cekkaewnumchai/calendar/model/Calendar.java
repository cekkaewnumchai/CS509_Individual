package cekkaewnumchai.calendar.model;

import java.util.Set;

class Calendar {
   String name;
   Set<Date> timeslots;

   // empty calendar
   public Calendar(String name, Date startDate, Date endDate, Time earlyHour, 
   Time latestHour, int duration) {
      this.name = name;
      
   }
}