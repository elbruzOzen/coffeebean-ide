package com.coffeebean.core;

import java.io.IOException;
import com.coffeebean.view.MainContainer;
import com.coffeebean.view.StartingScreen;

/*
 * Link StartingScreen and MainFrame
 * Create instances of them and show to the user
 * 
 * Author: Mustafa Erdem
 * 01.04.2014
 * 
 */

public class Launcher {

	public static void main(String[] args) throws IOException {
		
		MainContainer container;
		StartingScreen splashScreen;
		
		//Title
		container = new MainContainer( "CoffeeBean IDE - Demo Version" );
		//SplashScreen waits 3 seconds
		splashScreen = new StartingScreen(3000);
		
		//Show SplashScreen
		splashScreen.showSplash();
		
		//Show MainContainer
		container.setVisible( true );
	
	}

}
