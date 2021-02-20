import java.awt.Image;
import oop.ex2.*;
import java.lang.Math;

/**
 * This class is manage space ship objects for the SpaceWars game.
 * @author raz perry
 */
public abstract class SpaceShip{
    /* constants of ship objects */
    protected final static int LEFT = 1;
    protected final static int RIGHT = -1;
    protected final static int NO_TURN = 0;
    private final static int START_HEALTH = 22;
    private final static int START_ENERGY = 190;
    private final static int START_MAX_ENERGY = 210;
    private final static int SHIELD_COLLISION_ENERGY = 18;
    private final static int HIT_ENERGY = 10;
    private final static int FIRE_ENERGY = 19;
    private final static int SHIELD_ENERGY = 3;
    private final static int TELEPORT_ENERGY = 140;
    private final static int OUT_FIRING_ROUNDS = 7;

    /* ship attributes */
    private SpaceShipPhysics shipPhysics; // The physics object that controls this ship
    private int energy; // The current energy of this ship
    private int maxEnergy; // The current maximum energy of this ship
    private int health; // The current health level of this ship
    protected boolean shieldsUp; // true if the ship turn the shields on, false otherwise
    private int firing; // Counting rounds when ship can't fire - according to the game rules
    protected GameGUI GUI; // The GUI of this ship

    /* constructors */

    /**
     * The constructor of space ship object, calling defaultThisAttributes method to default ship's attributes
     */
    public SpaceShip(){
        defaultThisAttributes();
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position, by using defaultThisAttributes method.
     */
    public void reset(){
        defaultThisAttributes();
    }

    // This method is called whenever this ship needed reset its attributes, at the beginning and after death.
    private void defaultThisAttributes(){
        shipPhysics = new SpaceShipPhysics();
        energy = START_ENERGY;
        maxEnergy = START_MAX_ENERGY;
        health = START_HEALTH;
        shieldsUp = false;
        firing = 0;
    }

    /**
     * Does the actions of this ship for this round. 
     * This method is abstract and implemented to each of the ship types.
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is abstract so implemented to each ship and make their moves - called any turn by doAction.
     * @param game the game object to which this ship belongs.
     */
    protected abstract void move(SpaceWars game);

    /**
     * Making a shot if this ship has enough energy and firing counter reached to zero.
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (energy >= FIRE_ENERGY && firing == 0){
            game.addShot(shipPhysics);
            minusEnergy(FIRE_ENERGY);
            firing = OUT_FIRING_ROUNDS;
        }
    }

    /**
     * Turn shield on if this ship has enough energy.
     */
    public void shieldOn() {
        if (energy >= SHIELD_ENERGY){
            shieldsUp = true;
            minusEnergy(SHIELD_ENERGY);
        }
    }

    /**
     * Teleporting if this ship has enough energy.
     */
    public void teleport() {
        if (energy >= TELEPORT_ENERGY){
            minusEnergy(TELEPORT_ENERGY);
            shipPhysics = new SpaceShipPhysics();
        }
    }

    /**
     * Gets the physics object that controls this ship.
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysics;
    }

    /**
     * update the firing counter - called from any sheep with firing ability
     */
    protected void updateFiringCount(){
        if (firing > 0){
            firing--;
        }
    }

    /**
     * add energy to the ship - called at the end of each round
     */
    protected void addEnergy(){
        if (energy < maxEnergy){
            energy++;
        }
    }

    /**
     * Gets the current ship energy
     * @return the current ship energy
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * Gets the current ship max energy
     * @return the current ship max energy
     */
    public int getMaxEnergy(){
        return maxEnergy;
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip(){
        if (shieldsUp) {
            addMaxEnergy(SHIELD_COLLISION_ENERGY);
        }
        else {
            minusHealth();
            minusMaxEnergy(HIT_ENERGY);
        }
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldsUp) {
            minusHealth();
            minusMaxEnergy(HIT_ENERGY);
        }
    }

    /**
     * Checks if this ship is dead - if health reach to zero.
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (shieldsUp){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /*
    This method is minus by one the health level (if legal).
     */
    private void minusHealth(){
        if (health > 0){
            health--;
        }
    }

    /*
    This method is called only after checking that there is enough energy, so it isn't needed to validate that
    energy is still un-negative number.
     */
    private void minusEnergy(int num){
        energy -= num;
    }

    /*
    This method is adding energy to the max level and the current level according to the input number.
     */
    private void addMaxEnergy(int num){
        maxEnergy += num;
        energy += num;
    }

    /*
    This method is minus the max energy of this ship and updating the current energy if needed.
     */
    private void minusMaxEnergy(int num){
        if (maxEnergy - num < 0){
            maxEnergy = 0;
        }
        else {
            maxEnergy -= num;
        }
        if (energy > maxEnergy){
            energy = maxEnergy;
        }
    }
}
