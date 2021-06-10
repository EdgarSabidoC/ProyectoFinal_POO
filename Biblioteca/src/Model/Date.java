package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Carlos Antonio Ruiz
 */
public class Date {
    LocalDate date;

    public Date() {
       setDate(LocalDate.now());    
    }
    
    public Date(int day, int month, int year) {
        if(!isDate(day, month, year)){
            
            setDate(LocalDate.now());
            
            return;
        }
        setDate(LocalDate.of(year, month, day));
    }
    
    private void setDate(LocalDate date) {
        this.date = date;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        this.date.format(dateFormat);
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void printDate() {
        System.out.println(date);
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
    
    
    public void changeDate(int day, int month, int year) {
        if(!isDate(day, month, year)){
            return;
        }
        setDate(LocalDate.of(year, month, day));
    }
}
