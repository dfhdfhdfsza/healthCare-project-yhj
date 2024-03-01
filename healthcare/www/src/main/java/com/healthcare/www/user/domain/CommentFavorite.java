package com.healthcare.www.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentFavorite {

    @Id
    @Column(name="favorite_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long favoriteNo;

    @Column(name="user_no")
    private long userNo;

    @Column(name="comment_no")
    private long commentNo;
}
