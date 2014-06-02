import java.io.*;

public class Test{

	public static void main(String[] args) {

		
		Dog dog1 = new Dog(5,"Hel");
		System.out.println(dog1)
		

		//Keep console open
		try{
			System.in.read();
		}catch( IOException e){
			e.printStackTrace();
		}
	}
}
