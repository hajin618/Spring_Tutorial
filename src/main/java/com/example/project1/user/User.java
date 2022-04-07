package com.example.project1.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
// @JsonIgnoreProperties(value={"password", "ssn"})
@NoArgsConstructor  // default 생성자 생성
@JsonFilter("UserInfo")
public class User {
    private Integer id;
    @Size(min=2, message = "Name 은 2글자 이상 입력")    //최소 사이즈 2
    private String name;
    @Past   // 과거 Date만 사용 가능
    private Date joinDate;

    // @JsonIgnore
    private String password;
    // @JsonIgnore
    private String ssn;
}
