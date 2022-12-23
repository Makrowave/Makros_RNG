import com.makrowave.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringJoiner;

import static com.makrowave.RandomGenerator.RandomInteger;
import static java.lang.Integer.parseInt;

public class ApplicationFrame extends JFrame implements ActionListener {


    JTextField minField;
    JTextField maxField;
    JTextField stepField;
    JTextField quantityField;
    JTextField addField;
    JButton generateButton;

    JTextArea outputField;
    ApplicationFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(0, 3));
        //TEXT FIELDS FOR ARGS
        //MINIMUM VALUE
        minField = new JTextField();
        minField.setPreferredSize(new Dimension(100, 40));
        minField.setText("0");
        JPanel minPanel = new ButtonPanel(new JLabel("Minimum Value"), minField);
        fields.add(minPanel);
        //MAXIMUM VALUE
        maxField = new JTextField();
        maxField.setPreferredSize(new Dimension(100, 40));
        maxField.setText("100");
        JPanel maxPanel = new ButtonPanel(new JLabel("Maximum Value"), maxField);
        fields.add(maxPanel);
        //STEP VALUE - subject to change
        stepField = new JTextField();
        stepField.setPreferredSize(new Dimension(100, 40));
        stepField.setText("1");
        JPanel stepPanel = new ButtonPanel(new JLabel("Step"), stepField);
        fields.add(stepPanel);
        //QUANTITY OF NUMBERS
        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(100, 40));
        quantityField.setText("1");
        JPanel quantityPanel = new ButtonPanel(new JLabel("Quantity"), quantityField);
        fields.add(quantityPanel);
        //NUMBER ADDED TO RESULT
        addField = new JTextField();
        addField.setPreferredSize(new Dimension(100, 40));
        addField.setText("0");
        JPanel addPanel = new ButtonPanel(new JLabel("Add number to the result"), addField);
        fields.add(addPanel);

        this.add(fields);

        generateButton = new JButton("Generate!");
        generateButton.setSize(200, 100);
        generateButton.addActionListener(this);
        this.add(generateButton);

        outputField = new JTextArea();
        outputField.setPreferredSize(new Dimension(1000, 400));
        outputField.setText("result");
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        this.add(outputField);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==generateButton){
            //Checking if text fields contain anything - probably will change to try catch
            if (!(minField.getText().equals("") ||
            maxField.getText().equals("") ||
            stepField.getText().equals("") ||
            quantityField.getText().equals("") ||
            addField.getText().equals(""))) {


                ArrayList<Integer> result = RandomInteger(
                        parseInt(minField.getText()), parseInt(maxField.getText()),
                        parseInt(stepField.getText()), false,
                        false, parseInt(quantityField.getText()),
                        parseInt(addField.getText()), false);
                StringJoiner output = new StringJoiner(" ");
                for (Integer number : result) {
                    output.add(number.toString());
                }
                outputField.setText(output.toString());
            }
            else
                outputField.setText("Error: User left an empty field");
        }
    }
}
