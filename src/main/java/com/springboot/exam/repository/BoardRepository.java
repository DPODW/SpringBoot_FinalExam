package com.springboot.exam.repository;


import com.springboot.exam.vo.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    @Transactional
    @Modifying
    @Query("update Board b set b.id = :#{#board.id}, b.title = :#{#board.title}, b.content = :#{#board.content} where b.number = :number")
    void updateContent(Board board,Long number);
}
