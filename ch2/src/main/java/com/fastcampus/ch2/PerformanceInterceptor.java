package com.fastcampus.ch2;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import static jdk.internal.module.ModuleBootstrap.Counters.startTime;

@Component
public class PerformanceInterceptor implements HandlerInterceptor { // 단일 책임의 원칙
  //long startTime; //메소드 분리에 의해 인스턴스 변수로 빼주는데, 주의해야 할 점은 싱글톤이라서 여러 스레드가 하나의 객체를 공유->race condition발생 가능
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 전처리 작업
       long startTime = System.currentTimeMillis();
       request.setAttribute("startTime", startTime); //request객체에 startTime 저장
        //handler - 요청하고 연결된 컨트롤러의 메소드
        //이 핸들러라는 매개변수를 통해 메서드의 정보를 얻을 수 있다.
        HandlerMethod method = (HandlerMethod) handler;
        System.out.println("method.getMethod() = " + method.getMethod()); // URL하고 연결된 메서드
        System.out.println("method.getBean() = " + method.getBean());// 메서드가 포함된 컨트롤러

        // return true; //다음 인터셉터나 컨트롤러를 호출 false면 호출 안 함
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       //2.후처리 작업
        long startTime = (long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.print("[" + ((HttpServletRequest)request).getRequestURI() + "]");
        System.out.println("time=" + (endTime-startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
