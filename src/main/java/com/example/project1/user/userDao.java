package com.example.project1.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class userDao {
    private static List<User> users = new ArrayList<>();

    private static int usersCount=3;

    // 초기값 생성
    static{
        users.add(new User(1, "Kenneth", new Date(), "pass1", "990909-111111"));
        users.add(new User(2, "Alice", new Date(),"pass2", "990909-222222"));
        users.add(new User(3, "Elena", new Date(),"pass3", "990909-3333333"));
    }


    // 전체 사용자 조회
    public List<User> findAll(){
        return users;
    }


    // 사용자 추가
    public User save(User user){
        if (user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }


    // 사용자 개별 데이터 반환
    public User findOne(int id){
        for(User user:users){
            // lombok을 사용했기 때문에 정의하지 않은 getId 사용 가능
            if(user.getId()==id){
                return user;
            }
        }
        return null;    // 일치하는 id가 없으면 null 반환
    }


    // 사용자 삭제
    public User deleteById(int id){
        // iterator : 배열이나 리스트 등 자료 구조를 순회할 수 있게 해주는 객체
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        // 데이터를 찾지 못한 경우 null 반환
        return null;
    }
}
