/** game board on which players can move around on terrain */
public class Board {
    private final Tile[][] board;

    public Board(String layout) {
        String[] lines = layout.split("\n");  // split input map in to lines

        //create matrix for holding tiles
        board = new Tile[lines.length][lines[0].length()];

        // fill matrix with Tile objects
        for(int row=0; row<lines.length; row++) {
            for(int col=0; col<lines[row].length(); col++) {
                board[row][col] = new Tile(row, col, lines[row].charAt(col));
            }
        }
    }

    /** returns a tile object at the specified location on the board */
    public Tile getTile(int row, int col) {
        if(row < board.length && row >= 0 && col < board[row].length && col >= 0) {
            return board[row][col];
        }
        return null;
    }

    /** places a player at the specified location on the board */
    public void placePlayer(Player player, int row, int col) {
        if(row < board.length && row >= 0 && col < board[row].length && col >= 0) {
            board[row][col].occupy(player);
        }
    }

    /** finds the tile a player is currently occupying */
    public Tile findPlayer(Player player) {
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[0].length; col++) {
                // search every row / column for player
                if(board[row][col].isOccupied() &&
                        board[row][col].getOccupant() == player)
                    return board[row][col]; // found the player at this row/col
            }
        }
        return null;  // couldn't find the player, fall back to null
    }

    /** draws the state of this board */
    public String formatBoard() {
        StringBuffer sb = new StringBuffer();
        for(int row = 0; row < board.length; row++) {
            for(int col=0; col < board[0].length; col++) {
                var tile = board[row][col];
                if(tile.isOccupied())
                    sb.append(tile.getOccupant().getSymbol());
                else
                    sb.append(tile.getSymbol());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /** if possible, moves the player north on the board */
    public void movePlayerNorth(Player player) {
        // TODO: implement
    }

    /** if possible, moves the player north on the board */
    public void movePlayerSouth(Player player) {
        // TODO: implement
    }

    /** if possible, moves the player north on the board */
    public void movePlayerEast(Player player) {
        // TODO: implement
    }

    /** if possible, moves the player north on the board */
    public void movePlayerWest(Player player) {
        // TODO: implement
    }
}
