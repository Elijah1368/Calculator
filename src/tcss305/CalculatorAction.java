package tcss305;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Actions performed by buttons in calculator class
 */
public class CalculatorAction implements ActionListener {
    CalculatorScreen screenDisplay;
    int number;
    String operation;
    JLabel currentCalculation;

    public CalculatorAction(CalculatorScreen screenDisplay, int number) {
        this.screenDisplay = screenDisplay;
        this.number = number;
    }

    public CalculatorAction(CalculatorScreen screenDisplay, JLabel currentCalculation, String operation) {
        this.screenDisplay = screenDisplay;
        this.currentCalculation = currentCalculation;
        this.operation = operation;
    }

    /**
     * Action performed for each of the buttons
     * @param e triggers the action of the button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (operation == null) {
            if (screenDisplay.getText().equals("0")) {
                return;
            }
            this.screenDisplay.setText(this.screenDisplay.getText() + number);
        } else if (operation.equalsIgnoreCase("clear")) {
            this.screenDisplay.setText("");
            this.currentCalculation.setText("");
        } else if (operation.equalsIgnoreCase("delete")) {
            int length = screenDisplay.getText().length();
            int number = length - 1;

            if (length > 0) {
                StringBuilder back = new StringBuilder(screenDisplay.getText());
                back.deleteCharAt(number);
                screenDisplay.setText(back.toString());

            }
            if (screenDisplay.getText().endsWith("")) {
                currentCalculation.setText("");
            }
        } else if (operation.equalsIgnoreCase("decimal")) {
            if (screenDisplay.getText().contains(".")) {
                return;
            } else {
                screenDisplay.setText(screenDisplay.getText() + ".");
            }

        }
    }
}
