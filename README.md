# Creature Class:

move()
to be used by every subclass for moving and updating the x,y coordinates

changeDirection()
every subclass should be able to change their direction when faced with a wall.

# Human Class:

move()
Randomly select a number in range of 1-100 and if it falls within 10 or less, then move randomly. Else just move normally and react to the vampire.

unMove()
Go back to the spot you were if there was a wall in front of you and you tried to move there. This will make it a little slower but it was to ensure that it was functional.

changeDirection()
Randomly generate a number from 0-3 and until it a value that the instance does not have, then continue to generate a new number and assign that to the instance. 

infected()
Takes in a vampire as an argument and then compares the x,y coordinates to see if it is adjacent to it by adding 1 to each of the directions and seeing if it is also within the same axis while checking one of the other axes. 

# Vampire Class:

move()
Randomly select a number in range of 1-100 and if it falls within 10 or less, then move randomly. Else just move normally and react to the vampire.

unMove()
Go back to the spot you were if there was a wall in front of you and you tried to move there. This will make it a little slower but it was to ensure that it was functional.

changeDirection()
Randomly generate a number from 0-3 and until it a value that the instance does not have, then continue to generate a new number and assign that to the instance. 

distToHuman()
This acts as the chasing mechanism for the vampire that takes in a human as an argument. This calculates the bounds of the human’s position by + and - 10 from the vampire’s x,y position to ensure that it is within it’s constraints. If it is, it will increment it’s position towards that human and continue to do so while they are in the same axis and bounds.

infect()
Takes in a human as an argument and then compares the x,y coordinates to see if it is adjacent to it by adding 1 to each of the directions and seeing if it is also within the same axis while checking one of the other axes. Returns as a boolean function

# Priest Class:

move()
Randomly select a number in range of 1-100 and if it falls within 10 or less, then move randomly. Else just move normally and react to the vampire.

unMove()
Go back to the spot you were if there was a wall in front of you and you tried to move there. This will make it a little slower but it was to ensure that it was functional.

changeDirection()
Randomly generate a number from 0-3 and until it a value that the instance does not have, then continue to generate a new number and assign that to the instance. 

rescue()
Same logic as the infect method but takes in the vampire as an argument. This is a boolean function to get a true or false value that you can use to check and if it is true, delete the vampire and convert that into a human with the same x and y coordinates.

distToVamp()
Takes in a vampire as an argument and uses the same logic to chase the vampire as a vampire does to chase a human except the numbers being different. This looks at 5 steps as opposed to 10.

useHolyWater()
Same logic as when checking the distance to a vampire, but is checking for when a vampire is two blocks away. Uses the helper class to generate a random number and if that number is 2 or less, then it will follow through with the holy water and kill the vampire. Else, it should not do anything there and return false.
