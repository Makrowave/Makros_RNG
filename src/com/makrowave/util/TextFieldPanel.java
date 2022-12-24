package com.makrowave.util;

import javax.swing.*;
import java.awt.*;

public class TextFieldPanel extends JPanel {
    public TextFieldPanel(JLabel label, JTextField textField){
        label.setFont(new Font("Helvetica", Font.PLAIN, 20));
        this.add(label);
        this.add(textField);
        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setOpaque(true);
    }
    public TextFieldPanel(JLabel label, JRadioButton radioButton){
        label.setFont(new Font("Helvetica", Font.PLAIN, 20));
        this.add(label);
        this.add(radioButton);
        GridLayout gridLayout = new GridLayout(1, 2);
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setOpaque(true);
    }
}
