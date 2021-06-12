package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Carlos Antonio Ruiz
 */
public class Date {
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;

    public Date() {
       setDate(LocalDate.now());    
    }
    
    public Date(int day, int month, int year) {
        if(!isDate(day, month, year)){
            
            setDate(LocalDate.now());
            setTime(LocalTime.now());
            setDateTime(LocalDateTime.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth(), this.time.getHour(), this.time.getMinute(), this.time.getSecond()));
            return;
        }
        setDate(LocalDate.of(year, month, day));
        setTime(LocalTime.now());
        setDateTime(LocalDateTime.of(year, month, day, this.time.getHour(), this.time.getMinute(), this.time.getSecond()));
    }
    
    public Date(int day, int month, int year, int hour, int minute, int second) {
        if(!isDate(day, month, year)){
            
            setDate(LocalDate.now());
            setTime(LocalTime.now());
            setDateTime(LocalDateTime.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth(), this.time.getHour(), this.time.getMinute(), this.time.getSecond()));
            
            return;
        }
        if(!isTime(hour, minute, second)) {
            setDate(LocalDate.now());
            setTime(LocalTime.now());
            setDateTime(LocalDateTime.of(this.date.getYear(), this.date.getMonth(), this.date.getDayOfMonth(), this.time.getHour(), this.time.getMinute(), this.time.getSecond()));
            
            return;
        }
        setDate(LocalDate.of(year, month, day));
        setTime(LocalTime.of(hour, minute, second));
        setDateTime(LocalDateTime.of(year, month, day, hour, minute, second));
    }
    
    private void setDate(LocalDate date) {
        this.date = date;
    }
    
    private void setTime(LocalTime time) {
        this.time = time;
    }
    
    private void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public String getDateS() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateF = this.date.format(dateFormat);
        return dateF;
    }

    public String getTimeS() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeF = this.time.format(dateFormat);
        return timeF;
    }
    
    public String getDateAndTime() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateTimeF = this.dateTime.format(dateFormat);
        return dateTimeF;
    }

    public void printDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateF = this.date.format(dateFormat);
        System.out.println(dateF);
    }
    
    public void printTime() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeF = this.time.format(dateFormat);
        System.out.println(timeF);
    }
    

    public void printDateAndTime() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateTimeF = this.dateTime.format(dateFormat);
        System.out.println(dateTimeF);
    }
    
    private boolean isDay(int day) {
        return !(day < 1 || day > 31);
    }
    
    private boolean isMonth(int month) {
        return !(month < 1 || month > 12);
    }
    
    private boolean isYear(int year) {
        return !(year < 1 || year > 2147483647);
    }
    
    private boolean isHour(int hour) {
        return !(hour < 0 || hour > 23);
    }
    
    private boolean isMinute(int minute) {
        return !(minute < 0 || minute > 59);
    }
    
    private boolean isSecond(int second) {
        return !(second < 0 || second > 59);
    }
    
    private boolean isLeapYear(int year) {
        if(year%4 == 0) {
            if (year%100 == 0) {
                return year%400 == 0;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    
    private int daysOfMonth(int month, int year){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        else if((month == 2) && isLeapYear(year)) {
            return 29;
        }
        else if(month == 2) {
            return 28;
        }
        else {
            return 30;
        }
    }
    
    private boolean isDate(int day, int month, int year) {
        if(!isDay(day)) {
            return false;
        }
        if(!isMonth(month)) {
            return false;
        }
        if(!isYear(year)) {
            return false;
        }
        return day <= daysOfMonth(month, year);
    }
    
    private boolean isTime(int hour, int minute, int second) {
        if(!isHour(hour)) {
            return false;
        }
        if(!isMinute(minute)) {
            return false;
        }
        return isSecond(second);
    }
    
    
    public void changeDate(int day, int month, int year) {
        if(!isDate(day, month, year)){
            return;
        }
        setDate(LocalDate.of(year, month, day));
    }
}
