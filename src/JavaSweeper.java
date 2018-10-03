import javax.swing.*;
import java.awt.*;

public class JavaSweeper extends JFrame {
    private JLabel label;
    private Game game;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(Statics.COLS, Statics.ROWS);
        game.start();
        initPanel();
        initLabel();
        initFrame();
    }

    protected void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initPanel() {
        add(game.getPanel());
    }

    private void initLabel() {
        label = new JLabel("Найди бомбы!");
        add (label, BorderLayout.SOUTH);
    }
}
