import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements JavaArcade, MouseListener, KeyListener {
    private Board board;
    private String name = "2048 CATS";
    private String author = "Jessie & Lasya";
    private int score;
    private static boolean isRunning;

    //label variables
    private JLabel scoreLabel;
    private JLabel currentBlock;
    private JLabel nextBlock;
    private JLabel currentCatLabel;
    private JLabel nextCatLabel;

    /*
     * This method should return true if your game is in a "start" state, it should
     * return false if
     * your game is in a "paused" state or "stopped" or "unstarted"
     */

    public Window() {
        super("CATS 2048");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(board, BorderLayout.CENTER);
        panel.add(makeSidePanel(), BorderLayout.EAST);
        add(panel);


        board.addKeyListener(this);
        board.setFocusable(true);
        setVisible(true);
        board.requestFocusInWindow();

        //This makes it so keys can work

    }

    public JPanel makeSidePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setPreferredSize(new Dimension(200, 500));
        newPanel.setBackground(Color.RED);

        //labels
        scoreLabel = new JLabel("Score: 0");
        currentBlock = new JLabel("Current Block: 2");
        nextBlock = new JLabel("Next Block: ");

        //image label (shows the current cat)
        currentCatLabel = new JLabel(new ImageIcon("images/2.png"));
        nextCatLabel = new JLabel(new ImageIcon("images/4.png"));
       /* ImageIcon currentIcon = new ImageIcon("images/2.png");
        Image currentImage = currentIcon.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH);
        currentCatLabel = new JLabel(new ImageIcon(currentImage));

        ImageIcon nextIcon = new ImageIcon("images/4.png");
        Image nextImage = nextIcon.getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH);
        nextCatLabel = new JLabel(new ImageIcon(nextImage));*/

        //adding to panel
        newPanel.add(scoreLabel);
        newPanel.add(currentBlock);
        newPanel.add(nextBlock);
        newPanel.add(currentCatLabel);
        newPanel.add(nextCatLabel);

        return newPanel;
    }

    public boolean running() {
        // if (START.BUTTON)
        return false; // for now to fix the error
    }

        /*
         * This method should start your game, it should also set a global boolean value
         * so that your running method
         * can return the appropriate value
         */

    public void startGame() {
        score = 0;
            /*
             * static boolean isRunning = running();
             * return isRunning;
             */
     }

        /* This method should return the name of your game */
    public String getGameName()
    {
        return name;
    }

        /*
         * This method should stop your timers but save your score, it should set a
         * boolean value to indicate
         * the game is not running, this value will be returned by running method
         */

        public void pauseGame() {
            isRunning = false;
            int savedScore = score;
        }

        /* This method should return your instructions */
        public String getInstructions() {
            return ("Welcome to Cat's 2048\n In this game, instead of numbers, each value is associated with a certain cat!\n Use  the arrow keys to combine similar looking cats! As the cats combine, the cats get bigger and fatter! (hehehe).\n Overall, there are no rules to 2048 so ultimately have fun! " +
                    "Bye Bye! (Meow Meow)");
        }

        public String getCredits() {
            return author;
        }

        /* This method should return the highest score played for this game */
        public String getHighScore() {
            return "";
        }

        /*
         * This method should stop the timers, reset the score, and set a running
         * boolean value to false
         */
        public void stopGame() {

        }

        /* This method shoud return the current players number of points */

        public int getPoints() {
            return board.getScore();

        } // add to spec

        /*
         * This method provides access to GameStats display for UserPanel to pass
         * information to update score
         * GameStats is created in Arcade, a reference should be passed to UserPanel
         * (main panel) to update poionts
         */
        public void setDisplay(GameStats d) {

        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.PINK);
            g.fillRect(0, 0, 50, 50);
        }

        public static void main(String[] args) {
            JOptionPane.showMessageDialog(
                    null,
                    "Welcome to Cat's 2048\n In this game, instead of numbers, each value is associated with a certain cat! Use the arrow keys to combine similar looking cats! As the cats combine, the cats get bigger and fatter! (hehehe)Overall, there are no rules to 2048 so ultimately have fun! " +
                    "Bye Bye! (Meow Meow)",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);

            new Window();
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //Used cases bc it works better when doing logic supposedly?
            //ADD CASES FOR WASD 
            switch(key){
                case KeyEvent.VK_UP:
                    board.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    board.moveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    board.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    board.moveRight();
                    break;
            }

            // adds block after movement
            if (board.getCanMove())
                board.addBlock();

            //update labels!!
            scoreLabel.setText("Score: " + board.getScore());
            currentBlock.setText("Highest Block: " + board.getCurrentHighestValue());
            nextBlock.setText("Next Block: " + board.getNextValue());
            currentCatLabel.setIcon(new ImageIcon("images/" + board.getCurrentHighestValue() + ".png"));
            nextCatLabel.setIcon(new ImageIcon("images/" + board.getNextValue() + ".png"));

            //Refreshes display to show new numbers
            if (board.hasLost())
                JOptionPane.showMessageDialog(null, "Game Over! Final Score: " + board.getScore());
            board.repaint();
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
}
