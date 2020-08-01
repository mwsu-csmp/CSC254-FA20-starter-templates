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

    // TODO (optional): add more tests, or create a main method to test with
}
