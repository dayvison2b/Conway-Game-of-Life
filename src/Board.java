import java.util.Random;

public class Board {
    private final int rows;
    private final int cols;
    private int[][] board;

    public Board(int rows, int cols, String initialState) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];

        if (initialState.equals("random")) {
            initializeRandomBoard();
        }
    }

    public Board(int[][] initialState) {
        this.rows = initialState.length;
        this.cols = initialState[0].length;
        this.board = new int[rows][cols];

        initializeCustomBoard(initialState);
    }

    private void initializeRandomBoard() {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = random.nextInt(2); // Generates random integers 0 or 1
            }
        }
    }

    private void initializeCustomBoard(int[][] initialState) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = initialState[i][j];
            }
        }
    }

    public void nextState() {
        int[][] nextBoard = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cellState = this.board[i][j];
                int cellNeighbors = neighbors(i, j);

                // Game of Life rules
                if (cellState == 1 && (cellNeighbors < 2 || cellNeighbors > 3)) {
                    nextBoard[i][j] = 0; // Cell dies
                } else if (cellState == 0 && cellNeighbors == 3) {
                    nextBoard[i][j] = 1; // Cell reproduces
                } else {
                    nextBoard[i][j] = cellState; // Cell stays the same
                }
            }
        }

        this.board = nextBoard;
    }

    public int neighbors(int row, int col) {
        int count = 0;
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < 8; i++) {
            int x = (row + dx[i] + rows) % rows; // Wrap around the rows
            int y = (col + dy[i] + cols) % cols; // Wrap around the columns

            if (this.board[x][y] == 1) {
                count++;
            }
        }

        return count;
    }

    public int[][] getBoard() {
        return this.board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // ASCII representation of block elements
        char deadCell = '□'; // White square (□)
        char liveCell = '■'; // Black square (■)

        for (int[] row : board) {
            for (int cell : row) {
                // Append the appropriate character based on the cell state
                sb.append(cell == 1 ? liveCell : deadCell);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}