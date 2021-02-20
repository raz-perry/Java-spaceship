/**
 * This class is manage special ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class SpecialShip extends SpaceShip {
	/* constants of special ship objects */
	private final static double TELEPORT_DISTANCE = 0.15;

	/**
	 * Does the actions of this ship for this round - teleporting, moving, turn shields on, firing - according
	 * to closest ship and energy level.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		shieldsUp = false;
		teleport(game);
		move(game);
		shieldOn(game);
		fire(game);
		addEnergy();
	}

	/**
	 * This method is moving this ship according to the angle of the closest ship and the energy level.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void move(SpaceWars game) {
		if (getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0 && getEnergy() >
																				  getMaxEnergy()/2){
			getPhysics().move(true, RIGHT);
		}
		else if (getEnergy() > getMaxEnergy()/2){
			getPhysics().move(true, LEFT);
		}
		else if (getPhysics().angleTo(game.getClosestShipTo(this).getPhysics()) < 0){
			getPhysics().move(true, LEFT);
		}
		else {
			getPhysics().move(true, RIGHT);
		}
	}

	/**
	 * Called super.teleport to teleporting if the closest ship is very close.
	 */
	public void teleport(SpaceWars game) {
		if (getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) < TELEPORT_DISTANCE){
			super.teleport();
		}
	}

	/**
	 * Called super.shieldOn to turn shield on if the closest ship is very close and this ship has enough
	 * energy to fight and not run away.
	 */
	public void shieldOn(SpaceWars game) {
		if (getPhysics().distanceFrom(game.getClosestShipTo(this).getPhysics()) < 0.2 && getEnergy() >
																						 getMaxEnergy()/3){
			super.shieldOn();
		}
	}

	/**
	 * Called super.fire to make a shot if the closest ship is in the front angle and this ship has a lot of
	 * energy. Before its updating the firing counter.
	 * @param game the game object.
	 */
	@Override
	public void fire(SpaceWars game) {
		updateFiringCount();
		if (Math.abs(getPhysics().angleTo(game.getClosestShipTo(this).getPhysics())) < 1 && getEnergy() >
																						getMaxEnergy()/1.5){
			super.fire(game);
		}
	}
}
