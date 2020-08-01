/** represents a single tile on a board that can hold a single player (or none) */
public class Tile {

    /** all possible symbols for tiles */
    public static final char[] SYMBOLS = {
            '.',            // grass
            '#',            // wall
            '*',            // boulder
            'D',            // Door
    };

    /** descriptions of tiles with symbols at the same index in the SYMBOLS field */
    public static final String[] DESCRIPTIONS = {
            "grass",
            "wall",
            "boulder",
            "door",
    };

    /** determines whether a tile of the given type can be occupied or not.
     *  again, matches up with SYMBOLS field
     */
    public static final boolean[] CAN_BE_OCCUPIED = {
            true,
            false,
            false,
            true
    };

    private final int row;
    private final int col;
    private final char symbol;
    private Player player = null;

    public Tile(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() { return row; }
    public int getColumn() { return col; }
    public char getSymbol() { return symbol; }

    /** describes this tile */
    public String getDescription() {
        for(int i = 0; i< SYMBOLS.length; i++) {
            if(SYMBOLS[i] == symbol) {
                return DESCRIPTIONS[i];
            }
        }
        return null;
    }

    /** returns the player occupying this tile */
    public Player getOccupant() {
        return player;
    }

    /** determines whether or not a player is occupying this tile */
    public boolean isOccupied() {
        return getOccupant() != null;
    }

    /** the given player will occupy the tile if possible */
    public void occupy(Player player) {
        if(canBeOccupied()) {
            this.player = player;
        }
    }

    /** if this given player is occupying this tile, it will become unoccupied */
    public void leaveTile(Player player) {
        if(getOccupant() == player) {
            this.player = null;
        }
    }


    /** determines whether or not this tile can be occupied */
    public boolean canBeOccupied() {
        if(isOccupied())
            return false; // tile cannot be occupied by more than one player

        // determine if terrain is appropriate for occupation
        for(int i = 0; i< SYMBOLS.length; i++) {
            if(SYMBOLS[i] == symbol) {
                return CAN_BE_OCCUPIED[i];
            }
        }
        return false; // default to unoccupiable
    }

}
