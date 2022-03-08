package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")  //hello라는 것이  url로 오면 이 컨트롤러가 호출된다
    public String hello(Model model) {
         model.addAttribute("data","hello!!"); //값을 담곘다
         return "hello";   //hello.html 화면랜더링
    }
}
