/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functionality;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class GetCurrentDateTime {
    private static Date currentDate=new Date();
    public static String getTime(){
        SimpleDateFormat time=new SimpleDateFormat("h:mm");
        return time.format(currentDate);
    }
    public static String getDate(){
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        return date.format(currentDate);
    }
    public static String getClock(){
        SimpleDateFormat clock=new SimpleDateFormat("h:mm a");
        return clock.format(currentDate);
    }
    public static String getDay(){
        SimpleDateFormat day=new SimpleDateFormat("EEEE");
        return day.format(currentDate);
    }
    public static void main(String [] args){
        String clock=GetCurrentDateTime.getClock();
        String[] dummy=clock.split(" ");
        System.out.println("Time:"+dummy[0]+" \n"+dummy[1]);
        System.out.println("Date:"+getDate());
    }
    
}
