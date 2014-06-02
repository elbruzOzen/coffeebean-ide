import java.util.Scanner;
import java.io.*;

public class ScannerAndKeyboard
{

	public static void main(String[] args)
	{	Scanner s = new Scanner(System.in);
		System.out.print( "Enter your name: "  );
		String name = s.nextLine();
		System.out.println( "Hello " + name + "!" );
		
		//Keep console open
		try{
			System.in.read();
		}catch( IOException e){
			e.printStackTrace();
		}
	}
}