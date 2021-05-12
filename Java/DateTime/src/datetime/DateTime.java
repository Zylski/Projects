package datetime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

/*
 * Programmer: Damian Zylski
 * Project:    DateTime
 * Date:       03/14/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To play with the date time api
 */
public class DateTime
{
//*dateTime************************************************************************
    //practices with date time functions
    public static boolean dateTime()
    {
        boolean testsPassed = true;
        
        try{
        //1.)
        //Which class would you use to store your birthday in years, months, days, seconds, and nanoseconds
        //Use LocalDateTime
        LocalDateTime t = LocalDateTime.of(1989,7,10,5,5,5);
        
        //2.)
        //Given a random date, how would you find the date of the previous Thursday?
        //Use previous method of temporal adjuster
        LocalDate d = LocalDate.of(2020, 3, 15);
        System.out.println(d.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY)));
        
        //3.)
        //What is the difference between a ZoneId and a ZoneOffset?
        //They both track the offset from a Greenwich/UTC time but ZoneOffSet is absolute. ZoneID uses ZoneRules. ZoneOffSet extend zoneID
        ZoneOffset z = ZoneOffset.UTC;
        ZoneId z1 = ZoneId.systemDefault();
        System.out.println(z1.toString());
        
        //4.)
        //How would you convert an Instant to a ZonedDateTime? How would you convert a ZonedDateTime to an Instant?
        ZonedDateTime zd = ZonedDateTime.now();
        Instant ins = Instant.now();
        zd = ZonedDateTime.ofInstant(ins, z1);
        ins = zd.toInstant();
        System.out.println(ins);
        
        //5.)
        //Write an example that, for a given year, reports the length of each month within that year.
        int days = 0;
        for(int i = 1; i < 13; i++)
        {
            days = YearMonth.of(2020, i).lengthOfMonth();
            System.out.println(days);
        }
        
        //6.)
        //Write an example that, for a given month of the current year, lists all of the Mondays in that month.
        LocalDate ld = LocalDate.of(2021,2,1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        while(ld.getMonthValue() == 2)
        {    
            if(ld.getDayOfWeek().getValue() == 1)
            {
                System.out.println(ld);
            }
            ld = ld.plusDays(1);
        }
        
        //7.
        //Write an example that tests whether a given date occurs on Friday the 13th.
        LocalDate ld2 = LocalDate.of(2021, Month.MARCH, 13);
        boolean friday13 = false;
        if(ld2.getDayOfWeek().getValue() == 5)
        {
            friday13 = true;
        }
        else
        {
            friday13 = false;
        }
        System.out.println(friday13);
        }
        catch(NumberFormatException | DateTimeException e)
        {
            testsPassed = false;
            e.printStackTrace();
        }
        

        return testsPassed;      
    }
//*main************************************************************************    
    public static void main(String[] args)
    {
        //run date time
        dateTime();
    }
    
}
