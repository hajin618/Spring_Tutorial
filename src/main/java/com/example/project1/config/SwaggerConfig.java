package com.example.project1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
* localhost:8088/v2/api-docs : swagger file에 의해 만들어져 있는 내용이 json데이터 타입으로 보여짐
* localhost:8088/swagger-ui/index.html : html 형태로 나타남
*   : 생성한 controller에 대한 정보(필요한 파라미터, 반환값 등) 나타남
* */
@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    /*
    // contact, api info 모두 한 번 고정이 되면 변경할 필요가 없는 정보들이기 때문에 상수로 만듦

    // 사용자 정보를 위한 contact 객체 생성
    private static final Contact DEFAULT_CONTACT=new Contact("KHJ",
            "http://www.naver.com", // contact URL
            "khj@naver.com");       // email

    private static final ApiInfo DEFAULT_API_INFO=new ApiInfo(
            "Awesome API Title",                        // title
            "My User Management REST API service",      // description
            "1.0",                                      // version
            "urn:tos",                                  // uniform resource name
            DEFAULT_CONTACT,
            "Apache 2.0",                               // license
            "http://www.apache.org/license/LICENSE-2.0",// license url
            new ArrayList<>());                         // licens 관련 추가정보

    // 어떤 형태로 데이터를 사용할 수 있는지 문서 타입 지정
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            // asList : 배열 형태를 리스트로
            Array.asList("application/json", "application/xml"));

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(DEFAULT_API_INFO)
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
    */

}
