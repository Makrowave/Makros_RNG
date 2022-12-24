import com.makrowave.util.MakroColors;
import com.makrowave.util.TextFieldPanel;
import com.makrowave.util.TogglableButtonGroup;
import com.makrowave.util.ValueTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringJoiner;

import static com.makrowave.util.RandomGenerator.RandomInteger;
import static java.lang.Integer.parseInt;

public class ApplicationFrame extends JFrame implements ActionListener {


    JPanel buttonMenu = new JPanel();
    JButton helpButton = new JButton("Help");
    JRadioButton darkModeButton = new JRadioButton("Dark Mode");
    JTextField minField = new ValueTextField("0");
    JTextField maxField = new ValueTextField("100");
    JTextField stepField = new ValueTextField("1");
    JTextField quantityField = new ValueTextField("1");
    JTextField addField = new ValueTextField("0");
    JRadioButton evenRadioButton = new JRadioButton();
    JRadioButton oddRadioButton = new JRadioButton();
    JRadioButton sortRadioButton = new JRadioButton();
    JButton generateButton = new JButton("Generate!");

    TogglableButtonGroup oddEvenGroup;

    JTextArea outputField = new JTextArea(0, 0);
    ArrayList<JComponent> components = new ArrayList<>(){{
        add(buttonMenu);
        add(helpButton);
        add(darkModeButton);
        add(minField);
        add(maxField);
        add(stepField);
        add(quantityField);
        add(addField);
        add(evenRadioButton);
        add(oddRadioButton);
        add(sortRadioButton);
        add(generateButton);
        add(outputField);
    }};
    ApplicationFrame(){
        this.setBackground(MakroColors.lightBackground);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        //BUTTON MENU (not really a menu, yet I don't need different implementation)
        FlowLayout buttonMenuLayout = new FlowLayout(FlowLayout.LEFT);
        buttonMenu.setLayout(buttonMenuLayout);
        buttonMenu.setOpaque(true);

        helpButton.setSize(60, 20);
        helpButton.addActionListener(this);

        darkModeButton.setSelected(true);
        darkModeButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
        darkModeButton.addActionListener(this);

        buttonMenu.add(helpButton);
        buttonMenu.add(darkModeButton);

        this.add(buttonMenu);


        //PANEL TO HOLD FIELDS
        JPanel fields = new JPanel();
        components.add(fields);

        GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setHgap(20);
        fields.setLayout(gridLayout);
        //TEXT FIELDS FOR ARGS
        //MINIMUM VALUE
        JPanel minPanel = new TextFieldPanel(new JLabel("Minimum Value"), minField);
        fields.add(minPanel);
        components.add(minPanel);

        //MAXIMUM VALUE
        JPanel maxPanel = new TextFieldPanel(new JLabel("Maximum Value"), maxField);
        fields.add(maxPanel);
        components.add(maxPanel);

        //STEP VALUE - subject to change
        JPanel stepPanel = new TextFieldPanel(new JLabel("Step"), stepField);
        fields.add(stepPanel);
        components.add(stepPanel);

        //QUANTITY OF NUMBERS
        JPanel quantityPanel = new TextFieldPanel(new JLabel("Quantity"), quantityField);
        fields.add(quantityPanel);
        components.add(quantityPanel);

        //NUMBER ADDED TO RESULT
        JPanel addPanel = new TextFieldPanel(new JLabel("Add number to the result"), addField);
        fields.add(addPanel);
        components.add(addPanel);

        //CHECKBOXES
        oddEvenGroup = new TogglableButtonGroup();

        //SHOULD NUMBER BE ODD
        JPanel oddPanel = new TextFieldPanel(new JLabel("Odd numbers"), oddRadioButton);
        fields.add(oddPanel);
        components.add(oddPanel);
        oddEvenGroup.add(oddRadioButton);

        //SHOULD NUMBER BE EVEN
        JPanel evenPanel = new TextFieldPanel(new JLabel("Even numbers"), evenRadioButton);
        fields.add(evenPanel);
        components.add(evenPanel);
        oddEvenGroup.add(evenRadioButton);

        //SHOULD RESULT BE SORTED
        JPanel sortPanel = new TextFieldPanel(new JLabel("Sort result"), sortRadioButton);
        fields.add(sortPanel);
        components.add(sortPanel);

        this.add(fields);
        //Button and output
        generateButton.setSize(200, 100);
        generateButton.addActionListener(this);
        this.add(generateButton);

        outputField.setPreferredSize(new Dimension(400, 500));
        outputField.setText("Result");
        outputField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        outputField.setEditable(false);
        outputField.setLineWrap(true);
        outputField.setWrapStyleWord(true);
        JScrollPane outputScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScroll.setViewportView(outputField);
        components.add(outputScroll);
        this.add(outputScroll);

        changeColor(this, components, darkModeButton.isSelected());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Generate result on generateButton press
        if(e.getSource()==generateButton){
            //Checking if text fields are valid
            int minValue = stringToInt(minField, 0);
            int maxValue = stringToInt(maxField, 100);
            int stepValue = stringToInt(stepField, 1);
            int addValue = stringToInt(addField, 0);
            int quantityValue = stringToInt(quantityField, 1);

            //Swapping min and max values if min>max
            if(minValue>maxValue){
                int temp = maxValue;
                maxValue = minValue;
                minValue = temp;
                minField.setText(Integer.toString(minValue));
                maxField.setText(Integer.toString(maxValue));
            }

            //Anti infinite loop check
            if ((minValue%2==1 && evenRadioButton.isSelected()) && ((stepValue%2==0 || minValue==maxValue) || minValue+stepValue>maxValue)){
                evenRadioButton.setSelected(false);
            } else if((minValue%2==0 && oddRadioButton.isSelected()) && (stepValue%2==0 || minValue==maxValue) || minValue+stepValue>maxValue) {
                oddRadioButton.setSelected(false);
            }

            ArrayList<Integer> result = RandomInteger(
                    minValue, maxValue,
                    stepValue, evenRadioButton.isSelected(),
                    oddRadioButton.isSelected(), quantityValue,
                    addValue, sortRadioButton.isSelected());
            StringJoiner output = new StringJoiner(" ");
            for (Integer number : result) {
                output.add(number.toString());
            }
            //Definitely not a perfect solution, yet sufficient for this
            outputField.setText(output.toString());
            try {
                outputField.setRows(outputField.getLineEndOffset(0)/135);
            }
            catch (Exception exception){
                //This doesn't throw exception anyway
            }
        }

        //Changes between dark mode and light mode
        if(e.getSource()==darkModeButton){
            changeColor(this, components, darkModeButton.isSelected());
        }
    }
    private static int stringToInt(JTextField mTextField, int mDefault) {
        int mNumber;
        try {
            mNumber = parseInt(mTextField.getText());
        }
        catch (Exception e) {
            mNumber = mDefault;
            mTextField.setText(Integer.toString(mDefault));
            //add information window in order for user to be informed what went wrong
        }
        return mNumber;
    }
    private static void changeLabelColor(JPanel panel, boolean darkMode) {
        if(panel.getComponentCount()!=0){
            Component[] components = panel.getComponents();
            for(Component comp : components){
                if(comp instanceof JLabel){
                    if(darkMode){
                        comp.setForeground(MakroColors.darkText);
                    }
                    else {
                        comp.setForeground(MakroColors.lightText);
                    }
                }
            }
        }
    }


    private static void changeColor(ApplicationFrame frame, ArrayList<JComponent> components, boolean darkMode) {
        if (darkMode){
            frame.getContentPane().setBackground(MakroColors.darkBackground);
            //Changes colours of particular parts of components
            for(Component comp : components){
                if(comp instanceof JPanel){
                    comp.setBackground(MakroColors.darkBackground);
                    changeLabelColor((JPanel) comp, darkMode);
                }
                else if(comp instanceof AbstractButton || comp instanceof JLabel){
                    comp.setBackground(MakroColors.darkBackground);
                    comp.setForeground(MakroColors.darkText);
                }else if(comp instanceof JTextArea){
                    comp.setBackground(MakroColors.darkField);
                    comp.setForeground(MakroColors.darkText);
                }else if(comp instanceof JTextField){
                    comp.setForeground(MakroColors.darkText);
                    comp.setBackground(MakroColors.darkField);
                    ((JTextField) comp).setCaretColor(MakroColors.darkText);
                }
            }
        }//Same but for light mode
        else {
            for(Component comp : components){
                frame.getContentPane().setBackground(MakroColors.lightBackground);
                if(comp instanceof JPanel){
                    comp.setBackground(MakroColors.lightBackground);
                    changeLabelColor((JPanel) comp, darkMode);
                }
                else if(comp instanceof AbstractButton || comp instanceof JLabel){
                    comp.setBackground(MakroColors.lightBackground);
                    comp.setForeground(MakroColors.lightText);
                }else if(comp instanceof JTextArea){
                    comp.setBackground(MakroColors.lightField);
                    comp.setForeground(MakroColors.lightText);
                }else if(comp instanceof JTextField){
                    comp.setForeground(MakroColors.lightText);
                    comp.setBackground(MakroColors.lightField);
                    ((JTextField) comp).setCaretColor(MakroColors.lightText);
                }
            }
        }
    }
}
