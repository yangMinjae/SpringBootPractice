package com.jay.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String function(){
        return "안녕하세요";
    }

    @GetMapping("/2")
    @ResponseBody
    String function2(){
        return "안녕하세요2";
    }
    @GetMapping("/about")
    @ResponseBody
    String function3(){
        return "aboutabout";
    }
    @GetMapping("/getHTML")
    @ResponseBody
    String function4(){
        return "<h4>하하하하</h4>";
    }
}
