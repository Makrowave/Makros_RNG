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

    JTextField outputField;
    ApplicationFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        generateButton = new JButton("Generate");

        minField = new JTextField();
        minField.setPreferredSize(new Dimension(100, 40));
        minField.setText("0");
        JPanel minPanel = new ButtonPanel(new JLabel("Minimum Value"), minField);
        this.add(minPanel);

        maxField = new JTextField();
        maxField.setPreferredSize(new Dimension(100, 40));
        maxField.setText("100");
        JPanel maxPanel = new ButtonPanel(new JLabel("Maximum Value"), maxField);
        this.add(maxPanel);

        stepField = new JTextField();
        stepField.setPreferredSize(new Dimension(100, 40));
        stepField.setText("1");
        JPanel stepPanel = new ButtonPanel(new JLabel("Step"), stepField);
        this.add(stepPanel);

        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(100, 40));
        quantityField.setText("1");
        JPanel quantityPanel = new ButtonPanel(new JLabel("Quantity"), quantityField);
        this.add(quantityPanel);

        addField = new JTextField();
        addField.setPreferredSize(new Dimension(100, 40));
        addField.setText("0");
        JPanel addPanel = new ButtonPanel(new JLabel("Add number to the result"), addField);
        this.add(addPanel);

        generateButton = new JButton();
        generateButton.setSize(200, 100);
        generateButton.setText("Generate!");
        generateButton.addActionListener(this);
        this.add(generateButton);

        outputField = new JTextField();
        outputField.setPreferredSize(new Dimension(1000, 400));
        outputField.setText("result");
        outputField.setEditable(false);
        this.add(outputField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==generateButton){
            //Checking if text fields contain something
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
                StringJoiner output = new StringJoiner(", ");
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
