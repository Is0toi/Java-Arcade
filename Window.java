import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JPanel implements JavaArcade, MouseListener {
    private Board board;
    private JPanel boardPanel;
    private String name = "2048 CATS";
    private String author = "Jessie & Lasya";
    private int score;
    private boolean isRunning;
    /*
     * public Game() {
     * super("2048 cats");
     * }
     */

    /*
     * This method should return true if your game is in a "start" state, it should
     * return false if
     * your game is in a "paused" state or "stopped" or "unstarted"
     */

    public Window()
    {
            setTitle("2048 CATS");

        }

        public boolean running() {
            // if (START.BUTTON)
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
            return ("Welcome to Cat's 2048\n This is a little different than normal 2048. THERE ARE " +
                    "CATS! and moreover there are power ups:\n 1. Duplicate(which can be useful to " +
                    "duplicate right at 1024)\n 2. Replace (Where you replace a a block with a " +
                    "number present on the board) \n 3.Undo (Where you can undo a mistake which can " +
                    "happen twice) \n Overall, there are no rules to 2048 so ultimately have fun! " +
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
            return 0;

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
            JFrame frameInstruc = new JFrame("2048 CATS hehe");
            frameInstruc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameInstruc.setSize(300, 200);
            frameInstruc.setVisible(true);
            JOptionPane.showMessageDialog(
                    frameInstruc,
                    "Welcome to Cat's 2048\n This is a little different than normal 2048. THERE ARE " +
                            "CATS! and moreover there are power ups:\n 1. Duplicate(which can be useful to " +
                            "duplicate right at 1024)\n 2. Replace (Where you replace a a block with a number " +
                            "present on the board) \n 3.Undo (Where you can undo a mistake which can happen " +
                            "twice) \n Overall, there are no rules to 2048 so ultimately have fun! Bye Bye! " +
                            "(Meow Meow)",
                    "Instructions", JOptionPane.INFORMATION_MESSAGE);

            JFrame frame = new JFrame("2048 CATS!!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 1000);

            Window panel = new Window();
            // frame.add(panel);
            frame.setVisible(true);

            isRunning = false;
            JButton startButton = new JButton("Start Game");
            startButton.setBounds(100, 100, 15, 15);

            /*
             * JTextField txtfld = new JTextField(20);
             * txtfld.addKeyListener(this);
             * add(txtfld);
             */

            frame.paintComponent(panel);

        }
}
