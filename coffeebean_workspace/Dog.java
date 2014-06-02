/*
	A virtual dog with some properties.
	Author: Elbruz Özen 07 May 2014
*/
public class Dog{
	
	int age;
	String name;
	
	public Dog( int age, String name){
		this.age = age;
		this.name = name;
	}
	
	public String toString(){
		return name + " " + age;
	}

}
