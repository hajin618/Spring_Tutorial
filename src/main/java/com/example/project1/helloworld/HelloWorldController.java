package com.example.project1.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/*
   RestController 사용하면 반환하고자 하는 데이터 값을 ResponseBody에 저장하지 않더라도
   자동으로 json 형태로 반환
*/
@RestController
public class HelloWorldController {

    // annotation을 통한 의존성 주입
    @Autowired
    private MessageSource messageSource;

    // GET
    // /hello-world (endpoint)
    // 기존 방식은 @RequestMapping(method=RequestMethod.GET, path="/hello-world")

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    // 단축키 alt + enter : 자동 생성
    /*
       반환하고자 하는 값이 String 형태가 아닌 java bean 형태일 때 스프링은
       값을 단순 text, object 형태가 아닌 json 형태로 변환해서 반환
        - xml 형태로 받고 싶다면 라이브러리 추가
    */
    //
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }


    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }


    // 다국어 처리 (resources) : 기본은 한국어, Headers - KEY 값에 Accept-Language,
    //                          VALUE 값에 fr 속성 넣으면 Bonjour, en 넣으면 Hello 출력
    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
