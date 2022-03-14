package jpabook.jpashop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //lombok으로 로그찍기
public class HomeController {

    @RequestMapping("/")
    public  String home(){
        log.info("home controller");
        return "home"; //이렇게하면 home.html로 찾아가서 타임리프가 파일을 찾아가게됨
   }
}
