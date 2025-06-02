import javax.swing.*; // JFrame, JPanel, JButton, JComponent
import java.awt.*; // Graphics
import java.awt.Color;
import java.awt.event.*; // ActionListener, ActionEvent
import javax.imageio.ImageIO;
import java.io.*; // File, IOException
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board extends JPanel {
    private Block[][] board;
    private boolean canMove;

    // constructor
    public Board() {
        // initializes the board
        board = new Block[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Block();
            }
        }
        // generates random row & col for the first block
        int randomRow1 = (int) (Math.random() * 4);
        int randomCol1 = (int) (Math.random() * 4);

        board[randomRow1][randomCol1].setValue(2);

        // ensures that the second block is NOT placed in the same location as the first
        int randomRow2 = randomRow1;
        int randomCol2 = randomCol1;
        while (randomRow1 == randomRow2 && randomCol1 == randomCol2) {
            randomRow2 = (int) (Math.random() * 4);
            randomCol2 = (int) (Math.random() * 4);
        }
        board[randomRow2][randomCol2].setValue(2);
        canMove = true;
    }

    public void moveUp() {
        for (int col = 0; col < 4; col++) {
            ArrayList<Block> values = new ArrayList<>();

            //Read top to bottom: Removes all 0's off the board so that everything will be on the left
            for (int row = 0; row < 4; row++) {
                Block block = board[row][col];
                if (!block.isEmpty())
                    values.add(new Block(block.getValue()));
            }

            // merges similar
            ArrayList<Block> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                if (i < values.size() - 1 && values.get(i).getValue == values.get(i + 1).getValue()) {
                    Block mergedBlock = new Block(values.get(i).getValue());
                    mergedBlock.doubleValue();
                    merged.add(mergedBlock);
                    score += mergedBlock.getValue();
                    i += 2;// Skip the next val
                // Skip the val if they are not equal but keep it on the board
                } else {
                    merged.add(new Block(values.get(i).getValue()));
                    i++;
                }
            }

            // Adds the 0's at the bottom
            while (merged.size() < 4) {
                merged.add(new Block());
            }

            // update the board w/ all remaining blocks from top to bottom
            for (int row = 0; row < 4; row++) {
                board[row][col].setValue(merged.get(row).getValue());
            }
        }
    }

    public void moveDown() {
        for (int col = 0; col < 4; col++) {
            ArrayList<Block> values = new ArrayList<>();

            // Read bottom to top: Removes all 0's off the board so that everything will be on the left
            for (int row = 3; row >= 0; row--) {
                Block block = board[row][col];
                if (!block.isEmpty())
                    values.add();
            }

            // Merge similar
            ArrayList<Integer> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                if (i < values.size() - 1 && values.get(i).equals(values.get(i + 1))) {
                    int mergedVal = values.get(i) * 2;
                    merged.add(mergedVal);
                    score += mergedVal;
                    i += 2;// Skip the next val
                // Skip the val if they are not equal but keep it on the board
                } else {
                    merged.add(values.get(i));
                    i++;
                }
            }

            // 3. Pad with 0s at the top
            while (merged.size() < 4) {
                merged.add(0, 0);
            }

            // 4. Write back bottom to top
            for (int row = 3, i = 0; row >= 0; row--, i++) {
                board[row][col].setValue(merged.get(i));
            }
        }
    }

    public void moveLeft() {
        for (int row = 0; row < 4; row++) {
            ArrayList<Block> values = new ArrayList<>();

            // Removes all 0's off the board so that everything will be on the left
            for (int column = 0; column < 4; column++) {
                Block block = board[row][column];
                if (!block.isEmpty()) {
                    values.add(new Block(block.getValue()));
                }
            }

            // merges similar
            ArrayList<Block> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                // When the same
                if (i < values.size() - 1 && values.get(i).getValue() == values.get(i + 1).getValue()) {
                    Block mergedBlock = new Block(values.get(i).getValue());
                    mergedBlock.doubleValue();
                    merged.add(mergedBlock;
                    score += mergedBlock.getValue()
                    i += 2;// Skip the next val
                    // Skip the val if they are not equal but keep it on the board
                } else {
                    merged.add(values.get(i));
                    i++;
                }
            }

            // Adds the 0's at the top
            while (merged.size() < 4) {
                merged.add(new Block());
            }

            // update
            for (int col = 0; col < 4; col++) {
                board[row][col].setValue(merged.get(col).getValue());
            }
        }
    }

    public void moveRight() {
        for (int row = 0; row < 4; row++) {
            ArrayList<Block> values = new ArrayList<>();

            // Removes all 0's off the board so that everything will be on the left
            for (int column = 3; column >= 0; column--) {
                Block block = board[row][column].getValue();
                if (!block.isEmpty()) {
                    values.add(new Block(b.getValue())); // copies block into values
                }
            }

            // merges similar
            ArrayList<Block> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                // When the same
                if (i < values.size() - 1 && values.get(i).getValue() == values.get(i + 1).getValue()) {
                    Block mergedBlock = values.get(i).getValue();
                    mergedBlock.doubleValue();
                    merged.add(mergedBlock);
                    score += mergedVal;
                    i += 2;// Skip the next val
                    // Skip the val if they are not equal but keep it on the baord
                } else {
                    merged.add(values.get(i));
                    i++;
                }
            }

            // adds 0's to the places that are empty on the board
            while (merged.size() < 4) {
                merged.add(new Block());
            }

            // update the board
            for (int col = 3; col >= 0; col--) {
                board[row][col].setValue(merged.get(col).getValue());
            }
        }
    }

    public void addBlock() {
        if (hasLost() == false) {
            int randRow = 0;
            int randCol = 0;
            while (board[randRow][randCol].getValue() != 0) {
                randRow = (int) (Math.random() * 4);
                randCol = (int) (Math.random() * 4);
            }
            board[randRow][randCol].setValue(2);
        }
    }

    // uses the boolean variable canMove to see if the user can make any more moves
    // if they can move, returns false. if they can't move, returns true
    public boolean hasLost() {
        return !canMove;
    }

    
}
