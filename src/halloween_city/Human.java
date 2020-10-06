package halloween_city;

import util.DotPanel;
import util.Helper;

import java.awt.*;

//create a child class that extends from the abstract creature class
public class Human extends Creature{


    //constructor for human subclass
    public Human(int size, int speed, Color color){
        super(size, speed, color);
        this.color = Color.WHITE;
    }

    @Override
    public void move() {
        int random = Helper.nextInt(100);
        if(random <= 10){
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
    public boolean isWall(City city) {
        //if it is facing N,S,E,W, check the corresponding value ahead of it
        /*
        if (this.facing == NORTH){
            if (this.y-1 > -1 && city.walls[this.x][this.y-1]){
                return true;
            }
        }
        if (this.facing == SOUTH){
            if (this.y+1 < (Simulation.MAX_Y-1) && city.walls[this.x][this.y+1]){
                return true;
            }
        }
        if (this.facing == WEST){
            if (this.x-1 > -1 && city.walls[this.x-1][this.y]){
                return true;
            }
        }
        if (this.facing == EAST){
            if (this.x+1 < (Simulation.MAX_X-1) && city.walls[this.x+1][this.y]){
                return true;
            }
        }
        return false;
        */
         if ((this.x < 0) || (this.x >= Simulation.MAX_X-1) || (this.y < 0) || (this.y > Simulation.MAX_Y-1)){
             return true;
         }
         else{
             return city.walls[this.x][this.y];
         }
    }


    //check the distance to vampires every time the
    //simulation updates
    public boolean infected(Vampire vampire){
        if (this.x +1 == vampire.x){
            return true;
        }
        if (this.x - 1 == vampire.x){
            return true;
        }
        if (this.y + 1 == vampire.y){
            return true;
        }
        if (this.y - 1 == vampire.y){
            return true;
        }
        return false;
    }

    //if distanceToVampire is true, then transform
    public void transform(){

    }


}
