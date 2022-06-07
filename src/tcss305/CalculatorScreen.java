package tcss305;

import javax.swing.*;
import java.awt.*;

/**
 * Graphical Interface for displaying calculations for calculator
 */
public class CalculatorScreen extends JTextField {
    public CalculatorScreen(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setEditable(false);
        this.setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
