public class ControlPanel extends JPanel implements ActionListener {
  private JavaArcade game;
  private GameStats gStats;
  private JButton startButton, pauseButton, stopButton, instructionsButton, creditsButton;
  private boolean isPaused = false;

  public ControlPanel(JavaArcade t, GameStats g) {
    game = t;
    gStats = g;

    instructionsButton = new JButton("Instructions");
    instructionsButton.addActionListener(this);
    add(instructionsButton);
    add(Box.createHorizontalStrut(80));

    startButton = new JButton("Start");
    startButton.addActionListener(this);
    add(startButton);

    pauseButton = new JButton("Pause");
    pauseButton.addActionListener(this);
    pauseButton.setEnabled(false);  // disabled until game starts
    add(pauseButton);

    stopButton = new JButton("Stop");
    stopButton.addActionListener(this);
    stopButton.setEnabled(false);  // disabled until game starts
    add(stopButton);

    add(Box.createHorizontalStrut(80));
    creditsButton = new JButton("Credits");
    creditsButton.addActionListener(this);
    add(creditsButton);
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton)e.getSource();

    if (button == startButton) {
      if (!game.running()) {
        game.startGame();
        isPaused = false;
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        gStats.update(0);
        gStats.repaint();
      }
    } else if (button == pauseButton) {
      if (isPaused) {
        game.startGame(); // Resume
        pauseButton.setText("Pause");
        isPaused = false;
      } else {
        game.pauseGame();
        pauseButton.setText("Resume");
        isPaused = true;
      }
    } else if (button == stopButton) {
      game.stopGame();
      gStats.gameOver(game.getPoints());
      gStats.repaint();
      startButton.setText("Restart");
      startButton.setEnabled(true);
      pauseButton.setEnabled(false);
      stopButton.setEnabled(false);
      pauseButton.setText("Pause");
      isPaused = false;
    } else if (button == creditsButton) {
      String credits = game.getCredits();
      JOptionPane.showMessageDialog(this, credits, "Game Credits", JOptionPane.PLAIN_MESSAGE);
    } else if (button == instructionsButton) {
      String instructions = game.getInstructions();
      JOptionPane.showMessageDialog(this, instructions, "Game Rules", JOptionPane.PLAIN_MESSAGE);
    }

    ((JPanel)(game)).requestFocus();
  }
}