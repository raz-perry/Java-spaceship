import java.util.Random;

/**
 * This class is manage drunk ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class DrunkShip extends SpaceShip {
	/* constants of drunk ship objects */
	private final static int TELEPORT_PROBABILITY = 100;
	private final static int SHIELD_PROBABILITY = 80;
	private final static int FIRE_PROBABILITY = 40;
	Random rnd = new Random();

	/**
	 * Does the actions of this ship for this round - teleporting, moving, turn shields on, firing - randomly.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		shieldsUp = false;
		teleport();
		move(game);
		shieldOn();
		fire(game);
		addEnergy();
	}

	/**
	 * This method is moving this ship, always accelerate and randomly turning left or right.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void move(SpaceWars game) {
		if (rnd.nextBoolean()){
			getPhysics().move(true, RIGHT);
		}
		else {
			getPhysics().move(true, LEFT);
		}

	}

	/**
	 * Called super.teleport to teleporting by random (probability of 1 in 100).
	 */
	@Override
	public void teleport() {
		if (rnd.nextInt(TELEPORT_PROBABILITY) == 1){
			super.teleport();
		}
	}

	/**
	 * Called super.shieldOn to turn shield on by random (probability of 1 in 80).
	 */
	@Override
	public void shieldOn() {
		if (rnd.nextInt(SHIELD_PROBABILITY) == 1){
			super.shieldOn();
		}
	}

	/**
	 * Called super.fire to make a shot by random (probability of 1 in 40).
	 * @param game the game object.
	 */
	@Override
	public void fire(SpaceWars game) {
		updateFiringCount();
		if (rnd.nextInt(FIRE_PROBABILITY) == 1){
			super.fire(game);
		}
	}
}
