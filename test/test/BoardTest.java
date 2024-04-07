import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    // Test 1: dead cells with no live neighbors should stay dead
    @Test
    public void testDeadCellsWithNoLiveNeighbors() {
        Board board = new Board(3,3, "dead");
        int[][] expected_next_state1 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        board.nextState();
        assertArrayEquals(expected_next_state1, board.getBoard());
    }

    // Test 2: test cells on the edges of the board
    @Test
    public void testEdgeCells() {
        int[][] init_state4 = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        Board board = new Board(init_state4);
        int[][] expected_next_state4 = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        board.nextState();
        assertArrayEquals(expected_next_state4, board.getBoard());
    }

    // Test 3: test cells in the corners of the board
    @Test
    public void testCornerCells() {
        int[][] init_state5 = {
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        Board board = new Board(init_state5);
        int[][] expected_next_state5 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        board.nextState();
        assertArrayEquals(expected_next_state5, board.getBoard());
    }

}
