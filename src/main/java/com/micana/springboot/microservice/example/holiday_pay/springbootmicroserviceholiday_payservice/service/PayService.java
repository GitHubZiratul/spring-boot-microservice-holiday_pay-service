package com.micana.springboot.microservice.example.holiday_pay.springbootmicroserviceholiday_payservice.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class PayService {

    public int getPay(String vacationStart,String vacationEnd,int money)throws ParseException {
        int days=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = simpleDateFormat.parse(vacationStart);
        Date endDate = simpleDateFormat.parse(vacationEnd);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DAY_OF_MONTH,1); //Небольшой костыль , т.к. цикл не считает последнюю дату отпуска

        while (start.getTime().before(end.getTime())){
            if (isHoliday(start)|| isWeekenday(start)){
                start.add(Calendar.DAY_OF_MONTH,1);
            } else {
                days++;
                start.add(Calendar.DAY_OF_MONTH,1);
            }
        }
        return days*(money/21);
    }
    public static boolean isWeekenday(Calendar calendar) { //Функция на проверку выходных
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1; //Воскресенье =1 Суббота=7 , для удобности вычел 1
        if (week == 0 || week == 6) { //Проверка выходной ли это день
            return true;

        }
        return false;
    }

    public static  boolean isHoliday (Calendar calendar){ //Функция для проверки является ли день праздником
        String[] holidayList={"0101","0201", "0301","0401" ,"0501","0601","0701","0801","2302","0803","0105","0905","1206","0411"}; //Массив строк с праздниками не указывал года т.к. в целом для всех годов праздники одинаковы
        String dateString = new SimpleDateFormat("ddMM").format(calendar.getTime());
        for (String day:holidayList){ //Проверка  являяется ли переданная дата праздником
            if (dateString.equals(day)) {
                return true;
            }
        }
        return false;
    }
    }
