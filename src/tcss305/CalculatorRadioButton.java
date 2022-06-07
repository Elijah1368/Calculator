package tcss305;

import javax.swing.*;
import java.awt.*;

/**
 * Custom radio buttons for calculator class
 */
class CalculatorRadioButton extends JRadioButton {
    public CalculatorRadioButton(String string){
        this.setFont(new Font("Arial", Font.BOLD, 10));
        this.setText(string);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }
}