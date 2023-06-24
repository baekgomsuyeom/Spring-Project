package com.springproject.springproject.dto;

import com.springproject.springproject.entity.Board;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id;        // 게시글 구분을 위한 id 값
    private String title;       // 제목
    private String username;    // 작성자명
    private String contents;    // 작성 내용
    private LocalDateTime createdAt;        // 게시글 생성 날짜
    private LocalDateTime modifiedAt;       // 게시글 수정 날짜

    private String msg;     // 게시글 삭제 시, 삭제 성공 메시지


    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

    // 게시글 삭제 시, 삭제 성공 메시지
    public BoardResponseDto(String msg) {
        this.msg = msg;
    }
}
