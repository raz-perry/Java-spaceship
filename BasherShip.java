/**
 * This class is manage basher ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class BasherShip extends SpaceShip {
	/* constants of basher ship objects */
	private final static double SHIELD_DISTANCE = 0.19;

	/**
	 * Does the actions of this ship for this round - moving and turn shields on - according to closest ship.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		shieldsUp = false;
		move(game);
		shieldOn(game);
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
	 * Called super.shieldOn to turn shield on if the closest ship is very close.
	 */
	public void shieldOn(SpaceWars game) {
		if (getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) <= SHIELD_DISTANCE){
			super.shieldOn();
		}
	}
}
