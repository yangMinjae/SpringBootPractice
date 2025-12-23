package com.jay.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class BasicController {
//    @GetMapping("/")
//    // @ResponseBody        // return에 있는 문자를 그대로 보내라
//    String function(){
//        return "index.html";    // static 폴더가 기본 경로
//    }
//
//    @GetMapping("/2")
//    @ResponseBody
//    String function2(){
//        return "안녕하세요2";
//    }
//    @GetMapping("/about")
//    @ResponseBody
//    String function3(){
//        return "aboutabout";
//    }
//    @GetMapping("/getHTML")
//    @ResponseBody
//    String function4(){
//        return "<h4>하하하하</h4>";
//    }
//
//    @GetMapping("/date")
//    @ResponseBody
//    String function5(){
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return now.format(formatter);
//    }
}
