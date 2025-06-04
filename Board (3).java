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
    private int score;

    //paint component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //dimensions for the tile size & spacing between tiles
        int tileSize = 100;
        int spacing = 10;

        //creating the tiles
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Block block = board[row][col];
                int x = col * (tileSize + spacing);
                int y = row * (tileSize + spacing);

                //set the image of the block based on value
                if (block.getValue() != 0) {
                    BufferedImage image = loadImage(block.getValue());
                    if (image != null)
                    {
                        g.drawImage(image, x, y, tileSize, tileSize, null);
                    }
                }
                else
                {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, tileSize, tileSize);
                }
            }
        }
    }

    // constructor
    public Board() {
        // initializes the board
        score = 0;
        board = new Block[4][4];
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                board[i][j] = new Block();
            }
        }
        // generates random row & col for the first block
        int randomRow1 = (int) (Math.random() * 4);
        int randomCol1 = (int) (Math.random() * 4);

        getBlock(randomRow1, randomCol1).setValue(2);

        // ensures that the second block is NOT placed in the same location as the first
        int randomRow2 = randomRow1;
        int randomCol2 = randomCol1;
        while (randomRow1 == randomRow2 && randomCol1 == randomCol2)
        {
            randomRow2 = (int) (Math.random() * 4);
            randomCol2 = (int) (Math.random() * 4);
        }
        getBlock(randomRow2, randomCol2).setValue(2);
        canMove = true;
    }

    //returns score
    public int getScore() {
        return score;
    }

    //returns Block
    public Block getBlock(int row, int col) {
        return board[row][col];
    }

    public void moveUp() {
        //copy current board into oldBoard
        int[][] oldBoard = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                oldBoard[r][c] = board[r][c].getValue();
            }
        }
        for (int col = 0; col < 4; col++) {
            ArrayList<Block> values = new ArrayList<>();

            //Read top to bottom: Removes all 0's off the board so that everything will be on the left
            for (int row = 0; row < 4; row++) {
                Block block = getBlock(row, col);
                if (!block.isEmpty())
                    values.add(new Block(block.getValue()));
            }

            // merges similar
            ArrayList<Block> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                if (i < values.size() - 1 && values.get(i).getValue() == values.get(i + 1).getValue()) {
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
        canMove = boardChanged(oldBoard);
    }

    public void moveDown() {
        //copy current board into oldBoard
        int[][] oldBoard = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                oldBoard[r][c] = board[r][c].getValue();
            }
        }
        for (int col = 0; col < 4; col++) {
            ArrayList<Block> values = new ArrayList<>();

            // Read bottom to top: Removes all 0's off the board so that everything will be on the left
            for (int row = 3; row >= 0; row--) {
                Block block = board[row][col];
                if (!block.isEmpty())
                    values.add(new Block(block.getValue()));
            }

            // Merge similar
            ArrayList<Block> merged = new ArrayList<>();
            int i = 0;
            while (i < values.size()) {
                if (i < values.size() - 1 && values.get(i).getValue() == values.get(i + 1).getValue()) {
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

            // 3. Pad with 0s at the top
            while (merged.size() < 4) {
                merged.add(new Block());
            }

            // 4. Write back bottom to top
            for (int x = 0; x < 4; x++) {
                board[3 - x][col].setValue(merged.get(x).getValue());
            }
        }
        //update canMove
        canMove = boardChanged(oldBoard);
    }

    public void moveLeft() {
        //copy current board into oldBoard
        int[][] oldBoard = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                oldBoard[r][c] = board[r][c].getValue();
            }
        }
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
                    merged.add(mergedBlock);
                    score += mergedBlock.getValue();
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
        canMove = boardChanged(oldBoard);
    }

    public void moveRight() {
        //copy current board into old board
        int[][] oldBoard = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                oldBoard[r][c] = board[r][c].getValue();
            }
        }

        for (int row = 0; row < 4; row++) {
            ArrayList<Block> values = new ArrayList<>();

            // Removes all 0's off the board so that everything will be on the left
            for (int column = 3; column >= 0; column--) {
                Block block = board[row][column];
                if (!block.isEmpty()) {
                    values.add(new Block(block.getValue())); // copies block into values
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
                    merged.add(mergedBlock);
                    score += mergedBlock.getValue();
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
            for (int x = 0; x < 4; x++) {
                board[row][3 - x].setValue(merged.get(x).getValue());
            }
        }
        //update canMove
        canMove = boardChanged(oldBoard);
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

    // checks if user can make any more moves (have they lost?)
    public boolean hasLost() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col].getValue() == 0) {
                    return false; //if any blocks are empty, user can still move
                }
            }
        }
        //check if user can move horizontally
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (board[x][y].getValue() == board[x][y + 1].getValue()) {
                    return false; //user can merge
                }
            }
        }

        //check if user can move vertically
        for (int l = 0; l < 4; l++) {
            for (int m = 0; m < 4; m++) {
                if (board[l][m].getValue() == board[l + 1][m].getValue()) {
                    return false; //user can merge
                }
            }
        }

        //user has no more moves left
        return true;
    }

    public boolean boardChanged(int[][] oldBoard) {
        for (int row = 0; row < 4; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                if (board[row][col].getValue() != oldBoard[row][col])
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public BufferedImage loadImage(int value) {
        try {
            return ImageIO.read(new File("images/" + value + ".png"));
        }
        catch (IOException e) {
            return null;
        }
    }
}