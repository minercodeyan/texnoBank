package com.example.danitexnobank.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Service
public class TimerService {
    public Date endDateMethod(int term){
        LocalDateTime localDateTime = LocalDateTime.now();
        Calendar calendar = new GregorianCalendar(localDateTime.getYear(),
                localDateTime.getMonth().getValue()+term-1,
                localDateTime.getDayOfMonth());
        Date date = calendar.getTime();
        return date;
    }
}
