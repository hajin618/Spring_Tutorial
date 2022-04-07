package com.example.project1.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private userDao service;


    // 생성자 이용
    public UserController(userDao service){
        this.service=service;
    }


    // 전체 사용자 목록 조회 (GET)
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    // 사용자 한명 조회 (GET) : id 값은 String으로 전달, int로 설정하면 자동으로 변환
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        // return service.findOne(id);

        /* 위의 코드 한줄 -> 두줄로 변경 : findOne - refactor - Introduce Variable
        User user = service.findOne(id);
        return user;
        */

        User user = service.findOne(id);

        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

/*
        // HATEOAS
        // 매개변수는 위에서 검색된 user 객체
        Resource<User> resource=new Resouce<>(user);
        Controller LinkBuilder linkTo = ControllerLinkBuilder.linkTo(
                // retrieveAllUsers 메소드의 링크 추가
                ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        // 위에서 생성한 resource 객체에 링크 추가
        resource.add(linkTo.withRel("all-users"));

        // 반환값은 resource
        return resource;

        // public Resource<User> retrieveUser(@PathVariable int id) 로 수정하기

        // : 전체 사용자 목록 보기로 돌아가는 all-users(링크) 추가됨

 */
       return user;
    }


    // 사용자 추가
    @PostMapping("/users")
    // @RequestBody : 클라이언트로부터 json, xml 등 오브젝트 형태의 데이터 받기 위해서 필요
    // 만들어진 uri를 리턴하므로 void 아닌 ResponseEntity<User>
    // @Valid 이용해 데이터 유효성 검증
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser=service.save(user);

        // 사용자에게 특정 값을 포함한 URI 전달 가능     // 현재 요청된 request값 사용
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                // buildAndExpand를 통해 얻은 값 들어옴
                .path("/{id}")
                // {id}에 넣어줄 값
                .buildAndExpand(savedUser.getId())
                // uri 생성 (만들어진 값 uri 형태로 변환)
                .toUri();

        return ResponseEntity.created(location).build();

        // 새로운 사용자 생성하면 상태 코드에 200 OK 가 아닌 201 Created
        // 서버로부터 요청 결과값에 적절한 상태 코드를 반환시켜주는 것이 좋음
    }


    // 사용자 삭제
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
