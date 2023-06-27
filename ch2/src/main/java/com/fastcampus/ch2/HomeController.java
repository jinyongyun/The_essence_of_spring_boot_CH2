package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//1.원격 프로그램 등록
//2.URL과 메서드 연결
@Controller
public class HomeController {//원격 프로그램
    @RequestMapping("/")
    public String main(){
        return "index"; // templates/index.html
    }

    @GetMapping("/test")
    public String test(Model model, HttpServletRequest request){
        request.setAttribute("year",2022);
        HttpSession session = request.getSession();
        session.setAttribute("id","asdf");
        ServletContext application = session.getServletContext();
        application.setAttribute("email","service@jinyongyun.com");

       model.addAttribute("lastName", "JINYONG");
       model.addAttribute("firstName","YUN");
       model.addAttribute("list", Arrays.asList("aaa","bbb","ccc","ddd","eee"));
       model.addAttribute("bno",123);
       model.addAttribute("user",new User("aaa", 20));
        return "test"; // templates/test.html
    }
}
