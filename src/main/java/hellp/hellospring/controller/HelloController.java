package hellp.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *     컨트롤러 - 웹 어플리케이션 첫번째 진입점이 컨트롤러
 */
@Controller
public class HelloController {

    // @GetMapping -> GetMethod
    // @PostMapping -> PostMethod
    @GetMapping("hello") // -> 웹 어플리케이션 /hello라고 들어오면 이 method , hello url 매칭
    public String hello(Model model) {
        // 스프링이 model이란걸 만들어서 넘겨 줌
        model.addAttribute("data", "hello!!");
        // key data, 값은 hello
        return "hello";
        // resources에 templates의 hello를 찾는다 -> 찾아서 렌더링을 한다. (타임리프 템플릿 엔진 처리)
    }

    // 좀더 내용있는 컨트롤러 작성시작
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // @RequestParam -> 외부에서 파라미터 받아옴
        // required -> true 기본으로 값을 넘겨하나다
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // -> html에 나온 body 태그 X, http에서 header와 body가 잇는데 body에 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody // -> 객체가 오면 기본 default json 방식으로 만들어서 http에 응답하겠다
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 반환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
