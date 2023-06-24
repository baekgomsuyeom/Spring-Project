package com.springproject.springproject.service;

import com.springproject.springproject.dto.BoardRequestDto;
import com.springproject.springproject.dto.BoardResponseDto;
import com.springproject.springproject.entity.Board;
import com.springproject.springproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 작성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);        // RequestDto -> Entity
        Board saveBoard = boardRepository.save(board);      // DB 저장
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);        // Entity -> ResponseDto

        return boardResponseDto;
    }

    // 게시글 전체 조회
    public List<BoardResponseDto> getBoardList() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();       // DB 조회
    }

    // 게시글 선택 조회
    public BoardResponseDto getBoard(Long id) {
        // 해당 id 가 없을 경우
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // 해당 id 가 있을 경우
        return new BoardResponseDto(board);
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);        // DB에 해당 id 의 게시글이 존재하는지 확인

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);       // 일치하면 게시글 수정
        } else {
            return new BoardResponseDto("비밀번호가 일치하지 않습니다.");
        }

        return new BoardResponseDto(board);
    }

    // 게시글 삭제
    public BoardResponseDto deleteBoard(Long id, String password) {
        Board board = findBoard(id);

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(board.getPassword())) {
            boardRepository.delete(board);        // 일치하면 게시글 삭제
        } else {
            return new BoardResponseDto("비밀번호가 일치하지 않습니다.");
        }

        return new BoardResponseDto("게시글을 삭제했습니다.");        // 게시글 삭제 시, 삭제 성공 메시지
    }

    // id 일치 여부 확인 (공용 사용되는 부분)
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}









