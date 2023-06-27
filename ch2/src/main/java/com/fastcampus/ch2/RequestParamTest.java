package com.fastcampus.ch2;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestParamTest {
    @RequestMapping("/requestParam")
    public String main(HttpServletRequest request) {
        String year = request.getParameter("year");
//		http://localhost/requestParam         ---->> year=null  아예 안쓰면 null
//		http://localhost/requestParam?year=   ---->> year=""    =까지 썼는데 값을 안쓰면 빈문자열
//		http://localhost/requestParam?year    ---->> year=""    =도 안쓰고 year까지만 쓰면 빈문자열
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam2")
//	public String main2(@RequestParam(name="year", required=false) String year) {   // 아래와 동일 생략가능
    public String main2(String year) { //year를 매개변수로 바로 받아와서  http://localhost/requestParam         ---->> year=null  이거 없음
//		http://localhost/requestParam2         ---->> year=null
//		http://localhost/requestParam2?year    ---->> year=""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam3")
//		public String main3(@RequestParam(name="year", required=true) String year) {   // 아래와 동일
    public String main3(@RequestParam String year) { //이렇게 쓰다 말면 required가 true, 즉 필수 요소
//		http://localhost/requestParam3         ---->> year=null   400 Bad Request. required=true라서
//		http://localhost/requestParam3?year    ---->> year=""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam4")
    public String main4(@RequestParam(required=false) String year) {
//		http://localhost/requestParam4         ---->> year=null
//		http://localhost/requestParam4?year    ---->> year=""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam5")
    public String main5(@RequestParam(required=false, defaultValue="1") String year) {
//		http://localhost/requestParam5         ---->> year=1
//		http://localhost/requestParam5?year    ---->> year=1
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

// =======================================================================

    @RequestMapping("/requestParam6")
//	public String main6(@RequestParam(required=false) int year) {
    public String main6(int year) {
//		http://localhost/requestParam6        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
//		http://localhost/requestParam6?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam7")
    public String main7(@RequestParam int year) {
//		http://localhost/requestParam7        ---->> 400 Bad Request, Required int parameter 'year' is not present
//		http://localhost/requestParam7?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam8")
    public String main8(@RequestParam(required=false) int year) {
        //	http://localhost/requestParam8        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
        //	http://localhost/requestParam8?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam9")
    public String main9(@RequestParam(required=true) int year) {
        //	http://localhost/requestParam9        ---->> 400 Bad Request, Required int parameter 'year' is not present
        //	http://localhost/requestParam9?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam10")
    public String main10(@RequestParam(required=true, defaultValue="1") int year) {
        //	http://localhost/requestParam10        ---->> year=1
        //	http://localhost/requestParam10?year   ---->> year=1
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }

    @RequestMapping("/requestParam11")
    public String main11(@RequestParam(required=false, defaultValue="1") int year) {
//		http://localhost/requestParam11        ---->> year=1
//		http://localhost/requestParam11?year   ---->> year=1
        System.out.printf("[%s]year=[%s]%n", new Date(), year);
        return "yoil";
    }
} // class