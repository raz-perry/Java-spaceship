import oop.ex2.*;

/**
 * This class is manage special ship objects for the SpaceWars game, inherited from SpaceShip.
 */
public class SpaceShipFactory {
    /* constant */
    private final static char HUMAN_SHIP = 'h';
    private final static char RUNNER_SHIP = 'r';
    private final static char BASHER_SHIP = 'b';
    private final static char AGGRESSIVE_SHIP = 'a';
    private final static char DRUNK_SHIP = 'd';
    private final static char SPECIAL_SHIP = 's';

    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] gameShips = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++)
        {
            switch (args[i].charAt(0)){
            case HUMAN_SHIP:
                gameShips[i] = new HumanShip();
                continue;
            case DRUNK_SHIP:
                gameShips[i] = new DrunkShip();
                continue;
            case RUNNER_SHIP:
                gameShips[i] = new RunnerShip();
                continue;
            case AGGRESSIVE_SHIP:
                gameShips[i] = new AggressiveShip();
                continue;
            case BASHER_SHIP:
                gameShips[i] = new BasherShip();
                continue;
            case SPECIAL_SHIP:
                gameShips[i] = new SpecialShip();
                continue;
            }
        }
        return gameShips;
    }
}
