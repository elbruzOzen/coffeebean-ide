import java.io.*;

public class CallingMethodsInSameClass
{
	public static void main(String[] args) {
		printOne();
		printOne();
		printTwo();
		
		//Keep console open
		try{
			System.in.read();
		}catch( IOException e){
			e.printStackTrace();
		}
	}

	public static void printOne() {
		System.out.println("Hello World");
	}

	public static void printTwo() {
		printOne();
		printOne();
	}
}