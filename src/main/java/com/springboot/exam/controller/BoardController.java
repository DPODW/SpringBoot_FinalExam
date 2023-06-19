package com.springboot.exam.controller;


import com.springboot.exam.repository.SessionConst;
import com.springboot.exam.service.BoardService;
import com.springboot.exam.vo.Board;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board(Model model){
        List<Board> boards = boardService.boardList();
        model.addAttribute(boards);
        return "board/board";
    }

    @GetMapping("/board1")
    public String boardWriteForm(HttpServletRequest request,Board board ,Model model){
        HttpSession session = request.getSession(false);
        HashMap<String,String> sessionData = (HashMap<String, String>) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String sessionDataToString = sessionData.toString().replaceAll("[{}]", "");
        String[] loginInfo = sessionDataToString.split("=");
        String id = loginInfo[0];

        model.addAttribute("id",id);
        model.addAttribute("board",board);
        return "board/write";
    }


    @GetMapping("/board2/{number}")
    public String boardUpdateForm(@PathVariable("number")Long number,Model model){
        Optional<Board> updateContent = boardService.findContent(number);
        model.addAttribute("board",updateContent.get());
        return "board/writeUpdate";
    }


    @GetMapping("/boardc/{number}")
    public String boardContent(@PathVariable("number")Long number,Model model){
        Optional<Board> content = boardService.findContent(number);
        model.addAttribute("board",content.get());
        log.info("{}",content);
        return "board/writeContent";
    }



    @PostMapping("/board1")
    public String boardWrite(@Validated @ModelAttribute Board board, BindingResult bindingResult,Model model){
          if(bindingResult.hasErrors()){
             model.addAttribute("id",board.getId());
             return "board/write";
          }else
            boardService.save(board);
            model.addAttribute(board);
            return "redirect:/board" ;
    }



    @PostMapping("/board2")
    public String boardUpdate(@Validated @ModelAttribute Board board,BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                return "board/writeUpdate";
            }else
            boardService.updateContent(board,board.getNumber());
            log.info("{}",board);
                return "redirect:/board";
        }


    @PostMapping("/board3/{number}")
    public String boardDelete(@PathVariable("number") Long number){
            boardService.deleteContent(number);
            return "redirect:/board";
        }


}
