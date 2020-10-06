package halloween_city;

import util.Helper;

import java.awt.*;



//abstract parent class for the three types of characters
public abstract class Creature {
    final int NORTH = 0;
    final int SOUTH = 1;
    final int EAST = 2;
    final int WEST = 3;
    //size of object, speed, color of it, x position, y position
    int size;
    int speed;
    Color color;
    int x;
    int y;
    int facing;

    //constructor for creature
    public Creature(int size, int speed, Color color){
        this.size = size;
        this.speed = speed;
        this.color = color;
        this.x = Helper.nextInt(200);
        this.y = Helper.nextInt(200);
        this.facing = Helper.nextInt(4);

        if (this.facing == 0){
            this.facing = NORTH;
        }
        else if (this.facing == 1){
            this.facing = SOUTH;
        }
        else if (this.facing == 2){
            this.facing = EAST;
        }
        else if (this.facing == 3){
            this.facing = WEST;
        }
    }

    public Creature() {

    }

    //move in a straight line given child class and their percentage
    public abstract void move();

    //move in a random way given the child class and their percentage
    public abstract void changeDirection();

    //puts the character on the screen
    public void draw() {
        //set color to be drawn
        Simulation.dp.setPenColor(color);

        //draw the pixel -> random dot
        Simulation.dp.drawDot(x, y);
        System.out.println("Working");
    }

    //check if there is a wall in front of character
    public abstract boolean isWall(City city);
}
