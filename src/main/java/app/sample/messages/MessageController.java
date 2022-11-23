package app.sample.messages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/messages")

public class MessageController {

    @GetMapping("/welcome")
    //리턴 값을 Http 응답본문으로 처리한다는 뜻 -> 타임리프로 대체
    //: 스프링이 핸들러 반환 값을 뷰 이름으로 사용해 타임리프로 응답 생성
    //@ResponseBody
    //public String welcome(Model model){
    public ModelAndView welcome(){
        //1. 볼드체 직접 사용
        //return "<strong>Hello, Welcome to Spring Boot!</strong>";

        //2. Model 인스턴스를 이용해 String 반환
        //메소드에 스프링이 생선하는 Model 인스턴스 전달 -> 템플릿의 키와 일치하는 메시지를 속성으로 추가
        //model.addAttribute("message", "Hello, Welcome to Spring Boot!");

        //return "welcome";

        //ModelAndView 인스턴스 생성
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("message", "Hello, Welcome to Spring Boot!");


        return mv;
    }

}
