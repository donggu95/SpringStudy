package com.koreait.mvc1223.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mvc1223.domain.Board;
import com.koreait.mvc1223.exception.BoardException;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void insert(Board board) throws BoardException{
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) throws BoardException{
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws BoardException{
		boardDAO.delete(board_id);
	}

	@Override
	public void reply(Board board) {
		boardDAO.updateStep(board); //자리확보
		boardDAO.replyInsert(board);//답변 글 등록
	}

}
