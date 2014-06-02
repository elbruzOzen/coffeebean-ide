import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics;

public class Window extends JFrame{

	public Window(){
	
		super("My Window");
		setSize( 500 , 500 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible( true );
		
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawOval( 200 , 200 , 100 , 100);
		g.drawLine(10,10,300,300);
	
	}
}
