package minesweeper;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import javax.swing.JFrame;
import java.awt.Component;


public class Play extends JFrame
{
	private static final int WIDTH = 750;
	private static final int HEIGHT = 473;

	public Play()
	{
		super("Minesweeper");
		setSize(WIDTH,HEIGHT);

		Minesweeper theGame = new Minesweeper();
		((Component)theGame).setFocusable(true);
                
		getContentPane().add(theGame);

		setVisible(true);

                
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		Play run = new Play();
	}
}