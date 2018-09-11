package cekkaewnumchai.calendar.model;

class Date {
   Time time;
   Day weekday;
   Month month;
   int day, year;

   // string parsing; format should be DD/MM/YYYY@HH:MM
   Date(String dateString) {
      // whole
      String[] data = dateString.split("@"); 
      String dateData = data[0];
      String timeData = data[1];
      // date
      String[] date = dateData.split("/");
      this.day = Integer.parseInt(date[0]);
      this.month = Month.getMonthFromId(Integer.parseInt(date[1]));
      this.year = Integer.parseInt(date[2]);
      // time
      String[] time = timeData.split(":");
      this.time = new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
   }
}