package com.example.project1.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
// @JsonIgnoreProperties(value={"password", "ssn"})
@NoArgsConstructor  // default 생성자 생성
//@JsonFilter("UserInfo")
//@ApiModel(description="사용자 상세 정보를 위한 도메인 객체")
@Entity     // 클래스 선언 위에 entity 어노테이션 추가 :
            // 해당하는 클래스의 이름으로 데이터베이스 테이블 생성, 필드의 정보를 가지고 컬럼 생성
public class User {
    @Id                 // id 기본키 설정
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name 은 2글자 이상 입력")    //최소 사이즈 2
    //@ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
    private String name;

    @Past   // 과거 Date만 사용 가능
    //@ApiModelProperty(notes="사용자 등록일을 입력해 주세요.")
    private Date joinDate;

    // @JsonIgnore
    //@ApiModelProperty(notes="사용자 패스워드를를 입력해 주세요.")
    private String password;

    // @JsonIgnore
    //@ApiModelProperty(notes="사용자 주민번호를 입력해 주세요.")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id=id;
        this.name=name;
        this.joinDate=joinDate;
        this.password=password;
        this.ssn=ssn;
    }
}
