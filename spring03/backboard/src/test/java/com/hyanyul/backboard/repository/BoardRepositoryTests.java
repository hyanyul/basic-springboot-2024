package com.hyanyul.backboard.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// import com.hyanyul.backboard.entity.Board;
// import com.hyanyul.backboard.service.BoardService;

@SpringBootTest
public class BoardRepositoryTests {
     // JUnit 테스트
    //  @Autowired
    //  private BoardRepository boardRepository;

    //  @Autowired
    //  private BoardService boardService;

    //  @Test
    //  void testHugeBoards(){
    //     for(int i=1; i<=400; i++){
    //         this.boardService.setBoard(String.format("테스트 데이터 - [%03d]", i), 
    //                                    "내용 없음", null);
    //     }
    //  }
 
    //  @Test
    //  void testInsertBoard() {
    //     Board board1 = new Board();   // 전통적인 객체 생성방식
    //     board1.setTitle("첫번째 테스트입니다.");
    //     board1.setContent("내용입니다.");
    //     board1.setCreateDate(LocalDateTime.now());
    //     this.boardRepository.save(board1);
    
    //     // Builder를 사용한 객체 생성 방식
    //     Board board2 = Board.builder().title("두번째 테스트입니다.")
    //                                   .content("내용입니다.")
    //                                   .createDate(LocalDateTime.now()).build();
    //     this.boardRepository.save(board2);
    //     System.out.println("테스트 완료!!");
    // }

    // @Test
    // void testSelectBoard(){
    //     List<Board> all = this.boardRepository.findAll();   // select * from board
    //     assertEquals(12, all.size());
    //     System.out.println(all.size());

    //     Board bd = all.get(0);  // 게시글 중 가장 첫번째 값
    //     assertEquals(1, bd.getBno());
    // }

    // @Test
    // void testUpdateBoard(){
    //     Optional<Board> bd = this.boardRepository.findById(1L); // Long 값은 뒤에 L 추가
    //     assertTrue(bd.isPresent()); // bno가 1번인 게시글 객체 넘어왔는지 확인
    //     Board ubd = bd.get();
    //     ubd.setContent("테스트로 수정합니다.");
    //     this.boardRepository.save(ubd); // save(): id가 없으면 INSERT, 있으면 UPDATE 쿼리 자동 실행
    //     System.out.println("수정 완료");
    // }

    // @Test
    // void testDeleteBoard(){
    //     Optional<Board> bd = this.boardRepository.findById(153L);
    //     assertTrue(bd.isPresent());
    //     Board dbd = bd.get();
    //     this.boardRepository.delete(dbd);
    //     System.out.println("삭제 완료");
    // }
}
