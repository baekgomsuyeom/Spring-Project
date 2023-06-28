package com.springproject.springproject.entity;

import com.springproject.springproject.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
//@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    // 게시글 작성
    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    // 게시글 수정
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
//        this.password = requestDto.getPassword();       // BoardService 에서 비밀번호 일치여부를 확인한 후, password 는 수정하지 않도록 한다. 게시글을 수정할 비밀번호가 필요한 거지, 비밀번호를 변경하는 게 아니기 때문 (db 를 건드릴 필요 X)
        this.contents = requestDto.getContents();
    }
}
