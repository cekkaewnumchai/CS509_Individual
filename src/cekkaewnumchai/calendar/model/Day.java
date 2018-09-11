package cekkaewnumchai.calendar.model;

public enum Day {
   MON, TUE, WED, THU, FRI, SAT, SUN;

   static Day getDayFromId(int id) {
      return Day.values()[id - 1];
   }
   public static String fullName(Day day){
      switch(day) {
         case MON: return "Monday";
         case TUE: return "Tuesday";
         case WED: return "Wednesday";
         case THU: return "Thursday";
         case FRI: return "Friday";
         case SAT: return "Saturday";
         case SUN: return "Sunday";
         default: return "";
      }
   }

   public static String shortName(Day day){
      switch(day) {
         case MON: return "Mon";
         case TUE: return "Tue";
         case WED: return "Wed";
         case THU: return "Thu";
         case FRI: return "Fri";
         case SAT: return "Sat";
         case SUN: return "Sun";
         default: return "";
      }
   }

   @Override
   public String toString(){
      return Day.fullName(this);
   }
}