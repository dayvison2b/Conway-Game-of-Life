import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeVisualizer extends JFrame {
    private static int BOARD_SIZE = 50; // Increased board size
    private static int CELL_SIZE = 10; // Decreased cell size for better visualization
    private static int WINDOW_SIZE = BOARD_SIZE * CELL_SIZE; // Size of the window

    private Board gameBoard;
    private JPanel boardPanel;
    private Timer timer;

    public GameOfLifeVisualizer(int rows, int cols, String state) {
        gameBoard = new Board(rows, cols, state);

        // Adjust cell size if grid is small
        if (rows < 10 || cols < 10) {
            CELL_SIZE = 40;
        }

        // Calculate WINDOW_SIZE based on the board size and cell size
        BOARD_SIZE = rows;
        WINDOW_SIZE = BOARD_SIZE * CELL_SIZE;

        // Initialize the frame
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + 18, WINDOW_SIZE + 43);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the board panel
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE)); // Set board panel size
        boardPanel.setBackground(Color.BLACK);

        // Add the board panel to the frame
        add(boardPanel, BorderLayout.CENTER);

        // Start the timer
        startTimer();
    }

    public GameOfLifeVisualizer(int[][] initialState) {
        // Initialize the board with the provided initial state
        gameBoard = new Board(initialState);

        // Adjust cell size if grid is small
        if (gameBoard.getBoard().length < 25 || gameBoard.getBoard()[0].length < 25) {
            CELL_SIZE = 30;
        }

        // Calculate WINDOW_SIZE based on the board size and cell size
        BOARD_SIZE = gameBoard.getBoard().length;
        WINDOW_SIZE = BOARD_SIZE * CELL_SIZE;

        // Initialize the frame
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + 18, WINDOW_SIZE + 43);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the board panel
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE)); // Set board panel size
        boardPanel.setBackground(Color.BLACK);

        // Add the board panel to the frame
        add(boardPanel, BorderLayout.CENTER);

        // Start the timer
        startTimer();
    }

    // Method to start the timer for continuous simulation
    private void startTimer() {
        timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard.nextState(); // Update the game state
                boardPanel.repaint(); // Repaint the grid
            }
        });
        timer.start(); // Start the timer
    }


    private void drawBoard(Graphics g) {
        int[][] board = gameBoard.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    g.setColor(Color.GREEN);
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
}
