package com.example.project1.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // User : Post = 1 : N (N=0 가능)     main : sub -> Parent : Child
    // LAZY : 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // 외부 공개 x
    private User user;
}
