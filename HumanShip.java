import oop.ex2.GameGUI;
import java.awt.*;

/**
 * This class is manage human ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class HumanShip extends SpaceShip {
	/**
	 * Does the actions of this ship for this round - teleporting, moving, turn shields on, firing - according
	 * to user inputs.
	 * This is called once per round by the SpaceWars game driver.
	 *
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		GUI = game.getGUI();
		shieldsUp = false;
		teleport();
		move(game);
		shieldOn();
		fire(game);
		addEnergy();
	}

	/**
	 * This method is moving this ship - checking if the user want to accelerate and in which direction he
	 * want to turn.
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void move(SpaceWars game) {
		boolean accel = false;
		if (GUI.isUpPressed()){
			accel = true;
		}
		if (GUI.isLeftPressed() && !GUI.isRightPressed()){
			getPhysics().move(accel, LEFT);
		}
		else if (GUI.isRightPressed() && !GUI.isLeftPressed()){
			getPhysics().move(accel, RIGHT);
		}
		else {
			getPhysics().move(accel, NO_TURN);
		}
	}

	/**
	 * Called super.teleport to teleporting if the user want to do it.
	 */
	@Override
	public void teleport() {
		if (GUI.isTeleportPressed()){
			super.teleport();
		}
	}

	/**
	 * Called super.shieldOn to turn shield on if the user want to do it.
	 */
	@Override
	public void shieldOn() {
		if (GUI.isShieldsPressed()){
			super.shieldOn();
		}
	}

	/**
	 * Called super.fire to make a shot if the user want to take one, before its updating the firing counter.
	 * @param game the game object.
	 */
	@Override
	public void fire(SpaceWars game) {
		updateFiringCount();
		if (GUI.isShotPressed()){
			super.fire(game);
		}
	}

	/**
	 * Gets the image of this ship according to the shield.This will be displayed on the GUI at the end of
	 * the round.
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		if (shieldsUp){
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		}
		else {
			return GameGUI.SPACESHIP_IMAGE;
		}
	}
}
