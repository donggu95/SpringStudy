package com.koreait.mvc.model.movie;

public class MovieManager {

	public String getAdvice(String movie) {
		String msg=null;
		
		if(movie.equals("avengers")){
			msg="지구와 우주 용사들의 싸움";	
		}else if(movie.equals("The Fast and the Furious")){
			msg="레이싱 질주영화";
		}else if(movie.equals("ironman")){
			msg="빨간갑옷 로봇이 싸우는영화";
		}else if(movie.equals("spiderman")){
			msg="거미에 물리면 몸에서 거미가 나감";
		}
		return msg;
	}
}
