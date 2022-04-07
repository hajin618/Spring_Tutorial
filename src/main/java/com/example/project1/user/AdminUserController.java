package com.example.project1.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")   // 공통적인 부분
public class AdminUserController {
    private userDao service;


    // 생성자 이용
    public AdminUserController(userDao service){
        this.service=service;
    }


    // 전체 사용자 목록 조회 (GET)
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){

        // return service.findAll(); : refactor -> introduce variable

        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }


    // GET /admin/users/1 -> /admin/v1/users/1 : 버전 관리
    // Version1
    // @GetMapping("/v1/users/{id}")
    // @GetMapping(value="/users/{id}/", params="version=1")           // Request Parameter를 이용하여 버전관리하는 방법
    // @GetMapping(value="/users/{id}", headers = "X-API-VERSION=1")   // Header를 이용하는 방법
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){

        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

    // Version2
    // @GetMapping("/v2/users/{id}")
    // @GetMapping(value="/users/{id}/", params="version=2")           // Request Parameter를 이용하여 버전관리하는 방법
    // @GetMapping(value="/users/{id}", headers = "X-API-VERSION=2")   // Header를 이용하는 방법
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){

        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        // 반환받았던 user 데이터 값을 user2로 변환
        // User -> User2
        UserV2 userV2 = new UserV2();
        // BeanUtils : 스프링 프레임워크에서 제공해주는 util class. Bean 들 간 작업들 도와줌
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }

}
