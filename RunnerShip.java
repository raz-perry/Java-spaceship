/**
 * This class is manage runner ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class RunnerShip extends SpaceShip {
	/* constants of runner ship objects */
	private final static double TELEPORT_DISTANCE = 0.25;
	private final static double TELEPORT_ANGLE = 0.23;

	/**
	 * Does the actions of this ship for this round - teleporting and moving - according to closest ship.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		teleport(game);
		move(game);
		addEnergy();
	}

	/**
	 * This method is moving this ship according to the angle of the closest ship.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void move(SpaceWars game) {
		if (getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0){
			getPhysics().move(true, LEFT);
		}
		else {
			getPhysics().move(true, RIGHT);
		}
	}

	/**
	 * Called super.teleport to teleporting if the closest ship is very close and in the front angle.
	 */
	public void teleport(SpaceWars game) {
		if (getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) < TELEPORT_DISTANCE
			&& Math.abs(getPhysics().angleTo(game.getClosestShipTo(this).getPhysics())) < TELEPORT_ANGLE){
			super.teleport();
		}
	}
}
