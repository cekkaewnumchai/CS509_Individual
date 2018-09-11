package cekkaewnumchai.calendar.model;

public enum Month {
   JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

   static Month getMonthFromId(int id) {
      return Month.values()[id - 1];
   }

   public static String fullName(Month month) {
      switch(month) {
         case JAN: return "January";
         case FEB: return "February";
         case MAR: return "March";
         case APR: return "April";
         case MAY: return "May";
         case JUN: return "June";
         case JUL: return "July";
         case AUG: return "August";
         case SEP: return "September";
         case OCT: return "October";
         case NOV: return "November";
         case DEC: return "December";
         default: return "";
      }
   }
   
   public static String shortName(Month month) {
      switch(month) {
         case JAN: return "Jan";
         case FEB: return "Feb";
         case MAR: return "Mar";
         case APR: return "Apr";
         case MAY: return "May";
         case JUN: return "Jun";
         case JUL: return "Jul";
         case AUG: return "Aug";
         case SEP: return "Sep";
         case OCT: return "Oct";
         case NOV: return "Nov";
         case DEC: return "Dec";
         default: return "";
      }
   }

   @Override
   public String toString(){
      return Month.fullName(this);
   }
}