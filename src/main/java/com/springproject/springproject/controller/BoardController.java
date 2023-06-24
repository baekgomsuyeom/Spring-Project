package com.springproject.springproject.controller;

import com.springproject.springproject.dto.BoardRequestDto;
import com.springproject.springproject.dto.BoardResponseDto;
import com.springproject.springproject.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;     // 2. 넣어주기만 하면 된다 (약한 결합을 위해)

    public BoardController(BoardService boardService) {        // 1. 외부에서 이미 만들어두었던 memoService 를 파라미터로 받아서 (단, bean 객체만 주입받을 수 있다 -> memoRepository 를 빈으로 등록)
        this.boardService = boardService;
    }

    // 게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);      //  memoService 클래스의 createMemo 메서드 실행한 것을 client 에게로 반환
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
//    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}/{password}")
//    public Long deleteBoard(@PathVariable Long id, @PathVariable String password) {
    public BoardResponseDto deleteBoard(@PathVariable Long id, @PathVariable String password) {
        return boardService.deleteBoard(id, password);
    }
}
/* 게시글 삭제에서 Long 타입인 id 만 파라미터에 있을 경우, 반환타입을 Long해도 무방했으나
* String 타입인 password 까지 파라미터에 있기 때문에, BoardResponseDto 타입으로 반환해준다.
* 그리고, 이렇게 되면 '게시글 수정'에서만 반환 타입이 다르기 때문에,
* 일치시켜주기 위해 게시글 수정까지도 BoardResponseDto 반환 타입으로 변경한다.*/
