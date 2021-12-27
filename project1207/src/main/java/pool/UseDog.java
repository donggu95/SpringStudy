package pool;

public class UseDog {

	public static void main(String[] args) {
		//더이상 생성자 호출에 의한 인스턴스 생성은 불가... 
		Dog d1 = Dog.getInstance();
		Dog d2 = Dog.getInstance();
		Dog d3 = Dog.getInstance();
		
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		
		d1.run();
		d2.run();
		d3.run();

	}

}
