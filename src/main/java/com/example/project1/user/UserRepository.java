
package com.example.project1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository     // 빈의 형태
// jpaRepository 상속 <user, 기본키 자료형>
// 이 선언만으로 crud 관련 메소드 사용 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    // JpaRepository 이외의 추가적인 메소드 필요하거나 재정의 필요하면 여기에 메서드 선언, 정의하여 사용 가능
}