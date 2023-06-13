package com.springboot.exam.service;

import com.springboot.exam.repository.BoardRepository;
import com.springboot.exam.vo.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board){
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    public Optional<Board> findContent(Long number){
        return boardRepository.findById(number);
    }

    public void updateContent(Board board,Long number){
         boardRepository.updateContent(board,number);
    }
}
