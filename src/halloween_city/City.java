package halloween_city;

import util.DotPanel;
import util.Helper;
import java.util.ArrayList;

import java.awt.Color;


public class City {

	/** Extra FUN
	 * 
	 * This is a option for students who want an extra challenge. 
	 * If you do use the walls you will get extra credit (only if your project works).
	 * 
	 *  walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans, vampires, and priests should never go into spaces that
	 *  have a wall.
	 *
	 */
	public boolean walls[][];
	ArrayList<Human> humanlist = new ArrayList<Human>();
	ArrayList<Vampire> vamplist = new ArrayList<Vampire>();
	ArrayList<Priest> priestlist = new ArrayList<Priest>();
	private int width, height;

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 * You should modify this function to take the number of:
	 *  vampires,priests, and other creatures too. 
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];
		
		randomBuildings(numB);
		
		populate(numP);
	}


	/**
	 * Generates numPeople random people distributed throughout the city.
	 * ETRA FUN : People, vampires & other creatures must not be placed inside walls!
	 * You can modify this function to 
	 *
	 * @param numPeople the number of people to generate
	 * 
	 * 
	 */
	private void populate(int numPeople)
	{
		// Generate numPeople new humans randomly placed around the city.

		//create list of humans and draw them
		for (int i = 0; i < numPeople; i ++){
			Human temp = new Human(1, 1, Color.WHITE);
			//check if human at index i is inside a wall
			while (walls[temp.x][temp.y]){
				temp = new Human(1, 1, Color.WHITE);
			}
			humanlist.add(temp);
			System.out.println("Human " + (i+1) + " (x,y) = (" + humanlist.get(i).x + ", " + humanlist.get(i).y + ") Direction: " + humanlist.get(i).facing);
		}

		Vampire temp = new Vampire(1,1,Color.RED);
		while(walls[temp.x][temp.y]){
			temp = new Vampire(1,1,Color.RED);
		}
		vamplist.add(temp);

		Priest priest = new Priest(1,1,Color.WHITE);
		while(walls[priest.x][priest.y]){
			priest = new Priest(1,1,Color.WHITE);
		}
		priestlist.add(priest);

	}


	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;

		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}

	
	/**
	 * Updates the state of the city for a time step.
	 */

	//set of conditions the simulation must follow
	public void rules(){
		for (int i = 0; i < vamplist.size(); i ++){
			for (int j = 0; j < humanlist.size(); j++){
				if (vamplist.get(i).infect(humanlist.get(j))){
					int x_pos = humanlist.get(j).x;
					int y_pos = humanlist.get(j).y;
					humanlist.remove(j);
					Vampire new_vamp = new Vampire(x_pos,y_pos);
					vamplist.add(new_vamp);
				}
				//chasing the humans for vampires
				vamplist.get(i).distToHuman(humanlist.get(j));
			}
			break;
		}
		for (int i = 0; i < priestlist.size(); i ++){
			for (int j = 0; j < vamplist.size(); j++){
				if (priestlist.get(i).rescue(vamplist.get(j))){
					int x_pos = vamplist.get(j).x;
					int y_pos = vamplist.get(j).y;
					vamplist.remove(j);
					Human new_human = new Human(x_pos,y_pos, Color.WHITE);
					humanlist.add(new_human);
				}
				if (priestlist.get(i).useHolyWater(vamplist.get(j))){
					vamplist.remove(j);
				}
				//chasing the humans for vampires
				priestlist.get(i).distToVamp(vamplist.get(j));
			}
			break;
		}
	}
	public void update() {
		//always check the rules
		rules();

		// Move humans, vampires, etc
		for (int i = 0; i < humanlist.size(); i++) {
			humanlist.get(i).move();
			//while the human is next to a wall, make them move backwards
			while (isWall(humanlist.get(i)) == true){
				humanlist.get(i).unMove();
			}
		}
		for (int i = 0; i < vamplist.size(); i++) {
			vamplist.get(i).move();
			//while the human is next to a wall, make them move backwards
			while (isWall(vamplist.get(i)) == true) {
				vamplist.get(i).unMove();
			}
		}

		for (int i = 0; i < priestlist.size(); i++) {
			priestlist.get(i).move();
			//while the human is next to a wall, make them move backwards
			while (isWall(priestlist.get(i)) == true) {
				priestlist.get(i).unMove();
			}
		}
	}





	/**
	 * Draw all humans, vampires, and buildings (.
	 */
	public void draw(){
		/* Clear the screen */
		Simulation.dp.clear(Color.BLACK);
		
		drawWalls();
		for (int i = 0; i < humanlist.size(); i++){
			Simulation.dp.setPenColor(humanlist.get(i).color);
			Simulation.dp.drawDot(humanlist.get(i).x, humanlist.get(i).y);
		}
		for (int i = 0; i < vamplist.size(); i++) {
			Simulation.dp.setPenColor(vamplist.get(i).color);
			Simulation.dp.drawDot(vamplist.get(i).x, vamplist.get(i).y);
		}
		for (int i = 0; i < priestlist.size(); i++){
			Simulation.dp.setPenColor(priestlist.get(i).color);
			Simulation.dp.drawDot(priestlist.get(i).x, priestlist.get(i).y);
		}

	}

	/**
	 * EXTRA FUN function... not used unless you are going for that
	 * Draw the buildings.
	 * First set the color for drawing, then draw a dot at each space
	 * where there is a wall.
	 */
	private void drawWalls() {
		Simulation.dp.setPenColor(Color.DARK_GRAY);
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					Simulation.dp.drawDot(c, r);
				}
			}
		}
	}

	public boolean isWall(Human human){
		if ((human.x < 0) || (human.x > Simulation.MAX_X-1) || (human.y < 0) || (human.y > Simulation.MAX_Y-1)){
			return true;
		}
		else{
			return this.walls[human.x][human.y];
		}
	}

	public boolean isWall(Vampire human){
		if ((human.x < 0) || (human.x > Simulation.MAX_X-1) || (human.y < 0) || (human.y > Simulation.MAX_Y-1)){
			return true;
		}
		else{
			return this.walls[human.x][human.y];
		}
	}

	public boolean isWall(Priest human){
		if ((human.x < 0) || (human.x > Simulation.MAX_X-1) || (human.y < 0) || (human.y > Simulation.MAX_Y-1)){
			return true;
		}
		else{
			return this.walls[human.x][human.y];
		}
	}

}
