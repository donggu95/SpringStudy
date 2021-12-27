package chef;

import tool.FriPan;
import tool.Pan;

//요리사를 정의한다!!
public class Cook {
	private Pan pan;

	// Dependency Injection = DI 
	// 의존성 약화를 위한 객체 주입
	// 만일 주입시키는 방법을 사용하지 않으면, 개발자는
	// new 연산자를 사용해야 하므로, 이때 의존성 즉 결합도가
	// 너무 강해진다!!
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public void makeFood() {
		pan.boil();
	}
	
}
