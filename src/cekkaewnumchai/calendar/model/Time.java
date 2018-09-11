package cekkaewnumchai.calendar.model;

class Time {
   int hr, min;

   Time(int hr, int min) {
      if(Time.verify(hr, min)) {
         this.hr = hr;
         this.min = min;
      } else {
         // set to zero (or throw error?) 
         this.hr = 0;
         this.min = 0;
      }
   }

   private static boolean verify(int hr, int min){
      return 0 <= hr && hr <= 23 && 0 <= min && min < 60;  
   }

   public int getHour(){
      return hr;
   }

   public int getMinute() {
      return min;
   }
}