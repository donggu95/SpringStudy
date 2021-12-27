package com.koreait.mvc1223.model.board;

import java.util.List;

import com.koreait.mvc1223.domain.Board;

//모든 Board관련 DAO들이 구현해야 할 최상위 객체 DI 때문에...
public interface BoardDAO {
	
	public List selectAll();
	public Board select(int board_id);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_id);
	//답변관련
	public void updateStep(Board board);//자리확보
	public void replyInsert(Board board);//답변등록
}
