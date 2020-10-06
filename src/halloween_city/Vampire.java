package halloween_city;

import util.Helper;

import java.awt.*;

//vampire class that extends to the creature abstract class
public class Vampire extends Creature{
    int size;
    int speed;
    Color color = Color.RED;


    //constructor for vampire subclass
    public Vampire(int size, int speed, Color color){
        super(size, speed, color);
        this.color = Color.RED;
    }
    public Vampire(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void move() {
        int random = Helper.nextInt(100);
        if(random <= 20){
            changeDirection();

        }
        if (facing == SOUTH){
            y += 1;
        }
        if (facing == NORTH){
            y += -1;
        }
        if (facing == EAST){
            x += 1;
        }
        if (facing == WEST) {
            x += -1;
        }
    }
    public void unMove() {
        if (facing == SOUTH){
            y -= 1;
        }
        if (facing == NORTH){
            y -= -1;
        }
        if (facing == EAST){
            x -= 1;
        }
        if (facing == WEST) {
            x -= -1;
        }
    }

    @Override
    public void changeDirection() {
        int newDirection;
        newDirection = Helper.nextInt(4);
        while(this.facing == newDirection){
            newDirection = Helper.nextInt(4);
        }
        this.facing = newDirection;
    }


    @Override
    public void draw() {

    }

    @Override
    public boolean isWall(City city) {
        return false;
    }


    //if splashed with holy water, then vampire dies
    public void death(){

    }

    //if priest is there, then transform back to human
    public void transform(){

    }

    //checks if priest is nearby (using dist. formula)
    public int distanceToPriest(){
        return 0;
    }

    //checks if distance is 1 to priest
    public boolean meetPriest(){
        return false;
    }

    //chasing the human based on 4 conditions
    public void distToHuman(Human human){
        if ((human.x - 10 > 0) && (human.x - 10 < Simulation.MAX_X) && (human.x - 10) < this.x  && (human.y == this.y)){
            this.x -= 1;
            System.out.println("chasing");
        }
        if ((human.x + 10 > 0) && (human.x + 10 < Simulation.MAX_X) && (human.x + 10) < this.x && (human.y == this.y)){
            this.x += 1;
            System.out.println("chasing");
        }
        if ((human.y + 10 > 0) && (human.y + 10 < Simulation.MAX_X) && (human.y+ 10) < this.y && (human.x == this.x)){
            this.y -= 1;
            System.out.println("chasing");
        }
        if ((human.y - 10 > 0) && (human.y - 10 < Simulation.MAX_X) && (human.y- 10) < this.y && (human.x == this.x)){
            this.y += 1;
            System.out.println("chasing");
        }
    }

    //check the x,y of the human and ensure they are adjacent
    public boolean infect(Human human){
        if (x + 1 == human.x && y == human.y){
            return true;
        }
        if (x == human.x && y == human.y){
            return true;
        }
        if (x - 1 == human.x && y == human.y){
            return true;
        }
        if (y + 1 == human.y && x == human.x){
            return true;
        }
        if (y - 1 == human.y && x == human.x){
            return true;
        }
        return false;
    }


}
