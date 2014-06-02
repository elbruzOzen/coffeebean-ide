/* HelloWorld.java
 */
 
 import java.io.*;

public class HelloWorld
{
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		//Keep console open
		try{
			System.in.read();
		}catch( IOException e){
			e.printStackTrace();
		}
		
	}
}