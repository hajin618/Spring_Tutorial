package com.example.project1.helloworld;

// lombok : setter ,getter, toString, equals 같은 메서드 자동 생성 : 개발 시간 단축

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //message 사용할 수 있는 생성자 만들어줌
// @NoArgsConstructor : 매개변수가 없는 default 생성자 만들 때
public class HelloWorldBean {
    private String message;

    /*
    // 아래와 같이 getter, setter 추가할 필요 x
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String msg){
        this.message=msg;
    }
    */

    /*
    @AllArgsConstructor 로 자동 생성됨
    public HelloWorldBean(String message){
        this.message=message;
    }*/


}
