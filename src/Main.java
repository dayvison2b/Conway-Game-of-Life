import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // optional
        int[][] initialState = new int[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        SwingUtilities.invokeLater(() -> new GameOfLifeVisualizer(50, 50, "random").setVisible(true));
    }
}
