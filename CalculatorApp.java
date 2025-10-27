import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorClicked = false;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        JButton clearBtn = new JButton("C");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clearBtn.addActionListener(e -> display.setText(""));
        add(clearBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if ("0123456789.".contains(input)) {
            if (isOperatorClicked) {
                display.setText("");
                isOperatorClicked = false;
            }
            display.setText(display.getText() + input);
        } else if ("/-*+".contains(input)) {
            firstNumber = Double.parseDouble(display.getText());
            operator = input;
            isOperatorClicked = true;
        } else if ("=".equals(input)) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/": 
                    if (secondNumber != 0) result = firstNumber / secondNumber;
                    else JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorApp::new);
    }
}
