package halloween_city;

import util.Helper;

import java.awt.*;

public class Priest extends Creature {

    //constructor for priest subclass
    public Priest(int size, int speed, Color color){
        super(size, speed, color);
        this.color = Color.WHITE;
    }

    @Override
    //randomly moving based on conditions/class you are in
    public void move() {
        int random = Helper.nextInt(100);
        if(random <= 15){
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
    //when you are facing a wall and trying to get back
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

    public boolean rescue(Vampire vamp){
        if (x + 1 == vamp.x && y == vamp.y){
            return true;
        }
        if (x - 1 == vamp.x && y == vamp.y){
            return true;
        }
        if (y + 1 == vamp.y && x == vamp.x){
            return true;
        }
        if (y - 1 == vamp.y && x == vamp.x){
            return true;
        }
        return false;
    }

    @Override
    public boolean isWall(City city) {
        return false;
    }

    public void distToVamp(Vampire vamp){
        if ((vamp.x - 5 > 0) && (vamp.x - 5 < Simulation.MAX_X) && (vamp.x - 5) < this.x  && (vamp.y == this.y)){
            this.x -= 1;
            System.out.println("priest chase");
        }
        if ((vamp.x + 5 > 0) && (vamp.x + 5 < Simulation.MAX_X) && (vamp.x + 5) < this.x && (vamp.y == this.y)){
            this.x += 1;
            System.out.println("priest chase");
        }
        if ((vamp.y + 5 > 0) && (vamp.y + 5 < Simulation.MAX_X) && (vamp.y+ 5) < this.y && (vamp.x == this.x)){
            this.y -= 1;
            System.out.println("priest chase");
        }
        if ((vamp.y - 5 > 0) && (vamp.y - 5 < Simulation.MAX_X) && (vamp.y- 5) < this.y && (vamp.x == this.x)){
            this.y += 1;
            System.out.println("priest chase");
        }
    }

    public boolean useHolyWater(Vampire vamp){
        int chance = Helper.nextInt(10);
        if (chance <= 2){
            if (x + 2 == vamp.x && y == vamp.y){
                return true;
            }
            if (x - 2 == vamp.x && y == vamp.y){
                return true;
            }
            if (y + 2 == vamp.y && x == vamp.x){
                return true;
            }
            if (y - 2 == vamp.y && x == vamp.x){
                return true;
            }
        }
        else
            return false;
        return false;
    }


}
