package tcss305;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Graphical Interface for buttons for the calculator class
 */
class CalculatorButton extends JButton {

    public CalculatorButton(String text, int width, int height){
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        this.setPreferredSize(new Dimension(width, height));
    }

    public CalculatorButton(String text, Color bgColor, Color fgColor, int width, int height){
        this(text);
        this.setBackground(bgColor);
        this.setForeground(fgColor);
        this.setPreferredSize(new Dimension(width, height));
    }

    public CalculatorButton(String text){
        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    }

    public CalculatorButton(String text, Color bgColor, Color fgColor){
        this(text);
        this.setBackground(bgColor);
        this.setForeground(fgColor);
    }
}