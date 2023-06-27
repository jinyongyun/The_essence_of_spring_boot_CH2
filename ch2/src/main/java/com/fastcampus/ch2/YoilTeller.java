package com.fastcampus.ch2;

//연월일을 입력하면 요일을 알려주는 원격 프로그램

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Calendar;

@Controller
public class YoilTeller {
    @RequestMapping("/getYoil")
    public String main(@ModelAttribute("myDate") MyDate myDate, Model model) throws IOException {
        //@ModelAttribute 사용으로 myDate 객체를 model의 Attribute로 저장

        return "yoilview"; //yoilview.html - 뷰의 이름을 반환


    }
@ModelAttribute("yoil")
    private char getYoil(MyDate myDate) {
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear(); // cal의 모든 필드를 초기화 - 정확한 계산을 위해
        cal.set(myDate.getYear(), myDate.getMonth()-1, myDate.getDay()); // 월(mm)은 0부터 11이기 때문에 1을 빼줘야

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1~7을 반환. 1: 일요일 2:월요일 3:화요일 ...
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // 배열이니까 1 빼기 (1~7이니까) 1~7 -> 0~6
        return yoil;
    }
}
