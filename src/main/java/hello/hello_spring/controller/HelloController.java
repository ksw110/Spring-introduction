package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //html 바디 부분에 직적 넣어주는 것
    public  String helloString(@RequestParam("name") String name){
        return  "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody //http의 bady에 직접입력됨 json 형태 자료형 가짐
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello= new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }


}
