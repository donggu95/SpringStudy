package com.koreait.mvc.model.blood;

public class BloodManager {

	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="세심하고 책임감이 강하다, 근데 쫌더 세심이 크다.";	
		}else if(blood.equals("B")){ 
			msg="남자이면 고집이 쎄고, 여자이면 4차원이다..";
		}else if(blood.equals("AB")){ 
			msg="여자이면 시시각각 변한다";
		}else if(blood.equals("O")){
			msg="지나치게 오지랖이 넓다";
		}	
		return msg;
	}
	
}