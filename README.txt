raz.perry


=============================
=      File description     =
=============================
SpaceShip.java - an abstract spaceship class, from which all spaceships inherit. The main methods are called
from SpaceWars every turn and make the ship action, checking hits and health status.
SpaceShipFactory.java - creates all the space ship objects according to the command line arguments.
AggressiveShip.java - implements computer controlled space ship that chasing after the closest ship and 
attempt to fire on it.
BasherShip.java - implements computer controlled space ship that bashing with shields up at any close ship.
RunnerShip.java - implements computer controlled space ship that running away from the closest ship. 
DrunkShip.java - implements computer controlled space ship that act randomly - moving, firing, shield up and
teleporting by random.
SpecialShip.java - implements computer controlled space ship that acts both like the aggressive and basher
space ships when has a lot of energy and acts like runner when hasn't a lot of eenrgy.


=============================
=          Design           =
=============================
I implemented the SpaceShip as abstract class.
I chose class and not interface because any type of space ship (human, runner, basher...) is really a type of
SpaceShip - so it makes sense to inherit from it.
I chose abstract because there is no need for an instance of a general SpaceShip.
- Extensibility: In that design adding new methods will be very easy - if its relavent to all ships it needed
to add the SpaceShip class and if its relavent for a specific ship type it needed to add its belong class.
In addition adding new rules of the games are very clear in the SpaceShip class (for example changing the
amount of energy which added after colusion with a shield.
- Modularity: By using sub-classes for each ship type and by dividing doAction method to "sub-methods" for
each "action type" (move, teleport, turn shield on, fire) we gets modularity program.
Most data members are private and if sub-class needed any data member (for example to add energy at the end 
of the turn) there is a new protected method to do that. If all needed is to get information i create the 
method as public (so i added getters to the API).
– Intuitiveness and understandability: i think making the sub-classes made the program to be very intuitive
and the same for making new methods as override for each check in doAction at the sub-classes (according to 
the user or the closest ship), by that its beacme easy to test each action by itself for all ships together.
– Representation of the classes and their relationships: as described in the file description - each type of
ship as a class that inherited from SpaceShip which is an abstract class.


=============================
=  Implementation details   =
=============================
The drunk ship behavior:
I create that ship to acts randomly by methods of Random. I chose probability chance for each action - 
teleport, fire and shield up (by limit nextint method). It choose to turn right/left according to the 
random method - nextboolean.
The drunk ship behavior:
My special ship is trying to be very dificault to deafet and it followes the game rules. When the ship has a
lot of energy (according the relation between the current energy and the current max energy) it acts both
like the aggressive and basher ships - chaising the closeset ship, fire and if getting very close the shield
goes up. When there isn't a lot of energy it acts more like the runner - trying to avoid any fight (to get
stronger with more energy).
More comments:
I create protected method updateFiringCount to manage the counter of rounds that the ship can't fire. This
method called from every ship class that able to fire even if that ship didn't try to fire at that turn.
Another method i create is defaultThisAttributes which called in the constructor and any reset time, by that
there is less code duplication.
I decided to create shield up boolean data member as protected and not private with update methods because
the action is very short and simple and worth the worst case that any new class will use it wrongly (not 
according to what i meant). At that case it still will be pretty easy to find the bug - because the data 
member is boolean (so it will be easy to find and change it).
In the SpaceShipFactory i assumed that the input is one char string (otherwise it will get unvalid input like
"sa" as specail ship.


=============================
=    Answers to questions   =
=============================

not asked.