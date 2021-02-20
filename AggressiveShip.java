/**
 * This class is manage aggressive ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class AggressiveShip extends SpaceShip {
	/* constants of aggressive ship objects */
	private final static double FIRE_ANGLE = 0.21;

	/**
	 * Does the actions of this ship for this round - moving and firing - according to closest ship.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		move(game);
		fire(game);
		addEnergy();
	}

	/**
	 * This method is moving this ship according to the angle of the closest ship.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void move(SpaceWars game) {
		if (getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0){
			getPhysics().move(true, RIGHT);
		}
		else {
			getPhysics().move(true, LEFT);
		}
	}

	/**
	 * Called super.fire to make a shot if the closest ship is in the front angle, before its updating the
	 * firing counter.
	 * @param game the game object.
	 */
	@Override
	public void fire(SpaceWars game) {
		updateFiringCount();
		if (Math.abs(getPhysics().angleTo(game.getClosestShipTo(this).getPhysics())) < FIRE_ANGLE){
			super.fire(game);
		}
	}
}
