import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ConwayGamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
    private final int[][] grid;
    private final int[][] nextGrid;
    private final int width = 800;
    private final int height = 800;
    private final int cellSize = 20;
    private final int delay = 0;
    private final Timer timer;
    private Boolean start = false;

    public ConwayGamePanel() {

        this.setPreferredSize(new Dimension(width, height));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.grid = new int[width/cellSize][height/cellSize];
        this.nextGrid = new int[width/cellSize][height/cellSize];
        initGrid();

        this.timer = new Timer(delay, this);
        timer.start();

        this.setBackground(Color.black);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void initGrid() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j] = (int) Math.round(Math.random());
                nextGrid[i][j] = 0;

            }
        }
    }

    private void nextToGrid() {
        for(int i = 0; i < grid.length; i++) {
            System.arraycopy(nextGrid[i], 0, grid[i], 0, grid[0].length);
        }
    }

    private void pointUpdate(int x, int y) {
        grid[x][y] = 1;
    }

    public void updateGraphics(Graphics g) {
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 1) {
                    g.setColor(Color.white);
                    g.fillRect(r * cellSize, c * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.black);
                    g.fillRect(r * cellSize, c * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private int searchAround(int x, int y) {
        int res = 0;

        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                int r = i;
                int c = j;
                if (r == x && c == y) continue;
                if (r == grid.length) r = 0;
                if (c == grid[0].length) c = 0;
                if (r < 0) r = grid.length - 1;
                if (c < 0) c = grid[0].length - 1;
                if (grid[r][c] == 1) ++res;
            }
        }

        return res;
    }

    private int cellBehavior(int x, int y) {
       int val = searchAround(x, y);
       if (grid[x][y] == 0) {
           if (val == 3) return 1;
           return 0;
       } else {
           if (val < 2 || val > 3) return 0;
           return 1;
       }
    }

    private void updateState() {
        for(int x = 0; x <= grid.length - 1; x++) {
            for(int y = 0; y <= grid[0].length - 1; y++) {
                int r = x;
                int c = y;
                if (x == grid.length - 1) r = 0;
                if (y == grid[0].length - 1) c = 0;
                if (x == 0) r = grid.length - 1;
                if (y == 0) c= grid[0].length - 1;
                nextGrid[r][c] = cellBehavior(r, c);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateGraphics(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
        if (start) {
            updateState();
            nextToGrid();
        }
        this.requestFocusInWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX() / cellSize;
        int mouseY = e.getY() / cellSize;
        if (mouseX < grid.length && mouseX > 0 && mouseY < grid[0].length && mouseY > 0) {
            pointUpdate(mouseX, mouseY);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX() / cellSize;
        int mouseY = e.getY() / cellSize;
        if (mouseX < grid.length && mouseX > 0 && mouseY < grid[0].length && mouseY > 0) {
            pointUpdate(mouseX, mouseY);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            start = !start;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            initGrid();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
