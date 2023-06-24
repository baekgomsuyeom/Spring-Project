package com.springproject.springproject.controller;

import com.springproject.springproject.dto.BoardRequestDto;
import com.springproject.springproject.dto.BoardResponseDto;
import com.springproject.springproject.service.BoardService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    // 게시글 전체 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getBoardList() {
        return boardService.getBoardList();
    }
    
    // 게시글 선택 조회
    @GetMapping("/board/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // 게시글 수정
    @PutMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}/{password}")
    public BoardResponseDto deleteBoard(@PathVariable Long id, @PathVariable String password) {
        return boardService.deleteBoard(id, password);
    }
}
