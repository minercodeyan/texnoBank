package com.example.danitexnobank.Service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimerService {

    public Date endDateMethod(int term){
        long endDateTime = new Date().getTime()+term;
        Date endDate=new Date(endDateTime);
        return endDate;
    }
}
