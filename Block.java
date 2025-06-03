import javax.swing.*; // JFrame, JPanel, JButton, JComponent
import java.awt.*; // Graphics
import java.awt.Color;
import java.awt.event.*; // ActionListener, ActionEvent
import javax.imageio.ImageIO;
import java.io.*; // File, IOException
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Block extends JPanel {
    private int value;

    // no args constructor
    public Block() {
        value = 0;
    }

    //constructor
    public Block(int value) {
        this.value = value;
    }

    //when blocks combine, doubles the value by 2
    public int doubleValue() {
        value *= 2;
        return value;
    }

    //if the value of a block is 0, then the block is empty (there is no block)
    public boolean isEmpty() {
        return value == 0;
    }

    //sets the value of a block
    public void setValue(int val) {
        value = val;
    }

    //gets the value of a block
    public int getValue() {
        return value;
    }

}
