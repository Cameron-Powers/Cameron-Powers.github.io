import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

public class Main {

	public static void main(String[] args){
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(550,200,700,600);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		obj.setVisible(true);
	}
}