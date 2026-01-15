package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // hello.html 으로 감, hello 가 ViewName 이다
        // 'resources:templates/' +{ViewName}+ '.html'   <- 경로
        // viewResolver 가 변환함
    }

    @GetMapping("hello-mvc")
    // MVC 방식 : 서버가 화면을 만들어준다
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // @ResponseBody : 서버는 데이터만 준다
    // View(html)를 거치지 않고 반환값을 HTTP Response Body에 직접 넣는다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // Hello : 객체
    public Hello  helloApi(@RequestParam("name") String name) {

        // 자동완성 : ctrl + shift + Enter
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // 단축키 Alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
