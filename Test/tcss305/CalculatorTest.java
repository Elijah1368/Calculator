package tcss305;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static java.lang.Double.NaN;

class CalculatorTest {
    Calculator test = new Calculator();
    CalculatorScreen screenDisplay = test.screenDisplay;
    Double floatNumber  = test.floatNumber;
    Double floatAnswer  = test.floatAnswer;
    int calculation = 0;
    JLabel currentCalculation = test.currentCalculation;

    @Test
    void add() {
        Assertions.assertEquals(15.8, test.add(5.3, 10.5));
        Assertions.assertEquals(10.5, test.add(0, 10.5));
        Assertions.assertEquals(0, test.add(500, -500));

        Assertions.assertEquals(10, test.add(5, 5));
        Assertions.assertEquals(1000, test.add(500, 500));
        Assertions.assertEquals(-500, test.add(1000, -1500));
        Assertions.assertEquals(0, test.add(1000, -1000));
    }

    @Test
    void subtract() {
        Assertions.assertEquals(117.5, test.subtract(140, 22.5));
        Assertions.assertEquals(-10.5, test.subtract(0, 10.5));
        Assertions.assertEquals(1000, test.subtract(500, -500));

        Assertions.assertEquals(50, test.subtract(100, 50));
        Assertions.assertEquals(-300, test.subtract(0, 300));
        Assertions.assertNotEquals(50, test.subtract(50, -1500));
    }

    @Test
    void multiply() {
        Assertions.assertEquals(0.008, test.multiply(.002, 4));
        Assertions.assertEquals(138.25, test.multiply(55.3, 2.5));
        Assertions.assertEquals(-138.25, test.multiply(55.3, -2.5));

        Assertions.assertEquals(80, test.multiply(40, 2));
        Assertions.assertEquals(100, test.multiply(10, 10));
        Assertions.assertEquals(-2000, test.multiply(1000, -2));
        Assertions.assertEquals(0, test.multiply(1000, 0));
    }

    @Test
    void divide() {
        Assertions.assertEquals(20.833333333333336, test.divide(50, 2.4));
        Assertions.assertEquals(-7.03125, test.divide(-225, 32));
        Assertions.assertEquals(0.0, test.divide(0, 5));

        Assertions.assertEquals(1, test.divide(5, 5));
        Assertions.assertEquals(60.666666666666664, test.divide(182, 3));
        Assertions.assertEquals(Double.POSITIVE_INFINITY, test.divide(182, 0));
    }


    @Test
    void integerEquals() {
        Assertions.assertEquals(55, 55);
        Assertions.assertNotEquals(100, 22);
        Assertions.assertNotEquals(-500, 500);
    }

    @Test
    void doubleEquals() {
        Assertions.assertEquals(55.0, 55.0);
        Assertions.assertNotEquals(100, 33.0);
        Assertions.assertNotEquals(-500, 500);
    }

    @Test
    void integerDivide() {
        Assertions.assertEquals(3, test.integerDivide(10, 3));
        Assertions.assertEquals(10, test.integerDivide(100, 10));
        Assertions.assertEquals(-2, test.integerDivide(1000, -500));
        Assertions.assertThrows(ArithmeticException.class, () ->  {test.integerDivide(1000, 0);});
    }

    @Test
    void switchFloatingPointCalculator() {
        Assertions.assertEquals("int", test.switchFloatingPointCalculator("float"));
        Assertions.assertEquals("float", test.switchFloatingPointCalculator("int"));
    }

    @Test
    void switchIntegerCalculator() {
        Assertions.assertEquals("int", test.switchFloatingPointCalculator("float"));
        Assertions.assertEquals("float", test.switchFloatingPointCalculator("int"));
    }

    @Test
    void logCurrent() {
        Boolean isUnary = true;

        test.logging = new StringBuilder("");

        screenDisplay.setText("1");

        test.logCurrent("+");
        Assertions.assertEquals("1 + ", test.logging.toString());

        isUnary = false;
        test.logCurrent("+");
        Assertions.assertEquals("1 + 1 + ", test.logging.toString());
    }

    @Test
    void actionPerformed() {
        /**
         *  Calculator is in Float Point Logic
         */
        test.buttonOne.doClick();
        Assertions.assertEquals("1", screenDisplay.getText());
        test.buttonTwo.doClick();
        Assertions.assertEquals("12", screenDisplay.getText());
        test.buttonThree.doClick();
        Assertions.assertEquals("123", screenDisplay.getText());
        test.buttonFour.doClick();
        Assertions.assertEquals("1234", screenDisplay.getText());
        test.buttonFive.doClick();
        Assertions.assertEquals("12345", screenDisplay.getText());
        test.buttonSix.doClick();
        Assertions.assertEquals("123456", screenDisplay.getText());
        test.buttonSeven.doClick();
        Assertions.assertEquals("1234567", screenDisplay.getText());
        test.buttonEight.doClick();
        Assertions.assertEquals("12345678", screenDisplay.getText());
        test.buttonNine.doClick();
        Assertions.assertEquals("123456789", screenDisplay.getText());

        screenDisplay.setText("12345.1");
        test.buttonDelete.doClick();
        Assertions.assertEquals("12345.", screenDisplay.getText());

        screenDisplay.setText("123.305");
        test.buttonClear.doClick();
        Assertions.assertEquals("", screenDisplay.getText());

        //sets current entered number to 100
        screenDisplay.setText("100");
        test.buttonClear.doClick();
        Assertions.assertEquals("", screenDisplay.getText());

        //subtract 10 - 1
        screenDisplay.setText("10");
        test.buttonSubtract.doClick();
        test.buttonOne.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("9", screenDisplay.getText());

        //add 10 + 1
        screenDisplay.setText("10");
        test.buttonAdd.doClick();
        test.buttonOne.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("11", screenDisplay.getText());

        //divide 10 / 2
        screenDisplay.setText("10");
        test.buttonDivide.doClick();
        test.buttonTwo.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("5", screenDisplay.getText());

        //multiply 10 * 2
        screenDisplay.setText("10");
        test.buttonMultiply.doClick();
        test.buttonTwo.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("20", screenDisplay.getText());

        // 122.5 ^ 2
        screenDisplay.setText("122.5");
        test.buttonSquare.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("15006.25", screenDisplay.getText());

        // -122.5 ^ 2
        screenDisplay.setText("122.5");
        test.buttonSquare.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("15006.25", screenDisplay.getText());

        // sqrt(17)
        screenDisplay.setText("17");
        test.buttonSquareRoot.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("4.123105625617661", screenDisplay.getText());

        // sqrt(-100)
        screenDisplay.setText("-100");
        test.buttonSquareRoot.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals(Double.toString(NaN), screenDisplay.getText());

        // reciprocal of 4
        screenDisplay.setText("4");
        test.buttonReciprocal.doClick();
        Assertions.assertEquals("0.25", screenDisplay.getText());

        //Operation out of order
        screenDisplay.setText("10.0");
        test.buttonMultiply.doClick();
        test.buttonMultiply.doClick();

        /**
         *  Set Calculator into Integer Logic
         *
         */
        screenDisplay.setText("100");
        test.buttonClear.doClick();
        Assertions.assertEquals("", screenDisplay.getText());

        test.integerRadioButton.doClick();
        Assertions.assertEquals("int", test.floatOrInt);

        //sqrt 100
        screenDisplay.setText("100");
        test.buttonSquareRoot.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("10", screenDisplay.getText());

        //non-perfect square root
        screenDisplay.setText("10");
        test.buttonSquareRoot.doClick();

        // 10 ^ 2
        screenDisplay.setText("10");
        test.buttonSquare.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("100", screenDisplay.getText());

        //subtract 10 - 1
        screenDisplay.setText("10");
        test.buttonSubtract.doClick();
        test.buttonOne.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("9", screenDisplay.getText());

        //good reciprocal test
        screenDisplay.setText("1");
        test.buttonReciprocal.doClick();
        Assertions.assertEquals("1", screenDisplay.getText());

        //bad reciprocal test
        screenDisplay.setText("3");
        test.buttonReciprocal.doClick();
        //dialogue window pop up

        //add 10 + 1
        screenDisplay.setText("10");
        test.buttonAdd.doClick();
        test.buttonOne.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("11", screenDisplay.getText());

        //divide 5 / 2
        screenDisplay.setText("5");
        test.buttonDivide.doClick();
        test.buttonTwo.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("2", screenDisplay.getText());

        //multiply 10 * 2
        screenDisplay.setText("10");
        test.buttonMultiply.doClick();
        test.buttonTwo.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("20", screenDisplay.getText());

        // 10 ^ 2
        screenDisplay.setText("10");
        test.buttonSquare.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("100", screenDisplay.getText());

        //sqrt 100
        screenDisplay.setText("100");
        test.buttonSquareRoot.doClick();
        test.buttonEquals.doClick();
        Assertions.assertEquals("10", screenDisplay.getText());

        //pressOutOfOrder
        test.buttonSquareRoot.doClick();
        test.buttonSquareRoot.doClick();

        //throw integer out of rangee
        screenDisplay.setText("100000000000000000000000000000");
        test.buttonAdd.doClick();
    }

}