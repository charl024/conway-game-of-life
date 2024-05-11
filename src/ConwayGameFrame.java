import javax.swing.*;
import java.awt.*;

public class ConwayGameFrame extends JFrame {

    public ConwayGameFrame() {
        super("Conway's Game of Life Clone");

        ConwayGamePanel panel = new ConwayGamePanel();

        this.setLayout(new BorderLayout());

        this.getContentPane().add(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new ConwayGameFrame();
    }
}
