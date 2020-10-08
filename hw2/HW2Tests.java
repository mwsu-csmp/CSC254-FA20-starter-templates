import org.junit.Test;

import static org.junit.Assert.*;

public class HW2Tests {
    public static final String TEST_BOARD_1 =
                "####\n" +
                "#..#\n" +
                "D..#\n" +
                "#..#\n" +
                "##D#\n";

    @Test public void createBoard() {
        var board = new Board(TEST_BOARD_1);
        assertEquals(board.formatBoard(), TEST_BOARD_1);
    }

    @Test public void drawPlayerOnBoard() {
        var board = new Board(TEST_BOARD_1);
        final var BOARD_WITH_PLAYER =
                        "####\n" +
                        "#..#\n" +
                        "D.@#\n" +
                        "#..#\n" +
                        "##D#\n";
        var player = new Player('@');
        board.placePlayer(player,2,2);
        assertEquals(board.formatBoard(), BOARD_WITH_PLAYER);
    }

    @Test public void tilesInitiallyEmpty() {
        var board = new Board(TEST_BOARD_1);
        for(int row=0; row<5; row++) {
            for(int col=0; col<4; col++) {
                assertFalse(board.getTile(row,col).isOccupied());
            }
        }
    }

    @Test public void tilesInRightPlaces() {
        var board = new Board(TEST_BOARD_1);
        for(int row=0; row<5; row++) {
            for(int col=0; col<4; col++) {
                var tile = board.getTile(row, col);
                assertNotNull(tile);
                assertEquals(row, tile.getRow());
                assertEquals(col, tile.getColumn());
            }
        }
    }

    @Test public void placePlayerSimple() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,2,0);
        var tile = board.findPlayer(player);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
    }

    @Test public void placeGhostSimple() {
        var board = new Board(TEST_BOARD_1);
        var player = new Ghost();
        board.placePlayer(player,2,0);
        var tile = board.findPlayer(player);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
    }
    @Test public void placeGhostImpassable() {
        var board = new Board(TEST_BOARD_1);
        var player = new Ghost();
        board.placePlayer(player,0,0);
        var tile = board.findPlayer(player);
        assertEquals(0, tile.getRow());
        assertEquals(0, tile.getColumn());
    }

    @Test public void placeGhostsDoubleOccupancy() {
        var board = new Board(TEST_BOARD_1);
        var player1 = new Ghost();
        var player2 = new Ghost();
        board.placePlayer(player1,2,0);
        board.placePlayer(player2,2,0);
        var tile = board.findPlayer(player1);
        var occupants = tile.getOccupants();
        assertTrue(occupants.contains(player1));
        assertTrue(occupants.contains(player2));
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
        tile = board.findPlayer(player2);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
    }

    @Test public void placeGhostsandPlayer() {
        var board = new Board(TEST_BOARD_1);
        var player1 = new Player('@');
        var player2 = new Ghost();
        board.placePlayer(player1,2,0);
        board.placePlayer(player2,2,0);
        var tile = board.findPlayer(player1);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
        tile = board.findPlayer(player2);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
    }

    @Test public void placePlayerandPlayer() {
        var board = new Board(TEST_BOARD_1);
        var player1 = new Player('@');
        var player2 = new Player('@');
        board.placePlayer(player1,2,0);
        board.placePlayer(player2,2,0);
        var tile = board.findPlayer(player1);
        assertEquals(2, tile.getRow());
        assertEquals(0, tile.getColumn());
        tile = board.findPlayer(player2);
        assertNull(tile);
    }

    @Test public void movePlayerEastSuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,2,0);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerEast(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile2.isOccupied());
        assertFalse(tile.isOccupied());
        assertEquals(tile2.getRow(), 2);
        assertEquals(tile2.getColumn(), 1);
    }

    @Test public void movePlayerEastUnuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,1,2);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerEast(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile2.isOccupied());
        assertEquals(tile2.getRow(), 1);
        assertEquals(tile2.getColumn(), 2);
    }

    @Test public void movePlayerWestSuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,2,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerWest(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile2.isOccupied());
        assertFalse(tile.isOccupied());
        assertEquals(tile2.getRow(), 2);
        assertEquals(tile2.getColumn(), 0);
    }

    @Test public void movePlayerWestUnsuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,1,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerWest(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile2.isOccupied());
        assertEquals(tile2.getRow(), 1);
        assertEquals(tile2.getColumn(), 1);
    }

    @Test public void movePlayerWestUnsuccessful2() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,2,0);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerWest(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile2.isOccupied());
        assertEquals(tile2.getRow(), 2);
        assertEquals(tile2.getColumn(), 0);
    }

    @Test public void movePlayerSouthSuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,1,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerSouth(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile2.isOccupied());
        assertFalse(tile.isOccupied());
        assertEquals(tile2.getRow(), 2);
        assertEquals(tile2.getColumn(), 1);
    }

    @Test public void movePlayerSouthUnsuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,3,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerSouth(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile == tile2);
    }

    @Test public void movePlayerNorthSuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,2,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerNorth(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile2.isOccupied());
        assertFalse(tile.isOccupied());
        assertEquals(tile2.getRow(), 1);
        assertEquals(tile2.getColumn(), 1);
    }

    @Test public void movePlayerNorthUnsuccessful() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,1,1);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerNorth(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile == tile2);
    }

    @Test public void movePlayerSouthUnsuccessful2() {
        var board = new Board(TEST_BOARD_1);
        var player = new Player('@');
        board.placePlayer(player,4,2);
        var tile = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        board.movePlayerSouth(player);
        var tile2 = board.findPlayer(player);
        assertTrue(tile.isOccupied());
        assertTrue(tile == tile2);
    }

    // TODO (optional): add more tests, or create a main method to test with
}
