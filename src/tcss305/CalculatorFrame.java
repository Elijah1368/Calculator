package tcss305;

import javax.swing.*;

/*
Calculator Frame
 */
public class CalculatorFrame extends JFrame {
    public CalculatorFrame(String title, int width, int height) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
    }
}
