package halloween_city;

import util.DotPanel;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;


import java.awt.event.*;


/*
 * You must add a way to represent humans.  When there is not a vampire  apocalypse occurring, humans
 * should follow these simple rules:
 * 		if (1 in 10 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space 
 *
 * We will add additional rules for dealing with sighting or running into vampires later.
 */


public class Simulation extends JFrame implements KeyListener, MouseListener {

	City world;
	private static final long serialVersionUID = -5176170979783243427L;

	/*
	 * The Dot Panel object you will draw to.
	 * NOTE: this is protected static! 
	 */
	protected static DotPanel dp;

	/* Define constants using static final variables */
	public static final int MAX_X = 200;
	public static final int MAX_Y = 200;
	private static final int DOT_SIZE = 3;
	private static final int NUM_HUMANS = 200;
	private static final int NUM_BUILDINGS = 60;



	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen.
	 */
	public Simulation() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Braaiinnnnnsss");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);
		this.addKeyListener(this);
		this.addMouseListener(this);
		

		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		/* Create our city */
		world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);

		/* This is the Run L
		oop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having humans take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */


		while(true)
		{

			// Run update rules for world and everything in it
			world.update();
			// Draw to screen and then refresh
			world.draw();
			dp.repaintAndSleep(30);

		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window  */
		new Simulation();
	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);
			world.draw();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Priest new_priest = new Priest(1,1,Color.WHITE);
		while(world.walls[new_priest.x][new_priest.y]){
			new_priest = new Priest(1,1,Color.WHITE);
		}
		world.priestlist.add(new_priest);
		System.out.println("added priest");

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
