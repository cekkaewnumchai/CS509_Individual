package cekkaewnumchai.calendar.model;

import java.util.List;
import java.util.ArrayList;

class Date {
   Day weekday;
   Month month;
   int day, year;

   // string parsing; format should be DD/MM/YYYY
   // this does not have checking
   Date(String dateString) {
      String[] date = dateString.split("/");
      this.day = Integer.parseInt(date[0]);
      this.month = Month.getMonthFromId(Integer.parseInt(date[1]));
      this.year = Integer.parseInt(date[2]);
      // TODO: add how to determine weekday
   }

   Iterable<Date> to(Date toDate) {
      List<Date> dates = new ArrayList<Date>();

      return dates;
   }
}