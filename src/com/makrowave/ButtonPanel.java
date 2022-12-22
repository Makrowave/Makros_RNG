package com.makrowave;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(JLabel label, JTextField textField){
        this.add(label);
        this.add(textField);
        this.setSize(200, 50);
    }
}
