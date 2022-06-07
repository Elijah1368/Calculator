package tcss305;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.border.EmptyBorder;

/**
Calculator made in Java Swing for tcss 305
Elijah Amian
 */

public class Calculator implements ActionListener {
    //dimensions of the calculator buttons and frames
    private final int CALCULATOR_WIDTH = 320;
    private final int CALCULATOR_HEIGHT = 500;
    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_HEIGHT = 40;
    private final int BUTTON_HEIGHT_2 = 103;
    private final int BUTTON_SPACE_HORIZONTAL = 10;
    private final int BUTTON_SPACE_VERTICAL = 20;    
    private final int CALCULATOR_SCREEN_WIDTH = 270;
    private final int CALCULATOR_SCREEN_HEIGHT = 40;
    

    //Calculator buttons
    CalculatorRadioButton floatingPointButton;
    CalculatorRadioButton integerRadioButton;
    CalculatorButton buttonZero;
    CalculatorButton buttonOne;
    CalculatorButton buttonTwo;
    CalculatorButton buttonThree;
    CalculatorButton buttonFour;
    CalculatorButton buttonFive;
    CalculatorButton buttonSix;
    CalculatorButton buttonSeven;
    CalculatorButton buttonEight;
    CalculatorButton buttonNine;
    CalculatorButton buttonDecimal;
    CalculatorButton buttonClear;
    CalculatorButton buttonDelete;
    CalculatorButton buttonEquals;
    CalculatorButton buttonMultiply;
    CalculatorButton buttonDivide;
    CalculatorButton buttonAdd;
    CalculatorButton buttonSubtract;
    CalculatorButton buttonSquare;
    CalculatorButton buttonReciprocal;
    CalculatorButton buttonSquareRoot;

    //Used for displaying calculations
    CalculatorFrame frame;
    CalculatorScreen screenDisplay;
    JLabel currentCalculation;
    CalculatorPanel zeroAndDecimalButtonsPanel;
    
    //Used for calculating data
    double floatNumber, floatAnswer;
    String floatOrInt;
    int calculation;
    
    //Used for logging data
    Logger logger;
    StringBuilder logging;
    boolean isUnary;


    Calculator() {
        floatOrInt = "float";
        isUnary = false;
        floatNumber = 0;
        floatAnswer = 0;
        prepareGraphicalInterface();
        addFunctionalityToButtons();
        createLogger();
    }

    //Creates logger for logging user input
    private void createLogger(){
        logging = new StringBuilder("\n");
        logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            fh = new FileHandler("logs/MyLogFile.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates the graphical interface of the calculator
     */
    private void prepareGraphicalInterface() {
        frame = new CalculatorFrame("Calculator", CALCULATOR_WIDTH, CALCULATOR_HEIGHT);
        frame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.out.println("logFiles are in logs/MyLogFile.txt");
            }
        });
        screenDisplay = new CalculatorScreen(10, 40, CALCULATOR_SCREEN_WIDTH, CALCULATOR_SCREEN_HEIGHT);

        currentCalculation = new JLabel();
        currentCalculation.setBounds(250, 0, 50, 50);
        currentCalculation.setForeground(Color.white);

        prepareButtons();
        CalculatorPanel mainPanel = prepareLayout();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Creates the buttons graphical interface
     */
    private void prepareButtons() {
        floatingPointButton = new CalculatorRadioButton("Float");
        floatingPointButton.setSelected(true); // on is selected by default
        integerRadioButton = new CalculatorRadioButton("Int");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(floatingPointButton);
        buttonGroup.add(integerRadioButton);
        buttonZero = new CalculatorButton("0");
        buttonOne = new CalculatorButton("1");
        buttonTwo = new CalculatorButton("2");
        buttonThree = new CalculatorButton("3");
        buttonFour = new CalculatorButton("4");
        buttonFive = new CalculatorButton("5");
        buttonSix = new CalculatorButton("6");
        buttonSeven = new CalculatorButton("7");
        buttonEight = new CalculatorButton("8");
        buttonNine = new CalculatorButton("9");
        buttonDecimal = new CalculatorButton(".", BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonSquareRoot = new CalculatorButton("\u221A");
        buttonSquare = new CalculatorButton("X\u00B2");
        buttonReciprocal = new CalculatorButton("1/x");
        buttonSubtract = new CalculatorButton("-", Color.ORANGE, Color.BLACK);
        buttonMultiply = new CalculatorButton("X", Color.ORANGE, Color.BLACK);
        buttonAdd = new CalculatorButton("+", Color.ORANGE, Color.BLACK);
        buttonDivide = new CalculatorButton("/", Color.ORANGE, Color.BLACK);
        buttonClear = new CalculatorButton("C", Color.RED, Color.WHITE);
        buttonDelete = new CalculatorButton("DEL", Color.RED, Color.WHITE);
        buttonEquals = new CalculatorButton("=", Color.ORANGE, Color.BLACK, BUTTON_WIDTH, BUTTON_HEIGHT_2);
    }


    /**
     * Prepares layout panel to arrange buttons
     */
    private CalculatorPanel prepareLayout(){

        CalculatorPanel mainPanel = new CalculatorPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(35, 15, 15, 15));
        CalculatorPanel allButtonPanel = new CalculatorPanel(new BorderLayout(BUTTON_SPACE_HORIZONTAL, 0));
        allButtonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        CalculatorPanel whiteButtonsPanel = new CalculatorPanel(new BorderLayout(0, BUTTON_SPACE_VERTICAL));
        CalculatorPanel numberButtonsPanel = new CalculatorPanel(new GridLayout(5, 3, BUTTON_SPACE_HORIZONTAL, BUTTON_SPACE_VERTICAL));
        CalculatorPanel radioButtonsPanel = new CalculatorPanel(new GridLayout(2,1, BUTTON_SPACE_HORIZONTAL, 0));
        zeroAndDecimalButtonsPanel = new CalculatorPanel(new BorderLayout(BUTTON_SPACE_HORIZONTAL,0));
        CalculatorPanel equalsButtonPanel = new CalculatorPanel(new BorderLayout(0, BUTTON_SPACE_VERTICAL));
        CalculatorPanel operationButtonsPanel = new CalculatorPanel(new GridLayout(4, 1, BUTTON_SPACE_HORIZONTAL, BUTTON_SPACE_VERTICAL));

        mainPanel.add(allButtonPanel, BorderLayout.CENTER);
        allButtonPanel.add(whiteButtonsPanel, BorderLayout.CENTER);
        allButtonPanel.add(equalsButtonPanel, BorderLayout.EAST);
        whiteButtonsPanel.add(numberButtonsPanel, BorderLayout.CENTER);
        whiteButtonsPanel.add(zeroAndDecimalButtonsPanel, BorderLayout.SOUTH);
        equalsButtonPanel.add(operationButtonsPanel, BorderLayout.CENTER);
        numberButtonsPanel.add(radioButtonsPanel);

        mainPanel.add(currentCalculation,BorderLayout.NORTH);
        mainPanel.add(screenDisplay, BorderLayout.NORTH);
        radioButtonsPanel.add(floatingPointButton);
        radioButtonsPanel.add(integerRadioButton);
        numberButtonsPanel.add(buttonClear);
        numberButtonsPanel.add(buttonDelete);
        numberButtonsPanel.add(buttonSquareRoot);
        numberButtonsPanel.add(buttonSquare);
        numberButtonsPanel.add(buttonReciprocal);
        numberButtonsPanel.add(buttonSeven);
        numberButtonsPanel.add(buttonEight);
        numberButtonsPanel.add(buttonNine);
        numberButtonsPanel.add(buttonFour);
        numberButtonsPanel.add(buttonFive);
        numberButtonsPanel.add(buttonSix);
        numberButtonsPanel.add(buttonOne);
        numberButtonsPanel.add(buttonTwo);
        numberButtonsPanel.add(buttonThree);
        operationButtonsPanel.add(buttonDivide);
        operationButtonsPanel.add(buttonSubtract);
        operationButtonsPanel.add(buttonMultiply);
        operationButtonsPanel.add(buttonAdd);
        equalsButtonPanel.add(buttonEquals, BorderLayout.SOUTH);
        zeroAndDecimalButtonsPanel.add(buttonZero, BorderLayout.CENTER);
        zeroAndDecimalButtonsPanel.add(buttonDecimal, BorderLayout.EAST);

        return mainPanel;
    }

    /**
     *  Creates functionality to each button
     */
    private void addFunctionalityToButtons() {
        buttonOne.addActionListener(new CalculatorAction(screenDisplay, 1));
        buttonTwo.addActionListener(new CalculatorAction(screenDisplay, 2));
        buttonThree.addActionListener(new CalculatorAction(screenDisplay, 3));
        buttonFour.addActionListener(new CalculatorAction(screenDisplay, 4));
        buttonFive.addActionListener(new CalculatorAction(screenDisplay, 5));
        buttonSix.addActionListener(new CalculatorAction(screenDisplay, 6));
        buttonSeven.addActionListener(new CalculatorAction(screenDisplay, 7));
        buttonEight.addActionListener(new CalculatorAction(screenDisplay, 8));
        buttonNine.addActionListener(new CalculatorAction(screenDisplay, 9));
        buttonZero.addActionListener(new CalculatorAction(screenDisplay, 0));

        floatingPointButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchFloatingPointCalculator(floatOrInt);
            }
        });
        integerRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchIntegerCalculator(floatOrInt);
            }
        });

        buttonClear.addActionListener(new CalculatorAction(screenDisplay, currentCalculation,"clear"));
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                floatNumber = 0;
                calculation = 0;
            }
        });
        buttonDelete.addActionListener(new CalculatorAction(screenDisplay, currentCalculation,"delete"));

        buttonDecimal.addActionListener(new CalculatorAction(screenDisplay, currentCalculation, "decimal"));

        buttonDivide.addActionListener(this);
        buttonSquareRoot.addActionListener(this);
        buttonSquare.addActionListener(this);
        buttonReciprocal.addActionListener(this);
        buttonSubtract.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonAdd.addActionListener(this);


        buttonEquals.addActionListener(this);
    }


    /**
     *   Logs the current calculation entered by user
     */
    public String logCurrent(String operation) {
        if (!isUnary) {
            logging.append(screenDisplay.getText() + " " + operation + " ");
        } else {
            logging.append(" " + operation + " ");
            isUnary = false;
        }
        return operation;
    }

    /**
     * Calculator logic for computing calculation
     * @param e Action event to use for obtaining source of teh action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object source = e.getSource();

            /**
             * Floating Integer Calculator Logic
             */
            if (floatOrInt.equalsIgnoreCase("float")) {
                if (source == buttonAdd) {
                    logCurrent("+");
                    if (calculation == 0) {
                        setupAdd();
                    } else {
                        add(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupAdd();
                    }
                } else if (source == buttonSubtract) {
                    logCurrent("-");
                    if (calculation == 0) {
                        setupSubtract();
                    } else {
                        subtract(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupSubtract();
                    }
                } else if (source == buttonMultiply) {
                    logCurrent("*");
                    if (calculation == 0) {
                        setupMultiply();
                    } else {
                        multiply(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupMultiply();
                    }
                } else if (source == buttonDivide) {
                    logCurrent("/");
                    if (calculation == 0) {
                        setupDivide();
                    } else {
                        divide(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupDivide();
                    }
                } else if (source == buttonSquareRoot) {
                    logging.append("sqrt(" + screenDisplay.getText() + ")");
                    isUnary = true;
                    floatNumber = Double.parseDouble(screenDisplay.getText());
                    Double sqrt = Math.sqrt(floatNumber);
                    screenDisplay.setText(Double.toString(sqrt));

                } else if (source == buttonSquare) {
                    String str = screenDisplay.getText();
                    logging.append(screenDisplay.getText() + "^2");
                    isUnary = true;
                    floatNumber = Double.parseDouble(screenDisplay.getText());
                    double square = Math.pow(floatNumber, 2);
                    String string = Double.toString(square);
                    if (string.endsWith(".0")) {
                        screenDisplay.setText(string.replace(".0", ""));
                    } else {
                        screenDisplay.setText(string);
                    }
                    currentCalculation.setText(str + "^2");
                } else if (source == buttonReciprocal) {
                    String str = screenDisplay.getText();
                    logging.append("1/" + screenDisplay.getText());
                    isUnary = true;
                    floatNumber = Double.parseDouble(screenDisplay.getText());
                    double reciprocal = 1 / floatNumber;
                    String string = Double.toString(reciprocal);
                    if (string.endsWith(".0")) {
                        screenDisplay.setText(string.replace(".0", ""));
                    } else {
                        screenDisplay.setText(string);
                    }
                }

                if (source == buttonEquals) {
                    if(!isUnary) logging.append(screenDisplay.getText());
                    doubleEquals(floatNumber, floatAnswer);
                    logging.append(" = " + screenDisplay.getText() + "\n");
                    logger.info(logging.toString());
                    isUnary = false;
                }

                /**
                 * Integer Calculator Logic
                 */
            } else {
                if (Math.abs(Double.parseDouble(screenDisplay.getText())) > Integer.MAX_VALUE) {
                    throw new outOfRangeIntegerError("result is out bounds");
                }
                if (source == buttonAdd) {
                    logCurrent("+");
                    if (calculation == 0) {
                        setupAdd();
                    } else {
                        add(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupAdd();
                    }
                } else if (source == buttonSubtract) {
                    logCurrent("-");
                    if (calculation == 0) {
                        setupSubtract();
                    } else {
                        subtract(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupSubtract();
                    }
                } else if (source == buttonMultiply) {
                    logCurrent("*");
                    if (calculation == 0) {
                        setupMultiply();
                    } else {
                        multiply(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupMultiply();
                    }
                } else if (source == buttonDivide) {
                    logCurrent("/");
                    if (calculation == 0) {
                        setupIntegerDivide();
                    } else {
                        integerDivide(floatNumber, Double.parseDouble(screenDisplay.getText()));
                        setupIntegerDivide();
                    }
                } else if (source == buttonSquareRoot) {
                    logging.append("sqrt(" + screenDisplay.getText() + ")");
                    isUnary = true;
                    double sq = Math.sqrt(Double.parseDouble(screenDisplay.getText()));
                    if (sq - Math.floor(sq) != 0) {
                        throw new ArithmeticException();
                    }

                    if (Double.toString(sq).endsWith(".0")) {
                        screenDisplay.setText(Double.toString(sq).replace(".0", ""));
                    } else {
                        screenDisplay.setText(Double.toString(sq));
                    }
                } else if (source == buttonSquare) {
                    String str = screenDisplay.getText();
                    logging.append(screenDisplay.getText() + "^2");
                    isUnary = true;
                    floatNumber = Double.parseDouble(screenDisplay.getText());
                    double square = Math.pow(floatNumber, 2);
                    String string = Double.toString(square);
                    if (string.endsWith(".0")) {
                        screenDisplay.setText(string.replace(".0", ""));
                    } else {
                        screenDisplay.setText(string);
                    }
                    currentCalculation.setText(str+ "^2");
                } else if (source == buttonReciprocal) {
                    logging.append("1/" + screenDisplay.getText());
                    isUnary = true;
                    if (Double.parseDouble(screenDisplay.getText()) != 1) {
                        throw new ArithmeticException();

                    }
                    floatNumber = Double.parseDouble(screenDisplay.getText());
                    double reciprocal = 1 / floatNumber;
                    String string = Double.toString(reciprocal);
                    if (string.endsWith(".0")) {
                        screenDisplay.setText(string.replace(".0", ""));
                    } else {
                        screenDisplay.setText(string);
                    }
                }

                if (source == buttonEquals) {
                    if(!isUnary) logging.append(screenDisplay.getText());
                    integerEquals(floatNumber, floatAnswer);
                    if (Math.abs(Double.parseDouble(screenDisplay.getText())) > Integer.MAX_VALUE) {
                        throw new outOfRangeIntegerError("result is out bounds");
                    }
                    logging.append(" = " + screenDisplay.getText() + "\n");
                    logger.info(logging.toString());
                    isUnary = false;
                }

            }

            //If Integer results in too big or too small of a result
        } catch (outOfRangeIntegerError err) {
            String message = "The input number results exceeds maximum/minimum value of integer, please enter another value";
            if(!isUnary) logging.append(screenDisplay.getText());
            logging.append(" = " + message + "\n");
            logger.info(logging.toString());
            isUnary = false;
            JOptionPane.showMessageDialog(frame,
                    "The input number results exceeds maximum/minimum value of integer, please enter another value");


            buttonClear.doClick();

            //If Integer operations result in a non integer
        } catch (ArithmeticException err) {
            String message = "The operation produces a non-integer result, please enter another operand that will \n" +
                    "result in a perfect integer result";
            if(!isUnary) logging.append(screenDisplay.getText());
            logging.append(" = " + message + "\n");
            logger.info(logging.toString());
            isUnary = false;
            JOptionPane.showMessageDialog(frame,
                    "The operation produces a non-integer result, please enter another operand that will \n" +
                            "result in a perfect integer result");
            buttonClear.doClick();

            //If Operation is entered in incorrect order
        } catch (Exception err){
            String message = "Please Enter number and then Operand in correct order: Number, Operand, Number.";
            if(!isUnary) logging.append(screenDisplay.getText());
            logging.append(" = " + message + "\n");
            logger.info(logging.toString());
            isUnary = false;
            JOptionPane.showMessageDialog(frame,
                    "Please Enter number and then Operand in correct order: Number, Operand, Number.");
            buttonClear.doClick();
        }

    }

    /**
     *
     * @param x first number to add
     * @param y second number to add
     * @return result of summation
     *     Performs calculation for integer calculator
     */
    public boolean integerEquals(double x, double y){
        switch (calculation) {
            case 1:
                add(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 2:
                subtract(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 3:
                multiply(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 4:
                integerDivide(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
        }
        return x == y;
    }

    /**
     * Performs calculation for floating point calculator
     * @param x first number to perform calculation
     * @param y second number to perform calculation
     * @return calculation performed
     */

    public boolean doubleEquals(double x, double y){
        switch (calculation) {
            case 1:
                add(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 2:
                subtract(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 3:
                multiply(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
            case 4:
                divide(floatNumber, Double.parseDouble(screenDisplay.getText()));
                break;
        }
        return x == y;
    }


    /**
     * Performs division for integer calculator
     * @param x first to divide
     * @param y second to divide
     * @return return integer division result
     */
    public int integerDivide(double x, double y) {
        int number = (int) x;
        int divisor = (int) y;
        int answer = number/divisor;

        floatAnswer = answer;
        if (Double.toString(floatAnswer).endsWith(".0")) {
            screenDisplay.setText(Double.toString(floatAnswer).replace(".0", ""));
        } else {
            screenDisplay.setText(Double.toString(floatAnswer));
        }
        currentCalculation.setText("");
        calculation = 0;

        return answer;
    }

    /**
     * If user decides to enter another operation for integer division
     */
    private void setupIntegerDivide() {

        String str = screenDisplay.getText();
        floatNumber = Double.parseDouble(screenDisplay.getText());
        screenDisplay.setText("");
        currentCalculation.setText(str + "/");

        calculation = 4;
    }


    /**
     *     //If user decides to enter another operation for division
     */
    private void setupDivide() {
        String str = screenDisplay.getText();
        floatNumber = Double.parseDouble(screenDisplay.getText());
        screenDisplay.setText("");
        currentCalculation.setText(str + "/");
        calculation = 4;
    }


    /**
     *
     //If user decides to enter another operation for multiplication
     */
    private void setupMultiply() {
        String str = screenDisplay.getText();
        floatNumber = Double.parseDouble(screenDisplay.getText());
        screenDisplay.setText("");
        currentCalculation.setText(str + "x");
        calculation = 3;
    }

    /**
     * If user decides to enter another operation for subtraction
     */
    private void setupSubtract() {
        String str = screenDisplay.getText();
        floatNumber = Double.parseDouble(screenDisplay.getText());
        screenDisplay.setText("");
        currentCalculation.setText(str + "-");
        calculation = 2;
    }

    /**
     * //If user decides to enter another operation for addition
     */
    private void setupAdd(){
        String str = screenDisplay.getText();
        floatNumber = Double.parseDouble(screenDisplay.getText());
        screenDisplay.setText("");
        currentCalculation.setText(str + "+");
        calculation = 1;
    }

    /**
     * /Performs addition for current calculation
     * @param floatNumber first to add
     * @param y second to add
     * @return returns summation of both number
     */
    public double add(double floatNumber, double y) {
        floatAnswer = floatNumber + y;
        if (Double.toString(floatAnswer).endsWith(".0")) {
            screenDisplay.setText(Double.toString(floatAnswer).replace(".0", ""));
        } else {
            screenDisplay.setText(Double.toString(floatAnswer));
        }
        currentCalculation.setText("");
        calculation = 0;
        return floatAnswer;
    }

    /**
     * Performs subtraction for current calculation
     * @param x to subract from
     * @param y what to subtract
     * @return subtraction results
     */
    public double subtract(double x, double y){
        floatAnswer = x - y;
        if (Double.toString(floatAnswer).endsWith(".0")) {
            screenDisplay.setText(Double.toString(floatAnswer).replace(".0", ""));
        } else {
            screenDisplay.setText(Double.toString(floatAnswer));
        }
        currentCalculation.setText("");
        calculation = 0;
        return floatAnswer;
    }

    /**
     * Performs multiplication for current calculation
     * @param x multiply from
     * @param y multiply to
     * @return multiplication results
     */
    public double multiply(double x, double y){
        floatAnswer = x * y;
        if (Double.toString(floatAnswer).endsWith(".0")) {
            screenDisplay.setText(Double.toString(floatAnswer).replace(".0", ""));
        } else {
            screenDisplay.setText(Double.toString(floatAnswer));
        }
        currentCalculation.setText("");
        calculation = 0;
        return x * y;
    }


    /**
     *  //Performs division for current calculation
     * @param x to divide from
     * @param y to divide to
     * @return results of division two numbers
     */
    public double divide(double x, double y) {
        floatAnswer = x / y;
        if (Double.toString(floatAnswer).endsWith(".0")) {
            screenDisplay.setText(Double.toString(floatAnswer).replace(".0", ""));
        } else {
            screenDisplay.setText(Double.toString(floatAnswer));
        }
        currentCalculation.setText("");
        calculation = 0;
        return floatAnswer;
    }

    /**
     * switches to floating point calculator
     * @param current if float or int
     * @return returns int if float returns float if int
     */
    public String switchFloatingPointCalculator(String current) {
        floatOrInt = "float";
        floatingPointButton.setEnabled(false);
        integerRadioButton.setEnabled(true);
        zeroAndDecimalButtonsPanel.add(buttonDecimal, BorderLayout.EAST);
        zeroAndDecimalButtonsPanel.validate();
        return current.equalsIgnoreCase("float") ? "int" : "float";
    }

    /**
     * switches to integer point calculator
     * @param current if float or int
     * @return returns int if float returns float if int
     */
    public String switchIntegerCalculator(String current) {
        floatOrInt = "int";
        floatingPointButton.setEnabled(true);
        zeroAndDecimalButtonsPanel.remove(buttonDecimal);
        zeroAndDecimalButtonsPanel.validate();

        integerRadioButton.setEnabled(false);
        screenDisplay.setText("");
        currentCalculation.setText(" ");
        return current.equalsIgnoreCase("float") ? "int" : "float";
    }


}