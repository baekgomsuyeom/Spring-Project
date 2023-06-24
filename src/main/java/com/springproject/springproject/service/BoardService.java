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
    private final BoardRepository boardRepository;     // 2. 넣어주기만 하면 된다 (약한 결합을 위해)

    // 생성자 주입 방법
    // @Autowired : 빈을 사용하기위해, 빈을 주입 (생성자가 1개일 땐 생략해도 무방하나, 두 개 이상일 땐 직접 이 어노테이션을 붙여줘야함) (빈 클래스에서만 주입 가능)
    public BoardService(BoardRepository boardRepository) {        // 1. 외부에서 이미 만들어두었던 memoRepository 를 파라미터로 받아서 (단, bean 객체만 주입받을 수 있다 -> memoRepository 를 빈으로 등록)
        this.boardRepository = boardRepository;
    }

    // 게시글 작성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);

        // DB 저장
        Board saveBoard = boardRepository.save(board);      // save()메서드로 memo 를 저장하고, Memo 타입으로 반환

        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }

    // 게시글 전체 조회
    public List<BoardResponseDto> getBoardList() {
        // DB 조회
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();        // MemoResponseDto(Memo memo) 를 호출해서 List 타입으로 변환 후 반환
    }

    // 게시글 선택 조회
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        return new BoardResponseDto(board);
    }

    // 게시글 수정
    @Transactional      // 트랜잭션 환경을 만들어야, 영속성 컨텍스트가 유지되고, 변경 감지가 가능해짐 (없다면, 수정이 안 된다)
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인 (Optional 사용. null 값이 아니면 메모가 반환되고, null 값이면 예외 메시지)
        Board board = findBoard(id);

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(requestDto.getPassword())) {
            // memo 내용 수정
            board.update(requestDto);
        } else {
            return new BoardResponseDto("비밀번호가 일치하지 않습니다.");
        }

        return new BoardResponseDto(board);
    }

    // 게시글 삭제
    public BoardResponseDto deleteBoard(Long id, String password) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);

        // 비밀번호 일치 여부 확인
        if (board.getPassword().equals(board.getPassword())) {
            // memo 삭제
            boardRepository.delete(board);        // Entity 객체를 파라미터로 받음
        } else {
            return new BoardResponseDto("비밀번호가 일치하지 않습니다.");
        }

        return new BoardResponseDto("게시글을 삭제했습니다.");
    }

    // id 일치 여부 확인 (공용 사용되는 부분)
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}









